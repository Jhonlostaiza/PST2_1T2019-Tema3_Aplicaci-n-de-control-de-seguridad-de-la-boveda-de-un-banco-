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

public class activity_editar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        final EditText usuarioE = (EditText) findViewById(R.id.usuarioE);
        final EditText contrasenaE = (EditText) findViewById(R.id.contrasenaE);
        Button btnEditar = (Button) findViewById(R.id.editarbtn);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre_usuario = usuarioE.getText().toString();
                String contraseña = contrasenaE.getText().toString();
                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("ok");
                            if (ok == true) {
                                Intent i = new Intent(activity_editar.this, activity_bobeda.class);
                                activity_editar.this.startActivity(i);
                                activity_editar.this.finish();


                            } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(activity_editar.this);
                                alerta.setMessage("Fallo al editar datos").setNegativeButton("Reintentar", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.getMessage();
                        }
                    }

                };
                Actualizar a = new Actualizar(nombre_usuario, contraseña, respuesta);
                RequestQueue cola= Volley.newRequestQueue(activity_editar.this);
                cola.add(a);
            }
        });
    }

    public void Regresar(View view) {
        Intent i = new Intent(this, activity_bobeda.class );
        startActivity(i);
    }
}




