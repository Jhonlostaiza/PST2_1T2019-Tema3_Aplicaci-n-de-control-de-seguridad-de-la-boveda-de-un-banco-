package com.example.sistemadesiguridad;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemadesiguridad.Entidades.user_datos;
import com.example.sistemadesiguridad.adaptadores.user_adaptador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuarios extends AppCompatActivity {

    private String URLstring = "https://odinpst.000webhostapp.com/consultar.php";
    private static ProgressDialog mProgressDialog;
    private ListView listView;
    ArrayList<user_datos> userdatosArrayList;
    private user_adaptador userAdaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        Button volver = (Button) findViewById(R.id.btnvolver);
        listView = findViewById(R.id.lvusuarios);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent visorDetalles = new Intent(view.getContext(), VisorUsuario.class);
                //agregamos los parametros extras, titulo, descpricion, imagen
                //obtenemos la posicion de esos datos por medio de "position"

                user_datos datos = new user_datos();

                visorDetalles.putExtra("CEDULA", userdatosArrayList.get(position).getCedula());
                visorDetalles.putExtra("TIPO", userdatosArrayList.get(position).getTipo());
                visorDetalles.putExtra("CORREO", userdatosArrayList.get(position).getCorreo());
                visorDetalles.putExtra("CONTRA", userdatosArrayList.get(position).getContraseña());
                visorDetalles.putExtra("USER", userdatosArrayList.get(position).getNombre_usuario());
                //visorDetalles.putExtra("IMG", datosImg[position]);
                startActivity(visorDetalles);
            }
        });

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

                               userdatosArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("datos");


                                for (int i = 0; i < dataArray.length(); i++) {

                                    user_datos datos = new user_datos();
                                    JSONObject dataobj = dataArray.getJSONObject(i);


                                    datos.setCedula(dataobj.getString("cedula"));
                                    datos.setCorreo(dataobj.getString("correo"));
                                    datos.setNombre_usuario(dataobj.getString("nombre_usuario"));
                                    datos.setTipo(dataobj.getString("tipo"));
                                    datos.setContraseña(dataobj.getString("contraseña"));
                                    //datos.setImgURL(dataobj.getString("imgURL"));

                                    userdatosArrayList.add(datos);

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
                        //muestra el error si ocurre
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

    private void setupListview(){
        removeSimpleProgressDialog();  //elimina el dialogo de proceso
        userAdaptador = new user_adaptador(this, userdatosArrayList);
        listView.setAdapter(userAdaptador);
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

    public void volver(View view) {
        Intent i = new Intent(this, administrador.class);
        startActivity(i);
    }

}


