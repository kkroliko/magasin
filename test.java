import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Utilisateur {
    private int id;
    private String login;
    private String password;
    private String email;


    // Méthode pour s'authentifier
    public boolean authentifier(String login, String password) {
        // Logique pour vérifier l'authentification
        return this.login.equals(login) && this.password.equals(password);
    }
}


public class Client extends Utilisateur {

    private List<Commande> commandes = new ArrayList<>();


    public void passerCommande(List<Produit> produits, List<Integer> quantites) {
        // Logique pour passer une commande
    }
}


public class Produit {
    private int id;
    private String designation;
    private double prix;
    private int quantiteEnStock;

    k
    public void mettreAJourStock(int quantiteVendue) {
        this.quantiteEnStock -= quantiteVendue;
    }
}


public class Commande {
    private int id;
    private Client client;
    private Date date;
    private double total;
    private String description;
    private List<LigneCommande> lignesCommande = new ArrayList<>();


    public void ajouterLigneCommande(Produit produit, int quantite) {
        LigneCommande ligneCommande = new LigneCommande(produit, quantite);
        lignesCommande.add(ligneCommande);
        this.total += produit.getPrix() * quantite;
    }
}


public class LigneCommande {
    private Produit produit;
    private int quantite;


    public double calculerTotal() {
        return produit.getPrix() * quantite;
    }
}