package com.example.sistemadesiguridad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class administrador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);
    }
    public void CerraSesion(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
    public void Editar(View view) {
        Intent i = new Intent(this, activity_editar.class );
        startActivity(i);
    }
    public void Registar_usuario(View view) {
        Intent i = new Intent(this, activity_registro.class );
        startActivity(i);
    }
}
