package com.sergey.demoprojectspringboot.front.frame;

import com.sergey.demoprojectspringboot.front.api.ApiClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class AuthFrame extends JDialog {
    private final ApiClient api;
    private final JTextField emailField = new JTextField( 20);
    private final JPasswordField passField = new JPasswordField( 20);
    private final JLabel status = new JLabel(" ");

    public AuthFrame(Frame owner, ApiClient api) {
        super(owner, "Authorization", true);
        this.api = api;

        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(5,5,5,5);
        g.fill = GridBagConstraints.HORIZONTAL;

        g.gridx=0; g.gridy=0; p.add(new JLabel("Username (email):"), g);
        g.gridx=1; p.add(emailField, g);

        g.gridx=0; g.gridy=1; p.add(new JLabel("Password:"), g);
        g.gridx=1; p.add(passField, g);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> doLogin());

        JCheckBox show = new JCheckBox("Show password");
        show.addActionListener(e -> passField.setEchoChar(show.isSelected() ? (char)0 : '\u2022'));
        show.setSelected(true);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.add(show);
        buttons.add(loginBtn);

        status.setForeground(Color.RED);

        getContentPane().add(p, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
        getContentPane().add(status, BorderLayout.NORTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void doLogin() {
        status.setText(" ");
        String email = emailField.getText().trim();
        String pass = new String(passField.getPassword());

        if (email.isBlank() || pass.isBlank()) {
            status.setText("Fill email and password");
            return;
        }
        try {
            ApiClient.LoginResult r = api.login(email, pass);
            if (r.ok) {
                dispose(); // сlose Window
            } else {
                status.setText(r.errorMessage);
            }
        } catch (IOException ex) {
            status.setText("Connection error: " + ex.getMessage());
        }
    }
}
