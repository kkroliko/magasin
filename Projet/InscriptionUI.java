package Projet;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InscriptionUI extends JFrame {
    private JTextField emailField;
    private JTextField loginField;
    private JPasswordField passwordField;
    private JTextField nomField;
    private JTextField prenomField;
    private JTextField telField;
    private JTextField adresseField;

    private InscriptionService inscriptionService = new InscriptionService();

    public InscriptionUI() {
        setTitle("Inscription");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        emailField = new JTextField();
        loginField = new JTextField();
        passwordField = new JPasswordField();
        nomField = new JTextField();
        prenomField = new JTextField();
        telField = new JTextField();
        adresseField = new JTextField();

        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Login:"));
        panel.add(loginField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Nom:"));
        panel.add(nomField);
        panel.add(new JLabel("Prenom:"));
        panel.add(prenomField);
        panel.add(new JLabel("Telephone:"));
        panel.add(telField);
        panel.add(new JLabel("Adresse:"));
        panel.add(adresseField);


        JButton submitButton = new JButton("Inscription");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setUtilmail(emailField.getText());
                utilisateur.setUtillogin(loginField.getText());
                utilisateur.setUtilpass(new String(passwordField.getPassword()));
                utilisateur.setUtilnom(nomField.getText());
                utilisateur.setUtilprenom(prenomField.getText());
                utilisateur.setUtiltel(telField.getText());
                utilisateur.setUtiladresse(adresseField.getText());

                String result = null;
                try {
                    result = inscriptionService.inscrireUtilisateur(utilisateur);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                JOptionPane.showMessageDialog(null, result);
            }
        });

        panel.add(submitButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InscriptionUI().setVisible(true);
            }
        });
    }
}

