package Magasin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String Login;
    private String Password;
    private int Solde;
    private String Profil;


    public User(String login, String password, int solde,String profil) {
        this.Login = login;
        this.Password = password;
        this.Solde = solde;
        this.Profil = profil;

    }

    public String getProfil() {
        return Profil;
    }

    public void setProfil(String profil) {
        Profil = profil;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getSolde() {
        return Solde;
    }

    public void setSolde(int solde) {
        Solde = solde;
    }

    public void addpayment(Connection con){
        System.out.println("how much cost the payment");
        Scanner sc = new Scanner(System.in);
        int value = sc.nextInt();
        if(Solde > value){
            this.Solde = Solde-value;
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("UPDATE users SET Solde = " + this.Solde + " WHERE Login = '" + this.Login + "'");
                System.out.println("User is modified");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("Votre solde est inssufisant: "+Solde);
        }
    }
    public void DeleteUser(Connection con){
        System.out.println("write the user you want to delete");
        Scanner sc = new Scanner(System.in);
        String login = sc.nextLine();

        if(this.Profil.equals("admin")){
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("delete from users where login = '"+login+"'");
                System.out.println("User "+login+" deleted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("you are not alllowed to make this action");
        }
    }
    public void CreateUser(Connection con) {
        if(this.Profil.equals("admin")){
            Scanner sc = new Scanner(System.in);
            System.out.println("write the login you want to create");
            String login = sc.nextLine();
            System.out.println("write the password you want to create");
            String password = sc.nextLine();
            System.out.println("write the solde");
            int solde = sc.nextInt();
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("insert into users (Login,Password,Solde,Profil) values('"+login+"','"+password+"','"+solde+"','user')");
                System.out.println("User inserted");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("you are not alllowed to make this action");
        }
    }


    public void GetUsers(Connection con) {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet= stmt.executeQuery("select * from users");
            while(resultSet.next()){
                users.add(new User(resultSet.getString("Login"),resultSet.getString("Password"),resultSet.getInt("Solde"),resultSet.getString("Profil")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        users.forEach((user) -> System.out.println(user.toString()));
    }

    public int MenuUser(){

            System.out.println("Welcome User");
            System.out.println("choose an action : ");
            System.out.println("1.See my sold ");
            System.out.println("2.Make a payment");
            System.out.println("0.Quit");

        int a;
        do {
            Scanner sc = new Scanner(System.in);
            a = sc.nextInt();
        }while(a<0 || a>2);

        return a;
    }

    public int MenuAdmin(){

        System.out.println("Welcome Admin");
        System.out.println("choose an action : ");
        System.out.println("11.Create a new user ");
        System.out.println("22.Delete a user ");
        System.out.println("33.Display all users");
        System.out.println("0.Quit");

        int a;
        do {
            Scanner sc = new Scanner(System.in);
            a = sc.nextInt();
        }while(a!=11 && a!=22 && a!=33  && a!=0);

        return a;
    }

    @Override
    public String toString() {
        return "User{" +
                "Login='" + Login + '\'' +
                ", Password='" + Password + '\'' +
                ", Solde=" + Solde +
                ", Profil='" + Profil + '\'' +
                '}';
    }
}
