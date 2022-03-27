package com.example.gsblocation.model;

public class Tenant extends User {
    private String rib;
    private String banque;
    private String rueBanque;
    private String codeVB;
    private String telBanque;

    public Tenant(User unUser, String rib, String banque, String rueBanque, String codeVB, String telBanque) {
        super(unUser);
        this.rib = rib;
        this.banque = banque;
        this.rueBanque = rueBanque;
        this.codeVB = codeVB;
        this.telBanque = telBanque;
    }

    public String getRib() {
        return rib;
    }

    public String getBanque() {
        return banque;
    }

    public String getRueBanque() {
        return rueBanque;
    }

    public String getCodeVB() {
        return codeVB;
    }

    public String getTelBanque() {
        return telBanque;
    }
}
