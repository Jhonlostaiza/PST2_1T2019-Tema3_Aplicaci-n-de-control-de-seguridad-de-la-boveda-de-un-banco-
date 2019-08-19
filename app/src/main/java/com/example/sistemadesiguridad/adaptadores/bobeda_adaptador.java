package com.example.sistemadesiguridad.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sistemadesiguridad.Entidades.bobeda_datos;
import com.example.sistemadesiguridad.R;

import java.util.ArrayList;

public class bobeda_adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<bobeda_datos> bobedaArrayList;

    public bobeda_adaptador(Context context, ArrayList<bobeda_datos> bobedaArrayList) {

        this.context = context;
        this.bobedaArrayList = bobedaArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return bobedaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return bobedaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lista_bobeda,null, true);

            //holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvid = (TextView) convertView.findViewById(R.id.id);
            holder.tvnombre = (TextView) convertView.findViewById(R.id.nombre);
            holder.tvestado = (TextView) convertView.findViewById(R.id.estado);

            convertView.setTag(holder);
        }else {
            //
            //getTag devuelve el objeto viewHolder establecido como una etiqueta a la vista
            holder = (ViewHolder)convertView.getTag();
        }

        //Picasso.get().load(userdatosArrayList.get(position).getImgURL()).into(holder.iv);
        holder.tvid.setText("Id: "+ bobedaArrayList.get(position).getId());
        holder.tvnombre.setText("Nombre: "+ bobedaArrayList.get(position).getNombre());
        holder.tvestado.setText("Estado: "+ bobedaArrayList.get(position).getEstado());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvid, tvnombre, tvestado;
        //protected ImageView iv;
    }

}
