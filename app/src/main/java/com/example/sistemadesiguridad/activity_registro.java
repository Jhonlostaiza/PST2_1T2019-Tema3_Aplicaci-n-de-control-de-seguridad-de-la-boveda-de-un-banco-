package com.example.sistemadesiguridad;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
public class activity_registro extends AppCompatActivity {

    Spinner opciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final EditText usuarioN = (EditText) findViewById(R.id.usuarioE);
        final EditText correoN = (EditText) findViewById(R.id.correo);
        final EditText DNIN = (EditText) findViewById(R.id.DNI);
        final EditText contraseñaN = (EditText) findViewById(R.id.txtcontraseña);
        Button btnRegistro = (Button) findViewById(R.id.crear);

        opciones =(Spinner)findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtenemos los datos a enviar para el registro del usuario en la base de datos
                String usuario = usuarioN.getText().toString();
                String correo = correoN.getText().toString();
                int DNI = Integer.parseInt(DNIN.getText().toString());
                String contraseña = contraseñaN.getText().toString();
                String tipo = opciones.getSelectedItem().toString();
                //Realizamos la consulta a la base de datos y recivimos el objeto json de respuesta
                Response.Listener<String> respuesta = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonRespuesta = new JSONObject(response);
                            boolean ok = jsonRespuesta.getBoolean("sucess");
                            //Si la respuesta es correcta nos redirigimos al layout administrador
                            if (ok == true) {
                                Intent i = new Intent(activity_registro.this, administrador.class);
                                activity_registro.this.startActivity(i);
                                activity_registro.this.finish();

                            }//Si esto no ocurre se envia el mensaje de alerta de fallo de registro
                            else {
                                AlertDialog.Builder alerta = new AlertDialog.Builder(activity_registro.this);
                                alerta.setMessage("Fallo en el registro").setNegativeButton("Reintentar", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.getMessage();
                        }
                    }

                };
                Registro r = new Registro(usuario, correo, DNI, contraseña,tipo, respuesta);
                RequestQueue cola= Volley.newRequestQueue(activity_registro.this);
                cola.add(r);
            }
        });
    }
    public void regresar (View view){
        finish();
    }
}
