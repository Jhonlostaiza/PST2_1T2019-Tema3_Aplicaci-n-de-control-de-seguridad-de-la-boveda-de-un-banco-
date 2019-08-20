package com.example.sistemadesiguridad.admin;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class editar_admin extends StringRequest {
    private static final String ruta="https://odinpst.000webhostapp.com/editar_admin.php";
    private Map<String, String> parametros;

    public editar_admin( String contraseña, String correo, String cedula, String nombre_usuario, String tipo ,Response.Listener<String> listener) {
        super(Method.POST, ruta,listener,null);
        parametros = new HashMap<>();
        parametros.put("contraseña", contraseña+"");
        parametros.put("correo", correo+"");
        parametros.put("cedula", cedula+"");
        parametros.put("nombre_usuario", nombre_usuario+"");
        parametros.put("tipo", tipo+"");

    }
    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }


}