package com.example.sistemadesiguridad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuarios extends AppCompatActivity {

    Button regresar;
    ArrayList<String> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        ListView lista = findViewById(R.id.lista);
        ConsultarDatos();
        Log.i("probemos a ver",lista.toString());
        Log.i("y luego  a ver",datos.toString());


        Log.i("probemos a ver",lista.toString());

        regresar = (Button)findViewById(R.id.regresar);

        //generamos un objeto de clase adaptador y enviamos como parametro datos y datosImg
        lista.setAdapter(new Adaptador(this, datos));

    }

    public void volver(View view) {
        Intent i = new Intent(this, administrador.class );
        startActivity(i);
    }

    public void ConsultarDatos(){
        //Toast.makeText(getApplicationContext(), ""+URL, Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, "https://odinpst.000webhostapp.com/consultar.php",null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("datos");
                            Log.i("pruebaaa",jsonArray.toString());



                            for (int i = 0; i < jsonArray.length();i++){
                                ArrayList<String> lista = new ArrayList<>();
                                JSONObject word = jsonArray.getJSONObject(i);

                                Log.i("Palabras",word.toString());

                                String cedula = word.getString("cedula");
                                String nombre = word.getString("nombre_usuario");
                                String tipo = word.getString("tipo");

                                Log.i("contra",cedula.toString());
                                Log.i("usuaer",nombre.toString());

                                lista.add(cedula);
                                lista.add(nombre);
                                lista.add(tipo);

                                Log.i("PRIMEROOOO",jsonArray.getString(0));
                                Log.i("myInfoTag",jsonArray.getString(1));

                                Log.i("AQUIIIIIIIIIIIII",datos.get(i).substring(0));
                                datos.add(lista.toString());

                            }

                        Log.i("lista",datos.toString());

                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), e +"",Toast.LENGTH_LONG).show();
                        }


                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley", "ERROR");
                        Toast.makeText(getApplicationContext(), error +"",Toast.LENGTH_LONG).show();

                    }
                }


        );
        queue.add(jsonObjectRequest);
    }


}

