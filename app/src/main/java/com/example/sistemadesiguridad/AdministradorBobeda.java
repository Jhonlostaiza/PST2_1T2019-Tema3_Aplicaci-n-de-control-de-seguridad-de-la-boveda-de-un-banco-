package com.example.sistemadesiguridad;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemadesiguridad.Entidades.bobeda_datos;
import com.example.sistemadesiguridad.adaptadores.bobeda_adaptador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdministradorBobeda extends AppCompatActivity {

    private String URLstring = "https://odinpst.000webhostapp.com/bobeda.php";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<bobeda_datos> bobedaArrayList;
    private bobeda_adaptador BobedaAdaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador_bobeda);
        listView = findViewById(R.id.lvbobeda);

        retrieveJSON();

    }

    private void retrieveJSON() {

        showSimpleProgressDialog(this, "Loading...","Espere un momento...",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("success").equals("1")){

                                bobedaArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("datos");


                                for (int i = 0; i < dataArray.length(); i++) {

                                    bobeda_datos datos = new bobeda_datos();
                                    JSONObject dataobj = dataArray.getJSONObject(i);


                                    datos.setId(Integer.parseInt(dataobj.getString("id_bobeda")));
                                    datos.setNombre(dataobj.getString("nombre_bobeda"));
                                    datos.setEstado(Integer.parseInt(dataobj.getString("estado")));
                                    //datos.setImgURL(dataobj.getString("imgURL"));

                                    bobedaArrayList.add(datos);

                                }

                                setupListview();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //presenta el error si ocurre
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // respuesta queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void setupListview(){
        removeSimpleProgressDialog();  //will remove progress dialog
        BobedaAdaptador = new bobeda_adaptador(this, bobedaArrayList);
        listView.setAdapter(BobedaAdaptador);
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


