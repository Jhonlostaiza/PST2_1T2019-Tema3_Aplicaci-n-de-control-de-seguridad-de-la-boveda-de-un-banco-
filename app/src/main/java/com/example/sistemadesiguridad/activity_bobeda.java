package com.example.sistemadesiguridad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class activity_bobeda extends AppCompatActivity {
    private String usuario, cedula;
    private TextView txtv_usuario, txtv_cedula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras(); //obtener información del activity anterior
        usuario = bundle.getString("nombre_usuario");
        cedula = bundle.getString("cedula");
        setContentView(R.layout.activity_bobeda);

        txtv_usuario = (TextView)findViewById(R.id.nombre_usuario); //mostramos la información del usuario
        txtv_cedula = (TextView)findViewById(R.id.cod_usuario);

        txtv_usuario.setText(usuario);
        txtv_cedula.setText(cedula);

    }

    public void Editar(View view) {
        Intent i = new Intent(this, activity_editar.class );
        startActivity(i);
    }

    public void CerraSesion(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
}
