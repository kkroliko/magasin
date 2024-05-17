package Projet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtilisateurDAO {
    public boolean verifierExistenceUtilisateur(String utilmail) throws SQLException {
        String query = "SELECT COUNT(*) FROM utilisateur WHERE utilmail = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, utilmail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la vérification de l'existence de l'utilisateur: " + e.getMessage());
            throw e;
        }
        return false;
    }

    public boolean inscrireUtilisateur(Utilisateur utilisateur) throws SQLException {
        if (verifierExistenceUtilisateur(utilisateur.getUtilmail())) {
            return false;
        }
        String query = "INSERT INTO utilisateur (privid, villeid, utilmail, utillogin, utilpass, utilsexe, utilnom, utilprenom, utiltel, utilfax, utiladresse, utilcodepostal, utilCIN, utilremarque) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, utilisateur.getPrivid());
            statement.setInt(2, utilisateur.getVilleid());
            statement.setString(3, utilisateur.getUtilmail());
            statement.setString(4, utilisateur.getUtillogin());
            statement.setString(5, utilisateur.getUtilpass());
            statement.setString(6, utilisateur.getUtilsexe());
            statement.setString(7, utilisateur.getUtilnom());
            statement.setString(8, utilisateur.getUtilprenom());
            statement.setString(9, utilisateur.getUtiltel());
            statement.setString(10, utilisateur.getUtilfax());
            statement.setString(11, utilisateur.getUtiladresse());
            statement.setString(12, utilisateur.getUtilcodepostal());
            statement.setString(13, utilisateur.getUtilCIN());
            statement.setString(14, utilisateur.getUtilremarque());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Erreur lors de l'inscription de l'utilisateur: " + e.getMessage());
            throw e;
        }
    }
}


