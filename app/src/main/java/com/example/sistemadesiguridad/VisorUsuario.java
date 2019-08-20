package com.example.sistemadesiguridad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sistemadesiguridad.admin.editar_admin;

import org.json.JSONException;
import org.json.JSONObject;

import static java.security.AccessController.getContext;

public class VisorUsuario extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_usuario);

        ProgressDialog pDialog;
        StringRequest stringRequest;

        //declaramos los elementos y obtenemos la informacion que se esta obteniendo
        final EditText usuario = (EditText) findViewById(R.id.nombre_usuario);
        final EditText cedula = (EditText) findViewById(R.id.txced);
        final EditText correo = (EditText) findViewById(R.id.txcorreo);
        final EditText contraseña = (EditText) findViewById(R.id.txcontra);
        final EditText tipo = (EditText) findViewById(R.id.txtipo);
        //ImageView img = findViewById(R.id.imguser);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null) {
            cedula.setText(b.getString("CEDULA"));
            tipo.setText(b.getString("TIPO"));
            usuario.setText(b.getString("USER"));
            correo.setText(b.getString("CORREO"));
            contraseña.setText(b.getString("CONTRA"));
            //img.setImageResource(b.getInt("IMG"));
        }

        Button btnEditar = (Button) findViewById(R.id.editar_usuarios);

        btnEditar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String nombre_usuario = usuario.getText().toString();
                Log.d("REVISA1",nombre_usuario);
                String contra = contraseña.getText().toString();
                Log.d("REVISA2",contra);
                String email = correo.getText().toString();
                Log.d("3",email);
                String tip = tipo.getText().toString();
                String ced = cedula.getText().toString();
                Response.Listener<String> response = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);
                            boolean ok = jsonresponse.getBoolean("listo");
                            if (ok == true) {

                                Intent a = new Intent(VisorUsuario.this, Usuarios.class);
                                VisorUsuario.this.startActivity(a);

                            } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(VisorUsuario.this);
                                alerta.setMessage("Fallo al editar datos").setNegativeButton("Reintentar", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.getMessage();
                        }
                    }

                };
                editar_admin a = new editar_admin(contra, email, ced, nombre_usuario, tip, response);
                Log.d("REVISA",a.toString());
                RequestQueue cola= Volley.newRequestQueue(VisorUsuario.this);
                cola.add(a);
                Intent b = new Intent(VisorUsuario.this, Usuarios.class);
                VisorUsuario.this.startActivity(b);

            }
        });
    }




    public void volver(View view) {
        Intent i = new Intent(this, Usuarios.class);
        startActivity(i);
    }

    }










