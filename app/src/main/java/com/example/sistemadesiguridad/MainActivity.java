package com.example.sistemadesiguridad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText usuarioN,claveN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Elementos del activity:
        Button ingresar = (Button) findViewById(R.id.ingresar);
        usuarioN = (EditText) findViewById(R.id.username);
        claveN = (EditText) findViewById(R.id.clave);
    }


        public void ingreso(View view) { //evento al presionar ingresar
                final String usuario = usuarioN.getText().toString();//Almancenamos los datos ingresados , tanto el username y su clave
                final String clave = claveN.getText().toString();
                //Toast.makeText(this, "realizando", Toast.LENGTH_SHORT).show();
                //Toast.makeText(this, usuario, Toast.LENGTH_SHORT);
                //closekeyboard();
                if (usuario.isEmpty() || clave.isEmpty()) { //Validacion (Todos los datos ingresados
                    Toast.makeText(this, "Datos incompletos", Toast.LENGTH_SHORT).show();

                } else {//si estan completos ingresamos a la consulta a la base de datos
                    Response.Listener<String> respuesta = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonRespuesta = new JSONObject(response);
                                boolean ok = jsonRespuesta.getBoolean("sucess");

                                if (ok == true) {
                                    String tipo = jsonRespuesta.getString("tipo");//Se pregunta por el tipo de usuario que la base de datos retorna
                                    if (tipo.compareTo("gerente") == 0) {//Si es gerente se envia al Layout de bobeda y se enria el nombre del usuario
                                        String nombre = jsonRespuesta.getString("nombre_usuario");
                                        Intent bobeda = new Intent(MainActivity.this, activity_bobeda.class);
                                        //bobeda.putExtra("usuario",nombre);
                                        MainActivity.this.startActivity(bobeda);//Se inicia el layout bobeda y se cierra el layout actual
                                        MainActivity.this.finish();
                                    }
                                    if (tipo.compareTo("administrador") == 0) {//Se realiza lo mismo que el caso anterio solo que este se renvia al layout administrador
                                        String nombre = jsonRespuesta.getString("nombre_usuario");
                                        Intent administrador = new Intent(MainActivity.this, administrador.class);
                                        //bobeda.putExtra("usuario",nombre);
                                        MainActivity.this.startActivity(administrador);
                                        MainActivity.this.finish();
                                    }


                                } else {
                                    AlertDialog.Builder alerta = new AlertDialog.Builder(MainActivity.this);//si el respuesta sucess de la base es false no existe el usuario en la base de datos y se envia el mensaje de alerta
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
            }





    public void closekeyboard(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager immm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            int flags = 0;
            immm.hideSoftInputFromWindow(view.getWindowToken(), flags );
        }

    }

    public void Registrarse(View view) {
        Intent i = new Intent(this, activity_registro.class);
        startActivity(i);
    }
}