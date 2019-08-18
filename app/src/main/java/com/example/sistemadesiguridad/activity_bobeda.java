package com.example.sistemadesiguridad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class activity_bobeda extends AppCompatActivity {
    private String usuario, cedula;
    private int id_bobeda, estado;
    private TextView txtv_usuario, txtv_cedula, txtvId_bobeda, txtv_estado;
    private ImageView sem_estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras(); //obtener información del activity anterior
        usuario = bundle.getString("nombre_usuario");
        cedula = bundle.getString("cedula");
        id_bobeda = bundle.getInt("id_bobeda");
        estado = bundle.getInt("estado");

        setContentView(R.layout.activity_bobeda);


        txtv_usuario = (TextView)findViewById(R.id.nombre_usuario); //mostramos la información del usuario
        txtv_cedula = (TextView)findViewById(R.id.cod_usuario);
        txtvId_bobeda = (TextView)findViewById(R.id.textView6);
        txtv_estado = (TextView)findViewById(R.id.estado_puerta);
        sem_estado = (ImageView)findViewById(R.id.semaforo_estado);

        if (estado ==1){
            txtv_estado.setText("Estado: Puerta abierta");
            sem_estado.setImageResource(R.drawable.estado_open);
        }

        txtv_usuario.setText(usuario);
        txtv_cedula.setText(cedula);
        txtvId_bobeda.setText(id_bobeda);


    }

    public void Editar(View view) {
        Intent i = new Intent(this, activity_editar.class );
        startActivity(i);
    }

    public void CerraSesion(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
    public void abrirpuerta(View view){
        final int abrir=1;//ingresa la condicion de apertura de la puerta
        if (estado == 1){
            Toast.makeText(this, "La bobeda ya está abierta, intente otro comando", Toast.LENGTH_LONG).show();
        }else{
            Response.Listener<String> respuesta = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonRespuesta = new JSONObject(response);
                        boolean ok = jsonRespuesta.getBoolean("sucess");//si se logro el cambio dentro de la base de datos se procede 
                        if (ok == true){
                                //en esta parte se pone que se realiza en el app cuando ya se abre la puerta
                            //cambiar de color el indicador cosas asi io que se XD
                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(activity_bobeda.this);//si el respuesta sucess de la base es false no existe el usuario en la base de datos y se envia el mensaje de alerta
                            alerta.setMessage("Error en apertura").setNegativeButton("Reintentar", null).create().show();
                        }
                    } catch (JSONException e) {
                        e.getMessage();
                    }
                }
            };
            puerta r = new puerta(abrir, id_bobeda, respuesta);
            RequestQueue cola = Volley.newRequestQueue(activity_bobeda.this);
            cola.add(r);
        }
    }
    public void cierrepuerta(View view){
        final int abrir = 0;
        if (estado==0){
            Toast.makeText(this, "La bóbeda ya está cerrada, intente otro comando", Toast.LENGTH_LONG).show();
        }else{
            Response.Listener<String> respuesta = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonRespuesta = new JSONObject(response);
                        boolean ok = jsonRespuesta.getBoolean("sucess");
                        if (ok == true){
                            //en esta parte se pone que se realiza en el app cuando ya se abre la puerta
                            //cambiar de color el indicador cosas asi io que se XD
                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(activity_bobeda.this);//si el respuesta sucess de la base es false no existe el usuario en la base de datos y se envia el mensaje de alerta
                            alerta.setMessage("Error en apertura").setNegativeButton("Reintentar", null).create().show();
                        }
                    } catch (JSONException e) {
                        e.getMessage();
                    }
                }
            };
            puerta r = new puerta(abrir, id_bobeda,respuesta);
            RequestQueue cola = Volley.newRequestQueue(activity_bobeda.this);
            cola.add(r);
        }
    }
}


