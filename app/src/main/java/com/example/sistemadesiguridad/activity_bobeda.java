package com.example.sistemadesiguridad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
    private String id_bobeda, estado;
    private int estadoInt, id_bobedaInt;
    private TextView txtv_usuario, txtv_cedula, txtvId_bobeda, txtv_estado;
    private ImageView sem_estado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras(); //obtener información del activity anterior
        usuario = bundle.getString("nombre_usuario");
        cedula = bundle.getString("cedula");
        id_bobeda = bundle.getString("id_bobeda");
        id_bobedaInt = Integer.parseInt(id_bobeda);
        estado = bundle.getString("estado");
        estadoInt = Integer.parseInt(estado);


        setContentView(R.layout.activity_bobeda);


        txtv_usuario = (TextView)findViewById(R.id.nombre_usuario); //mostramos la información del usuario
        txtv_cedula = (TextView)findViewById(R.id.cod_usuario);
        txtvId_bobeda = (TextView)findViewById(R.id.textView6);
        txtv_estado = (TextView)findViewById(R.id.estado_puerta);
        sem_estado = (ImageView)findViewById(R.id.semaforo_estado);

        if (estadoInt==1){
            txtv_estado.setText("Estado: Puerta abierta");
            sem_estado.setImageResource(R.drawable.estado_open);
        }

        txtv_usuario.setText(usuario);
        txtv_cedula.setText(cedula);
        txtvId_bobeda.setText("id bobeda: "+id_bobeda);


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
        if (estadoInt == 1){
            Toast.makeText(this, "bobeda ya abierta, intente otro comando", Toast.LENGTH_SHORT).show();
        }else{

            //activamos el semaforo que indica el cambio de estado
            sem_estado.setImageResource(R.drawable.estado_trancision);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sem_estado.setImageResource(R.drawable.estado_open);
                    txtv_estado.setText("Estado: Puerta abierta");
                    //toast indicando la realizacion del proceso
                    Toast.makeText(getApplicationContext(), "Bóbeda abierta", Toast.LENGTH_SHORT).show();
                }
            },1500);

            //Cambiamos TextView que indica el estado
            txtv_estado.setText("Estado: abriendo...");


            //cambiamos los valores de las variables
            estado = "1";
            estadoInt=1;

            Response.Listener<String> respuesta = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonRespuesta = new JSONObject(response);
                        boolean ok = jsonRespuesta.getBoolean("sucess");//si se logro el cambio dentro de la base de datos se procede 
                        if (ok == true){


                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(activity_bobeda.this);//si el respuesta sucess de la base es false no existe el usuario en la base de datos y se envia el mensaje de alerta
                            alerta.setMessage("Error en apertura").setNegativeButton("Reintentar", null).create().show();
                        }
                    } catch (JSONException e) {
                        e.getMessage();
                    }
                }
            };
            puerta r = new puerta(abrir, id_bobedaInt, respuesta);
            RequestQueue cola = Volley.newRequestQueue(activity_bobeda.this);
            cola.add(r);
        }
    }
    public void cierrepuerta(View view){
        final int abrir = 0;
        if (estadoInt==0){
            Toast.makeText(this, "Bóbeda ya cerrada, intente otro comando", Toast.LENGTH_SHORT).show();
        }else{

            //activamos el semaforo que indica el cambio de estado
            sem_estado.setImageResource(R.drawable.estado_trancision);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sem_estado.setImageResource(R.drawable.estado_close);
                    txtv_estado.setText("Estado: Puerta cerrada");
                    //toast indicando la realizacion del proceso
                    Toast.makeText(getApplicationContext(), "Bóbeda cerrada", Toast.LENGTH_SHORT).show();
                }
            },1500);

            //Cambiamos TextView que indica el estado
            txtv_estado.setText("Estado: cerrando...");

            //cambiamos los valores de las variables
            estado = "0";
            estadoInt=0;





            //Envío de la informacion a la nube
            Response.Listener<String> respuesta = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonRespuesta = new JSONObject(response);
                        boolean ok = jsonRespuesta.getBoolean("sucess");
                        if (ok == true){
                            //


                        } else {
                            AlertDialog.Builder alerta = new AlertDialog.Builder(activity_bobeda.this);//si el respuesta sucess de la base es false no existe el usuario en la base de datos y se envia el mensaje de alerta
                            alerta.setMessage("Error en apertura").setNegativeButton("Reintentar", null).create().show();
                        }
                    } catch (JSONException e) {
                        e.getMessage();
                    }
                }
            };
            puerta r = new puerta(abrir, id_bobedaInt,respuesta);
            RequestQueue cola = Volley.newRequestQueue(activity_bobeda.this);
            cola.add(r);
        }
    }
}


