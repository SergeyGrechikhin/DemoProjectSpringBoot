package com.sergey.demoprojectspringboot.front.frame;

import com.sergey.demoprojectspringboot.front.api.ApiClient;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainFrame extends JFrame {
    private final ApiClient api;
    private final JTextArea log = new JTextArea(12, 50);

    public MainFrame(ApiClient api) {
        super("Company Manager");
        this.api = api;
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try{
            ApiClient.UserInfo me = api.infoAboutMe();
            initMe(me);
        } catch (IOException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        //Menu


        JMenuBar bar = new JMenuBar();
        JMenu mUser = new JMenu("User");
        JMenuItem miLogout = new JMenuItem("Logout");
        miLogout.addActionListener(e -> logout());
        mUser.add(miLogout);
        bar.add(mUser);

        JMenu mAdmin = new JMenu("Admin");
        JMenuItem miLoadEmployees = new JMenuItem("Load employees");
        miLoadEmployees.addActionListener(e -> getAllEmployees());
        mAdmin.add(miLoadEmployees);
        bar.add(mAdmin);

        setJMenuBar(bar);

        log.setEditable(false);
        add(new JScrollPane(log), BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }

    private void initMe(ApiClient.UserInfo me){
        JLabel userLabel = new JLabel("User : " + me.email + " My Role : " + me.role );
        add(userLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();

        if ("ADMIN".equals(me.role)){
            JButton adminButton = new JButton("Employees");
            panel.add(adminButton);
        }

        JButton tasksButton = new JButton("Tasks");
        panel.add(tasksButton);

        add(panel, BorderLayout.CENTER);
    }




    private void logout() {
        JOptionPane.showMessageDialog(this, "Logged out (token cleared)");
        // обнулить токен в ApiClient
        dispose();
    }

    private void getAllEmployees() {
        try {

            String json = api.getJson("/api/admin/find/employeesAll");
            log.setText(json);
        } catch (IOException ex) {
            log.setText("Error: " + ex.getMessage());
        }
    }
}
