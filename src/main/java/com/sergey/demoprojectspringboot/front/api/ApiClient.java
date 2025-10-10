package com.sergey.demoprojectspringboot.front.api;

import lombok.Getter;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiClient {

    private String baseUrl;
    @Getter
    private String bearerToken;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
    }

    public boolean isAuthorized() {
        return bearerToken != null && !bearerToken.isBlank();
    }




    public LoginResult login(String username, String password) throws IOException {
        String endPoint = baseUrl + "/api/auth";
        String jsonBody = String.format("{\"username\":\"%s\",\"password\":\"%s\"}", username, password);

        HttpURLConnection connection = (HttpURLConnection) new URL(endPoint).openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");

        try (OutputStream os = connection.getOutputStream()) {
            os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = connection.getResponseCode();
        String body = readBody(responseCode < 400 ? connection.getInputStream() : connection.getErrorStream());

        if (responseCode == 200) {
            String token = extractJson(body, "token");
            if (token == null) token = extractJson(body, "jwt");
            if (token == null || token.isBlank()) {
                return LoginResult.notSuccess("Token is empty" + body);
            }
            this.bearerToken = "Bearer " + token;
            return LoginResult.success(token);
        }else if (responseCode == 401 || responseCode == 403) {
            return LoginResult.notSuccess("Error auth" + responseCode + body);
        } else {
            return LoginResult.notSuccess("Error server" + responseCode + body);
        }
    }

    //info about me
    public static class UserInfo {
        public final String email;
        public final String role;
        public final String status;

        public UserInfo(String email, String role, String status) {
            this.email = email;
            this.role = role;
            this.status = status;
        }
    }

    public UserInfo infoAboutMe() throws IOException {
        String json = getJson("/api/auth/me");
        String name = extractJson(json, "name");
        String role = extractJson(json, "role");
        String status = extractJson(json, "status");
        return new UserInfo(name, role, status);
    }


    public String postJson(String path, String json) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(baseUrl + path).openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        if (bearerToken != null) connection.setRequestProperty("Authorization", bearerToken);

        try (OutputStream os = connection.getOutputStream()) {
            os.write(json.getBytes(StandardCharsets.UTF_8));
        }

        int responseCode = connection.getResponseCode();
        String body = readBody(responseCode < 400 ? connection.getInputStream() : connection.getErrorStream());

        if (responseCode >= 200 && responseCode < 300) {
            return body;
        } else {
            throw new IOException("HTTP " + responseCode + ": " + body);
        }
    }



    public String getJson(String path) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(baseUrl + path).openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);
        if (bearerToken != null) {
            connection.setRequestProperty("Authorization", bearerToken);
        }

        int responseCode = connection.getResponseCode();
        String body = readBody(responseCode < 400 ? connection.getInputStream() : connection.getErrorStream());

        if (responseCode >= 200 && responseCode < 300) {
            return body;
        } else {
            throw new IOException("HTTP " + responseCode + " GET " + path + ": " + body);
        }
    }






    private static String readBody(InputStream stream) throws IOException {
        if (stream == null) return "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) sb.append(line);
            return sb.toString();
        }
    }

    private static String extractJson(String json,String key) {
        String needle = "\"" + key + "\"";
        int index = json.indexOf(needle);
        if (index < 0) return null;
        int colon = json.indexOf(':', index + needle.length());
        if (colon < 0) return null;
        int firstQuote = json.indexOf('"', colon + 1);
        if (firstQuote < 0) return null;
        int secondQuote = json.indexOf('"', firstQuote + 1);
        if (secondQuote < 0) return null;
        return json.substring(firstQuote + 1, secondQuote);
    }

    private String escapeJson(String json) {
        return json.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private void requireAuth()  {
        if (bearerToken == null || bearerToken.isBlank()) {
            throw  new IllegalStateException("Bearer token is empty");
        }
    }



    public static class LoginResult{
        public boolean ok;
        public String token;
        public String errorMessage;

        private LoginResult(boolean ok, String token, String errorMessage) {
            this.ok = ok;
            this.token = token;
            this.errorMessage = errorMessage;
        }

        public static LoginResult success(String token) {
            return new LoginResult(true, token,null);
        }




        public static LoginResult notSuccess(String errorMessage) {
            return new LoginResult(false, null, errorMessage);
        }
    }
}
