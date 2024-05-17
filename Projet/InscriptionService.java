package Projet;

import java.sql.SQLException;

public class InscriptionService {
    private UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    public String inscrireUtilisateur(Utilisateur utilisateur) throws SQLException {
        if (utilisateurDAO.verifierExistenceUtilisateur(utilisateur.getUtilmail())) {
            return "Erreur: L'utilisateur existe déjà.";
        }
        if (utilisateurDAO.inscrireUtilisateur(utilisateur)) {
            return "Inscription réussie!";
        } else {
            return "Erreur lors de l'inscription.";
        }
    }
}

