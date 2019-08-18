package com.example.sistemadesiguridad;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class puerta extends StringRequest {
    private static final String ruta="https://odinpst.000webhostapp.com/puerta.php";
    private Map<String, String> parametros;

    public puerta (int estado, int id_boveda, Response.Listener<String> listener) {
        super(Request.Method.POST, ruta,listener,null);
        parametros = new HashMap<>();
        parametros.put("estado", estado+"");
        parametros.put("id_boveda", id_boveda+"");
    }
    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }
}