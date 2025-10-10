package com.sergey.demoprojectspringboot.front.api;

import ch.qos.logback.core.pattern.util.IEscapeUtil;

import javax.xml.xpath.XPath;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiClient {

    private String baseUrl;
    private String bearerToken;

    public ApiClient(String baseUrl) {
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
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


    public String get(String path) throws IOException {
        requireAuth();
        HttpURLConnection conn = (HttpURLConnection) new URL(baseUrl + path).openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.setRequestProperty("Authorization", bearerToken);

        int code = conn.getResponseCode();
        String body = readBody(code < 400 ? conn.getInputStream() : conn.getErrorStream());
        if (code >= 200 && code < 300) return body;
        throw new IOException("GET " + path + " -> " + code + ": " + body);
    }


    public String post(String path, String jsonBody) throws IOException {
        requireAuth();
        HttpURLConnection conn = (HttpURLConnection) new URL(baseUrl + path).openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", bearerToken);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
        }

        int code = conn.getResponseCode();
        String body = readBody(code < 400 ? conn.getInputStream() : conn.getErrorStream());
        if (code >= 200 && code < 300) return body;
        throw new IOException("POST " + path + " -> " + code + ": " + body);
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
