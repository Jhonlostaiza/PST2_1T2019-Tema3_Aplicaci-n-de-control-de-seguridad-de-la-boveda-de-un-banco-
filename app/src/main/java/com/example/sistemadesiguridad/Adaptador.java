package com.example.sistemadesiguridad;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


//Esta clase controlara nuestro adaptador
class Adaptador extends BaseAdapter {

    private static LayoutInflater inflater = null;

    private final ArrayList<String> datos; //matriz de datos principales


    public Adaptador(Context contexto, ArrayList<String> datos) {
        //entorno de la aplicacion
        this.datos = datos;
        //sirve para instanciar el archivo de diseño xml creado.
        inflater = (LayoutInflater)contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //Controlara la posicion que nos esten enviando para presentar los datos
    //Este metodo, nos devuelve un contador, por cada elemento que se genere ira incrementando de forma
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final View vista = inflater.inflate(R.layout.lista_usuario, null);

        TextView cedula = vista.findViewById(R.id.txtcedula);
        TextView usuario = vista.findViewById(R.id.txtusuario);
        TextView tipo = vista.findViewById(R.id.txttipo);

        //asignamos los valores, por medio de un contador
        //0 y 1 corresponden al nombre de plato y su valor
        cedula.setText(datos.get(i).toString());
        usuario.setText(datos.get(i).toString());
        tipo.setText(datos.get(i).toString());

        return vista;
    }

    //retornara los datos "datosImg", obtendra el total de elementos que hay

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
