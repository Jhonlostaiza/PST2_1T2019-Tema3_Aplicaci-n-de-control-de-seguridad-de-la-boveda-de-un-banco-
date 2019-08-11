package com.example.sistemadesiguridad;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Actualizar extends StringRequest {
    private static final String ruta="https://odinpst.000webhostapp.com/editar.php";
    private Map<String, String> parametros;

    public Actualizar(String usuario, String contraseña, Response.Listener<String> listener) {
        super(Method.POST, ruta,listener,null);
        parametros = new HashMap<>();
        parametros.put("nombre_usuario", usuario+"");
        parametros.put("contraseña", contraseña+"");
    }
    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }
}
