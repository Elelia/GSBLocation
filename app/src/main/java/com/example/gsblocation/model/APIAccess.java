package com.example.gsblocation.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class APIAccess {

    public static final String QUERY_FOR_USERS = "http://192.168.0.12:8080/utilisateurs/";

    Context context;
    JSONArray response;

    public APIAccess(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(JSONArray response);
    }

    public void UserConnexion(String login, String mdp, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_USERS + login + "/" + mdp;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(login.matches("") || mdp.matches("")) {
                            volleyResponseListener.onError("Vous n'avez pas saisi votre login ou votre mot de passe");
                        }
                        else if(response.length() > 0) {
                            volleyResponseListener.onResponse(response);
                        }
                        else {
                            volleyResponseListener.onError("Mauvais login ou mot de passe");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("value", "****************", error);
                volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
        //return response;
    }

    //public List<UserReportModel> getUserById(String userNum) {

    //}
}
