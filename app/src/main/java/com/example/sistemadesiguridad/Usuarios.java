package com.example.sistemadesiguridad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemadesiguridad.Entidades.usuario_datos;
import com.example.sistemadesiguridad.adaptadores.user_adapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class Usuarios extends AppCompatActivity {

    Button regresar;
    RecyclerView rv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        regresar = (Button)findViewById(R.id.regresar);
        rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        String consulta = "https://odinpst.000webhostapp.com/consultar.php";
        ConsultarDatos(consulta);


    }

    public void volver(View view) {
        Intent i = new Intent(this, administrador.class );
        startActivity(i);
    }

    public void ConsultarDatos(String URL){
        Toast.makeText(getApplicationContext(), ""+URL, Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][",",");
                if (response.length()>0){
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson",""+ja.length());
                        CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    public void CargarListView(JSONArray ja){

        ArrayList<String> lista = new ArrayList<>();
        ArrayList<usuario_datos> arreglo = new ArrayList<usuario_datos>();

        user_adapter adaptador = new user_adapter(arreglo);
        rv.setAdapter(adaptador);

    }

}

