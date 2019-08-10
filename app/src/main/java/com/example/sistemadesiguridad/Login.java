package com.example.sistemadesiguridad;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Login extends StringRequest {
    private static final String ruta="https://odinpst.000webhostapp.com/login.php";
    private Map<String, String> parametros;

    public Login(String username, String clave, Response.Listener<String> listener) {
        super(Request.Method.POST, ruta,listener,null);
        parametros = new HashMap<>();
        parametros.put("nombre_usuario", username+"");
        parametros.put("contraseña", clave+"");
    }
    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }
}