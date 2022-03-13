package com.example.gsblocation.model;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class User {
    //properties
    User unUser;
    protected Integer num;
    protected String login;
    protected String mdp;
    protected String nom;
    protected String prenom;
    protected String adresse;
    protected String codeVille;
    protected String telephone;
    protected String type;

    public User(Integer num, String login, String mdp, String nom, String prenom, String adresse, String codeVille, String telephone, String type) {
        this.num = num;
        this.login = login;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.codeVille = codeVille;
        this.telephone = telephone;
        this.type = type;
    }

    protected User(User unUser) {
        this.unUser = unUser;
    }

    public Integer getNum() {
        return num;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCodeVille() {
        return codeVille;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getType() {
        return type;
    }

    public void testResult() {
        this.num = 1;
        this.nom = "Bloupi";
        this.prenom = "Truffe";
        this.adresse = "26 je suis fatiguée";
        this.codeVille = "60222";
        this.telephone = "50505050";
        this.type = "c";
    }

    /**
     * conversion de l'utilisateur au format JSONArray
     * @return
     */
    public JSONArray convertToJSON() {
        List laListe=new ArrayList();
        laListe.add(num);
        laListe.add(login);
        laListe.add(mdp);
        laListe.add(nom);
        laListe.add(prenom);
        laListe.add(adresse);
        laListe.add(codeVille);
        laListe.add(telephone);
        laListe.add(type);
        return new JSONArray(laListe);
    }
}
