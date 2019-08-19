package com.example.sistemadesiguridad.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sistemadesiguridad.Entidades.user_datos;
import com.example.sistemadesiguridad.R;

import java.util.ArrayList;

public class user_adaptador extends BaseAdapter {

    private Context context;
    private ArrayList<user_datos> userdatosArrayList;

    public user_adaptador(Context context, ArrayList<user_datos> userdatosArrayList) {

        this.context = context;
        this.userdatosArrayList = userdatosArrayList;
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
        return userdatosArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userdatosArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.lista_usuario,null, true);

            //holder.iv = (ImageView) convertView.findViewById(R.id.iv);
            holder.tvcedula = (TextView) convertView.findViewById(R.id.cedula);
            holder.tvusuario = (TextView) convertView.findViewById(R.id.nombre_usuario);
            holder.tvtipo = (TextView) convertView.findViewById(R.id.tipo);

            convertView.setTag(holder);
        }else {
            //getTag devuelve el objeto viewHolder establecido como una etiqueta a la vista
            holder = (ViewHolder)convertView.getTag();
        }

        //Picasso.get().load(userdatosArrayList.get(position).getImgURL()).into(holder.iv);
        holder.tvcedula.setText("Cedula: "+ userdatosArrayList.get(position).getCedula());
        holder.tvusuario.setText("Usuario: "+ userdatosArrayList.get(position).getNombre_usuario());
        holder.tvtipo.setText("Tipo: "+ userdatosArrayList.get(position).getTipo());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvcedula, tvusuario, tvtipo;
        //protected ImageView iv;
    }

}
