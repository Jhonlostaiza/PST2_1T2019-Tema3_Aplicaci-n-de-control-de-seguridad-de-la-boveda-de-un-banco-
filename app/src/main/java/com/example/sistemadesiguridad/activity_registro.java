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
public class activity_registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final EditText usuarioN = (EditText) findViewById(R.id.usuarioE);
        final EditText correoN = (EditText) findViewById(R.id.correo);
        final EditText DNIN = (EditText) findViewById(R.id.DNI);
        final EditText contraseñaN = (EditText) findViewById(R.id.contraseña);
        final Button btnRegistro = (Button) findViewById(R.id.crear);
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = usuarioN.getText().toString();
                String correo = correoN.getText().toString();
                int DNI = Integer.parseInt(DNIN.getText().toString());
                String contraseña = contraseñaN.getText().toString();
                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("sucess");
                            if (ok == true) {
                                Intent i = new Intent(activity_registro.this, MainActivity.class);
                                activity_registro.this.startActivity(i);
                             } else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(activity_registro.this);
                                alerta.setMessage("Fallo en el registro").setNegativeButton("Reintentar", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.getMessage();
                        }
                    }

                };
                Registro r = new Registro(usuario, correo, DNI, contraseña, respuesta);
                RequestQueue cola= Volley.newRequestQueue(activity_registro.this);
                cola.add(r);
            }
        });
    }
    public void regresar (View view){
        finish();
    }
}
