package com.sergey.demoprojectspringboot.front;


import com.sergey.demoprojectspringboot.front.api.ApiClient;
import com.sergey.demoprojectspringboot.front.frame.LoginFrame;

public class SwingRunner {
    public static void main(String[] args) {
        String backEndUrl = "http://localhost:8080";
        ApiClient client = new ApiClient(backEndUrl);
        javax.swing.SwingUtilities.invokeLater(() -> new LoginFrame(client).setVisible(true));

    }
}
