package com.sergey.demoprojectspringboot.front.frame;

import com.sergey.demoprojectspringboot.front.api.ApiClient;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JTextField tfUsername = new JTextField(24);
    private final JPasswordField pfPassword = new JPasswordField(24);
    private final JButton btnLogin = new JButton("Login");
    private final JLabel lblStatus = new JLabel(" ");

    private final ApiClient api;

    public LoginFrame(ApiClient api) {
        super("Authorization");
        this.api = api;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 10, 6, 10);
        c.fill = GridBagConstraints.HORIZONTAL;


        c.gridx = 0; c.gridy = 0; add(new JLabel("Username (email):"), c);
        c.gridx = 1; c.gridy = 0; add(tfUsername, c);


        c.gridx = 0; c.gridy = 1; add(new JLabel("Password:"), c);
        c.gridx = 1; c.gridy = 1; add(pfPassword, c);


        JCheckBox cbShow = new JCheckBox("Show password");
        cbShow.addActionListener(e -> pfPassword.setEchoChar(cbShow.isSelected() ? (char)0 : '\u2022'));
        c.gridx = 1; c.gridy = 2; add(cbShow, c);


        c.gridx = 1; c.gridy = 3; add(btnLogin, c);


        c.gridx = 0; c.gridy = 4; c.gridwidth = 2;
        lblStatus.setForeground(new Color(0x555555));
        add(lblStatus, c);

        btnLogin.addActionListener(e -> doLogin());
        getRootPane().setDefaultButton(btnLogin); // login

        pack();
        setLocationRelativeTo(null);
    }

    private void doLogin() {
        btnLogin.setEnabled(false);
        lblStatus.setForeground(new Color(0x555555));
        lblStatus.setText("Signing in...");

        String username = tfUsername.getText().trim();
        String password = new String(pfPassword.getPassword());

        new Thread(() -> {
            try {
                var res = api.login(username, password);
                SwingUtilities.invokeLater(() -> {
                    if (res.ok) {
                        lblStatus.setForeground(new Color(0x2e7d32));
                        lblStatus.setText("Success! Token printed to console.");
                        System.out.println("JWT: " + res.token);

                        // future
                        // new MainAppFrame(api).setVisible(true);
                        // dispose();

                    } else {
                        lblStatus.setForeground(new Color(0xc62828));
                        lblStatus.setText("Auth error: " + res.errorMessage);
                    }
                });
            } catch (Exception ex) {
                SwingUtilities.invokeLater(() -> {
                    lblStatus.setForeground(new Color(0xc62828));
                    lblStatus.setText("Exception: " + ex.getMessage());
                });
            } finally {
                SwingUtilities.invokeLater(() -> btnLogin.setEnabled(true));
            }
        }).start();
    }
}
