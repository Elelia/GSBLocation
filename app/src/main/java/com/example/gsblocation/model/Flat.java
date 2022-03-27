package com.example.gsblocation.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class Flat {
    private Integer num;
    private String type;
    private Float prixLoc;
    private Float prixCharg;
    private String rue;
    private String arrondissement;
    private Integer etage;
    private String ascenseur;
    private Integer nbPieces;
    private String taille;
    private String preavis;

    public Flat(Integer num, String type, Float prixLoc, Float prixCharg, String rue, String arrondissement, Integer etage, String ascenseur, Integer nbPieces, String taille, String preavis) {
        this.num = num;
        this.type = type;
        this.prixLoc = prixLoc;
        this.prixCharg = prixCharg;
        this.rue = rue;
        this.arrondissement = arrondissement;
        this.etage = etage;
        this.ascenseur = ascenseur;
        this.nbPieces = nbPieces;
        this.taille = taille;
        this.preavis = preavis;
    }

    public Flat (JSONObject object) {
        try {
            this.num = object.getInt("numAppart");
            this.type = object.getString("typeAppart");
            this.prixLoc = BigDecimal.valueOf(object.getDouble("prixLoc")).floatValue();
            this.prixCharg = BigDecimal.valueOf(object.getDouble("prixCharg")).floatValue();
            this.rue = object.getString("rue");
            this.arrondissement = object.getString("arrondissement");
            this.etage = object.getInt("etage");
            this.ascenseur = object.getString("ascenseur");
            this.nbPieces = object.getInt("nbPieces");
            this.taille = object.getString("taille");
            this.preavis = object.getString("preavis");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Integer getNum() {
        return num;
    }

    public String getType() {
        return type;
    }

    public Float getPrixLoc() {
        return prixLoc;
    }

    public Float getPrixCharg() {
        return prixCharg;
    }

    public String getRue() {
        return rue;
    }

    public String getArrondissement() {
        return arrondissement;
    }

    public Integer getEtage() {
        return etage;
    }

    public String getAscenseur() {
        return ascenseur;
    }

    public Integer getNbPieces() {
        return nbPieces;
    }

    public String getTaille() {
        return taille;
    }

    public String getPreavis() {
        return preavis;
    }
}
