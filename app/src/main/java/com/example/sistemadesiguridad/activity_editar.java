package com.example.sistemadesiguridad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class activity_editar extends AppCompatActivity {
    private EditText usuarioE,contrasenaE;
    private String usuarioPrev,contraseñaPrev,cedula,id_bobeda,estado,tipo;
    private Button btnEditar;
    private ProgressBar pbr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        Bundle bundle = getIntent().getExtras(); //obtener información previa
        usuarioPrev =bundle.getString("nombre_usuario"); //datos previos
        contraseñaPrev=bundle.getString("contraseña"); //datos previos
        cedula = bundle.getString("cedula");
        tipo = bundle.getString("tipo");
        if (tipo.compareTo("gerente")==0){ //para gerente llegan más datos
            id_bobeda = bundle.getString("id_bobeda");
            estado = bundle.getString("estado");
        }


        usuarioE = (EditText) findViewById(R.id.nombre);
        contrasenaE = (EditText) findViewById(R.id.contrasenaE);
        btnEditar = (Button) findViewById(R.id.editarbtn);
        pbr = (ProgressBar) findViewById(R.id.progressBar3);

    }

    public void editar(View view) {
        String nombre_usuario = usuarioE.getText().toString();
        String contraseña = contrasenaE.getText().toString();
        pbr.setVisibility(View.VISIBLE);

        if (nombre_usuario.isEmpty() && contraseña.isEmpty()) { //Validacion (que tdos los datos hayan sido ingresados)
            Toast.makeText(getApplicationContext(), "Ingrese datos a modificar", Toast.LENGTH_SHORT).show();
            pbr.setVisibility(View.INVISIBLE);

        } else {
            //Cuando el usuario no ingresa uno de los campos enviamos el dato anterior a la función
            if(nombre_usuario.isEmpty()){
                nombre_usuario = usuarioPrev;
            }
            if(contraseña.isEmpty()){
                contraseña = contraseñaPrev;

            }
            final String usuarioIntent = nombre_usuario; //datos tipo final para poder enviar
            final String contraseñaIntent = contraseña; //en el intent

            Response.Listener<String> respuesta = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonRespuesta = new JSONObject(response);
                        boolean ok = jsonRespuesta.getBoolean("ok");
                        if (ok == true) {
                            Intent i;
                            if(tipo.compareTo("gerente")==0){
                                i = new Intent(activity_editar.this, activity_bobeda.class);
                            }else{
                                i = new Intent(activity_editar.this, administrador.class);
                            }

                            //enviamos los datos necesarios para que inicie la sgte actividad
                            i.putExtra("nombre_usuario",usuarioIntent);
                            i.putExtra("cedula",cedula);
                            i.putExtra("contraseña",contraseñaIntent);
                            if(tipo.compareTo("gerente")==0){ //Si es gerente enviamos los datos adicionales
                                i.putExtra("id_bobeda", id_bobeda);
                                i.putExtra("estado",estado);

                                activity_editar.this.startActivity(i);//Se inicia el layout bobeda y se cierra el layout actual
                                activity_editar.this.finish();
                                Toast.makeText(getApplicationContext(),"AAAAAAAAAAAAAAA",Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(activity_editar.this);
                            alerta.setMessage("Fallo al editar datos").setNegativeButton("Reintentar", null).create().show();
                        }
                    } catch (JSONException e) {
                        e.getMessage();
                    }
                }

            }  ;
            Actualizar a = new Actualizar(nombre_usuario, contraseña, respuesta);
            RequestQueue cola= Volley.newRequestQueue(activity_editar.this);
            cola.add(a);
            Toast.makeText(getApplicationContext(),"hola",Toast.LENGTH_SHORT).show();
            //Intent bobeda = new Intent(activity_editar.this, activity_bobeda.class);
            //startActivity(bobeda);//Se inicia el layout bobeda y se cierra el layout actual
            //activity_editar.this.finish();
            //bobeda.putExtra("nombre_usuario",usuarioIntent);
            //bobeda.putExtra("cedula",cedula);
            //bobeda.putExtra("id_bobeda", id_bobeda);
            //bobeda.putExtra("estado",estado);
            //bobeda.putExtra("contraseña",contraseñaIntent);
        }
    };

    public void Regresar(View view) {
        Intent i = new Intent(this, activity_bobeda.class );
        startActivity(i);
    }
}




