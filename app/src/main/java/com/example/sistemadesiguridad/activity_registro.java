package com.example.sistemadesiguridad;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class activity_registro extends AppCompatActivity {

    Spinner opciones;
    Spinner bobedas;
    ArrayList<String> bobedasSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Obtenemos los edittext del
        final EditText usuarioN = (EditText) findViewById(R.id.usuarioE);
        final EditText correoN = (EditText) findViewById(R.id.correo);
        final EditText DNIN = (EditText) findViewById(R.id.DNI);
        final EditText contraseñaN = (EditText) findViewById(R.id.txtcontraseña);
        Button btnRegistro = (Button) findViewById(R.id.crear);
        opciones =(Spinner)findViewById(R.id.spinner2);
        bobedas = (Spinner)findViewById(R.id.bobedas);

        Bundle bundle = getIntent().getExtras(); //obtener información para enviar en el intent a la ventana administrador
        final String usuarioPrev, contraseñaPrev, cedulaPrev;
        usuarioPrev = bundle.getString("nombre_usuario");
        contraseñaPrev = bundle.getString("contraseña");
        cedulaPrev = bundle.getString("cedula");

        //Hay que obtener de la base de datos los nombres de las bobedas
        final ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(this,R.array.bobedas,android.R.layout.simple_spinner_item);
        bobedas.setAdapter(spinAdapter);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);
        opciones.setAdapter(adapter);

        //si el usuario es gerente se muestra el spinner de asignacion de bobeda
        //si el usuario es un administrador no se asigna ninguna bobeda
        opciones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).equals("gerente")){
                    bobedas.setVisibility(View.VISIBLE);
                }

                if(adapterView.getItemAtPosition(i).equals("administrador")){
                    bobedas.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Obtenemos los datos a enviar para el registro del usuario en la base de datos
                String usuario = usuarioN.getText().toString();
                String correo = correoN.getText().toString();
                String DNI = DNIN.getText().toString();
                String contraseña = contraseñaN.getText().toString();
                String tipo = opciones.getSelectedItem().toString();


                if (usuario.isEmpty() ||correo.isEmpty()||DNI.isEmpty()||contraseña.isEmpty()||tipo.isEmpty() ) {
                    Toast.makeText(getApplicationContext(),"Debe ingresar todos los campos",Toast.LENGTH_SHORT).show();

                }else{

                    int DNIint = Integer.parseInt(DNI);
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
                                i.putExtra("nombre_usuario",usuarioPrev);
                                i.putExtra("contraseña",contraseñaPrev);
                                i.putExtra("cedula",cedulaPrev);
                                activity_registro.this.startActivity(i);
                                activity_registro.this.finish();
                                Toast.makeText(getApplicationContext(),"Usuario creado",Toast.LENGTH_SHORT).show();

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

                if(tipo.compareTo("gerente")==0){
                    String nombreBobeda = bobedas.getSelectedItem().toString();
                    RegistroGerente r = new RegistroGerente(usuario, correo, DNIint, contraseña, nombreBobeda, tipo, respuesta);
                    RequestQueue cola = Volley.newRequestQueue(activity_registro.this);
                    cola.add(r);

                }else{
                    Registro r = new Registro(usuario, correo, DNIint, contraseña, tipo, respuesta);
                    RequestQueue cola = Volley.newRequestQueue(activity_registro.this);
                    cola.add(r);
                }

            }
        }
        });
    }

    //public void selección



}
