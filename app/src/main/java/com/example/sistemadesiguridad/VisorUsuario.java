package com.example.sistemadesiguridad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class VisorUsuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_usuario);

        //declaramos los elementos y obtenemos la informacion que se esta obteniendo
        TextView usuario = findViewById(R.id.nombre_usuario);
        TextView cedula = findViewById(R.id.txced);
        TextView correo = findViewById(R.id.txcorreo);
        TextView contraseña = findViewById(R.id.txtcont);
        TextView tipo = findViewById(R.id.txtipo);
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

    }
    public void volver(View view) {
        Intent i = new Intent(this, Usuarios.class);
        startActivity(i);
    }
}
