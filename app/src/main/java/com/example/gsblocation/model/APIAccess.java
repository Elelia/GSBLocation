package com.example.gsblocation.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class APIAccess {

    public static final String QUERY_FOR_USERS = "http://192.168.1.72:8080/utilisateurs/";
    public static final String QUERY_FOR_FLATS = "http://192.168.1.72:8080/appartements/";
    public static final String QUERY_FOR_DISTRICTS = "http://192.168.1.72:8080/arrondissement/";
    public static final String QUERY_FOR_REQUESTS = "http://192.168.1.72:8080/demandes/";

    Context context;
    JSONArray response;

    public APIAccess(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(JSONArray response);
    }

    //vérifie si la connexion utilisateur peut se faire ou non en fonction des données saisies
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
    }

    //retrouve tous les appartements présents en base de données sous forme de JSONArray
    public void ReturnAllFlats(VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_FLATS;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("value", "****************", error);
                volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //retrouve tous les arrondissements de la base de données sous forme de JSONArray
    public void ReturnAllDistricts(VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_DISTRICTS;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("value", "****************", error);
                volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //retrouve toutes les demandes de la base de données
    public void ReturnAllRequests(VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_REQUESTS;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("value", "****************", error);
                volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //retrouve les demandes en fonction du numéro du client
    public void ReturnRequestsByNumC(Integer num, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_REQUESTS + num;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("value", "****************", error);
                volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //insert into requests
    public void sendDistrictRequest(String type, String dateLimite, Integer num, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_REQUESTS + "?typeDem=" + type +"&dateLimite=" + dateLimite +"&num=" + num;

        JSONObject requestObject = new JSONObject();
        JSONArray requestArray = new JSONArray();
        try {
            requestObject.put("typeDem",type);
            requestObject.put("dateLimite",dateLimite);
            requestObject.put("num",num);
            requestArray.put(requestObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, requestArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("send request","***********************"+response);
                        //volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "****************", error);
                //volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //retourne la dernière demande
    public void returnLastRequest(VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_REQUESTS + "last/";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("value", "****************", error);
                volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //créer une nouvelle ligne dans la table concerner
    public void sendConcernRequest(Integer numD, Integer numA, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_REQUESTS + "concerner/?numDem=" + numD +"&arrondisseDem=" + numA;

        JSONObject concernObject = new JSONObject();
        JSONArray concernArray = new JSONArray();
        try {
            concernObject.put("numDem",numD);
            concernObject.put("arrondisseDem",numA);
            concernArray.put(concernObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, concernArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("send request","***********************"+response);
                        //volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "****************", error);
                //volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //retourne les requests en fonction du type d'appartement
    public void returnRequestsByType(String type, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_REQUESTS + type;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(type.matches("")) {
                            volleyResponseListener.onError("Problème avec les demandes");
                        }
                        else if(response.length() > 0) {
                            volleyResponseListener.onResponse(response);
                        }
                        else {
                            volleyResponseListener.onError("Problème avec les demandes");
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
    }

    //retourne les appartements en fonction de l'id du propriétaire
    public void ReturnFlatsByIdProp(Integer id, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_FLATS +"posseder/" + id;

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("value", "****************", error);
                volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    //update informations of user qui insère des valeurs null en base de données ahah
    public void UpdateUserInfo(Integer num, String nom, String prenom, String adresse, String ville, String tel, VolleyResponseListener volleyResponseListener) {
        try {
            String rightAdresse = URLEncoder.encode(adresse, "utf-8");
            String rightVille = URLEncoder.encode(ville, "utf-8");
            String rightTel = URLEncoder.encode(tel, "utf-8");
            String url = QUERY_FOR_USERS + "update/" + num + "?nom=" + nom + "&prenom=" + prenom + "&adresse=" + rightAdresse + "&codeVille=" + rightVille + "&telephone=" + rightTel;

            JSONObject userObject = new JSONObject();
            JSONArray userArray = new JSONArray();
            try {
                userObject.put("nom",nom);
                userObject.put("prenom",prenom);
                userObject.put("adresse",adresse);
                userObject.put("codeVille",ville);
                userObject.put("telephone",tel);
                userArray.put(userObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("Update ok", "**************" + userArray);

            JsonArrayRequest request = new JsonArrayRequest(Request.Method.PUT, url, userArray,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.d("update user","***********************"+response);
                            //volleyResponseListener.onResponse(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("value", "****************", error);
                    //volleyResponseListener.onError("Not working");
                }
            });
            MySingleton.getInstance(context).addToRequestQueue(request);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    //sans num ça ne marche pas il n'y a pas d'auto increment
    public void sendNewFlat(Integer num, String type, String prixLoc, String prixChr, String rue, String arrondissement, String etage, String ascenseur, String pieces, String taille, VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_FLATS + "?numAppart=" + num +"&typeAppart=" + type +"&prixLoc=" + prixLoc + "&prixCharg=" + prixChr + "&rue=" + rue + "&arrondissement=" + arrondissement + "&etage=" + etage + "&ascenseur=" + ascenseur + "&nbPieces=" + pieces + "&taille=" + taille;

        JSONObject requestObject = new JSONObject();
        JSONArray requestArray = new JSONArray();
        try {
            requestObject.put("numAppart",num);
            requestObject.put("typeAppart",type);
            requestObject.put("prixLoc",prixLoc);
            requestObject.put("prixCharg",prixChr);
            requestObject.put("rue",rue);
            requestObject.put("arrondissement",arrondissement);
            requestObject.put("etage",etage);
            requestObject.put("ascenseur",ascenseur);
            requestObject.put("nbPieces",pieces);
            requestObject.put("taille",taille);
            requestArray.put(requestObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, requestArray,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("add flat","***********************"+response);
                        //volleyResponseListener.onResponse(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error", "****************", error);
                //volleyResponseListener.onError("Not working");
            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }
}
