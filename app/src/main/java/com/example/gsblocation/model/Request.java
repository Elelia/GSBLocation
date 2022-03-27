package com.example.gsblocation.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Request {

    private Integer numDem;
    private String typeDem;
    private String dateLimite;
    private Integer num;

    public Request(Integer numDem, String typeDem, String dateLimite, Integer num) {
        this.numDem = numDem;
        this.typeDem = typeDem;
        this.dateLimite = dateLimite;
        this.num = num;
    }

    public Request (JSONObject object) {
        try {
            this.numDem = object.getInt("numDem");
            this.typeDem = object.getString("typeDem");
            this.dateLimite = object.getString("dateLimite");
            this.num = object.getInt("num");
        } catch (
        JSONException e) {
            e.printStackTrace();
        }
    }

    public Integer getNumDem() {
        return numDem;
    }

    public String getTypeDem() {
        return typeDem;
    }

    public String getDateLimite() {
        return dateLimite;
    }

    public Integer getNum() {
        return num;
    }
}
