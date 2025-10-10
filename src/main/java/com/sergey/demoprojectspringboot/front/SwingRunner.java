package com.sergey.demoprojectspringboot.front;


import com.sergey.demoprojectspringboot.front.api.ApiClient;
import com.sergey.demoprojectspringboot.front.frame.AuthFrame;
import com.sergey.demoprojectspringboot.front.frame.MainFrame;

public class SwingRunner {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            ApiClient localHost = new ApiClient("http://localhost:8080");

            AuthFrame auth = new AuthFrame(null,localHost);
            auth.setVisible(true);
            if (localHost.isAuthorized()){
                new MainFrame(localHost).setVisible(true);
            } else {
                System.out.println("Error");
            }
        });



    }
}
