package com.example.sistemadesiguridad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button ingresar = (Button) findViewById(R.id.ingresar);
        final EditText usuarioN = (EditText) findViewById(R.id.username);
        final EditText claveN = (EditText) findViewById(R.id.clave);
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String usuario = usuarioN.getText().toString();
                final String clave = claveN.getText().toString();
                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("sucess");
                            String tipo=jsonRespuesta.getString("tipo");

                            if (ok == true) {
                                if(tipo.compareTo("gerente")==0) {
                                    String nombre = jsonRespuesta.getString("nombre_usuario");
                                    Intent bobeda = new Intent(MainActivity.this, activity_bobeda.class);
                                    //bobeda.putExtra("usuario",nombre);
                                    MainActivity.this.startActivity(bobeda);
                                    MainActivity.this.finish();
                                }if(tipo.compareTo("administrador")==0){
                                    String nombre = jsonRespuesta.getString("nombre_usuario");
                                    Intent administrador = new Intent(MainActivity.this, administrador.class);
                                    //bobeda.putExtra("usuario",nombre);
                                    MainActivity.this.startActivity(administrador);
                                    MainActivity.this.finish();
                                }
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                                alerta.setMessage(tipo).setNegativeButton("Reintentar", null).create().show();


                            } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);
                                alerta.setMessage("Fallo en el login").setNegativeButton("Reintentar", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.getMessage();
                        }
                    }
                };
                Login r = new Login(usuario, clave, respuesta);
                RequestQueue cola = Volley.newRequestQueue(MainActivity.this);
                cola.add(r);
            }
        });
    }


    public void Registrarse(View view) {
        Intent i = new Intent(this, activity_registro.class);
        startActivity(i);
    }
}