package com.example.sistemadesiguridad.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sistemadesiguridad.Entidades.usuario_datos;
import com.example.sistemadesiguridad.R;

import java.util.List;

public class user_adapter extends RecyclerView.Adapter<user_adapter.DatosConsulta> {

    List<usuario_datos> listaDatos;

    public user_adapter(List<usuario_datos> listaDatos) {this.listaDatos = listaDatos; }

    @Override
    public DatosConsulta onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_usuario, parent, false);
        DatosConsulta holder = new DatosConsulta(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(DatosConsulta holder, int position) {
        holder.txtcedula.setText(listaDatos.get(position).getCedula());
        holder.txtusuario.setText(listaDatos.get(position).getNombre_usuario());
        holder.txtcontraseña.setText(listaDatos.get(position).getContraseña());
        holder.txttipo.setText(listaDatos.get(position).getTipo());

    }

    @Override
    public int getItemCount() {
        return listaDatos.size();
    }


    public static class DatosConsulta extends RecyclerView.ViewHolder{

        TextView txtcedula, txtusuario, txtcontraseña, txttipo;
        public DatosConsulta(View itemView) {
            super(itemView);
            txtcedula = (TextView)itemView.findViewById(R.id.txtcedula);
            txtusuario = (TextView)itemView.findViewById(R.id.txtusuario);
            txtcontraseña = (TextView)itemView.findViewById(R.id.txtcontraseña);
            txttipo = (TextView)itemView.findViewById(R.id.txttipo);



        }
    }
}
