package com.example.sistemadesiguridad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class administrador extends AppCompatActivity {
    private String usuario, contraseña,cedula;
    private TextView txtv_usuario, txtv_cedula;
    Button btnConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        //obtenemos la informacion enviada en el intent
        Bundle bundle = getIntent().getExtras(); //obtener información del activity anterior
        usuario = bundle.getString("nombre_usuario");
        contraseña = bundle.getString("contraseña");
        cedula = bundle.getString("cedula");
        btnConsulta = (Button)findViewById(R.id.usuarios);

        //mostramos estos datos en la pantalla
        txtv_usuario = (TextView)findViewById(R.id.nombre_usuario); //mostramos la información del usuario
        txtv_cedula = (TextView)findViewById(R.id.cod_usuario);

        txtv_usuario.setText(usuario);
        txtv_cedula.setText(cedula);



    }
    public void ConsultarUser(View view) {
        Intent i = new Intent(this, Usuarios.class );
        startActivity(i);
    }

    public void ConsultarBobeda(View view) {
        Intent i = new Intent(this, AdministradorBobeda.class );
        startActivity(i);
    }

    public void CerraSesion(View view) {
        Intent i = new Intent(this, MainActivity.class );
        startActivity(i);
    }
    public void Editar(View view) {
        Intent i = new Intent(this, activity_editar.class );
        i.putExtra("nombre_usuario",usuario);
        i.putExtra("contraseña",contraseña);
        i.putExtra("cedula",cedula);
        i.putExtra("tipo","administrador");
        startActivity(i);
    }
    public void Registar_usuario(View view) {
        Intent i = new Intent(this, activity_registro.class );
        i.putExtra("nombre_usuario",usuario);
        i.putExtra("contraseña",contraseña);
        i.putExtra("cedula",cedula);
        startActivity(i);
    }
}
