package com.example.sistemadesiguridad;

import android.view.View;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegistroBobeda extends StringRequest {
    private static final String ruta="https://odinpst.000webhostapp.com/RegistroBobeda.php";
    private Map<String, String> parametros;

    public RegistroBobeda(String nombre, int estado,  Response.Listener<String> listener) {
        super(Method.POST, ruta,listener,null);
        parametros = new HashMap<>();
        parametros.put("nombre_bobeda", nombre+"");
        parametros.put("estado", estado+"");
    }
    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }
}
