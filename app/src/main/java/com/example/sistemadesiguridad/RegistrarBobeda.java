package com.example.sistemadesiguridad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistrarBobeda extends AppCompatActivity {

    private EditText nombreE, estadoE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_bobeda);

        nombreE = (EditText)findViewById(R.id.nombre);
        estadoE = (EditText)findViewById(R.id.estado);





    }


    public void crearBobeda (View v){
        String nombre = nombreE.getText().toString();
        String estado = estadoE.getText().toString();

        if(nombre.isEmpty()|| estado.isEmpty()){
            Toast.makeText(getApplicationContext(),"Debe ingresar los datos completos",Toast.LENGTH_SHORT).show();

        }else{

            //Codigo para agregar la boveda a la base de datos


            int estadoInt = Integer.parseInt(estado);
            //Realizamos la consulta a la base de datos y recivimos el objeto json de respuesta
            Response.Listener<String> respuesta = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonRespuesta = new JSONObject(response);
                        boolean ok = jsonRespuesta.getBoolean("sucess");
                        //Si la respuesta es correcta nos redirigimos al layout administrador
                        if (ok == true) {
                            Intent i = new Intent(RegistrarBobeda.this, administrador.class);

                            Bundle bundle = getIntent().getExtras(); //obtener información para enviar en el intent a la ventana administrador
                            final String usuarioPrev, contraseñaPrev, cedulaPrev;
                            usuarioPrev = bundle.getString("nombre_usuario");
                            contraseñaPrev = bundle.getString("contraseña");
                            cedulaPrev = bundle.getString("cedula");

                            i.putExtra("nombre_usuario",usuarioPrev); //envío de informacion a la sgte activity
                            i.putExtra("contraseña",contraseñaPrev);
                            i.putExtra("cedula",cedulaPrev);


                            RegistrarBobeda.this.startActivity(i);
                            RegistrarBobeda.this.finish();
                            Toast.makeText(getApplicationContext(),"Bóveda creada",Toast.LENGTH_SHORT).show();

                        }//Si esto no ocurre se envia el mensaje de alerta de fallo de registro
                        else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(RegistrarBobeda.this);
                            alerta.setMessage("Fallo en el registro").setNegativeButton("Reintentar", null).create().show();
                        }
                    } catch (JSONException e) {
                        e.getMessage();
                    }
                }

            };


                RegistroBobeda r = new RegistroBobeda(nombre, estadoInt, respuesta);
                RequestQueue cola = Volley.newRequestQueue(RegistrarBobeda.this);
                cola.add(r);


        }

    }
}
