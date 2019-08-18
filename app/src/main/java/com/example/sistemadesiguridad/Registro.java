package com.example.sistemadesiguridad;

import android.view.textclassifier.TextLinks;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;
//Jhonny Lopez
//Esta clase realiza el envio de un objeto json con los datos de usuario,clave,correo,dni y tipo  al archivo registro.php , el cual nos retorna un boolean
//de respuesta que nos confirma si se logro ser almacenado en la base de datos.
public class Registro extends StringRequest {
    private static final String ruta="https://odinpst.000webhostapp.com/registro.php";
    private Map<String, String> parametros;

    public Registro(String usuario, String correo, int DNI, String contraseña, String tipo, Response.Listener<String> listener) {
        super(Method.POST, ruta,listener,null);
        parametros = new HashMap<>();
        parametros.put("usuario", usuario+"");
        parametros.put("correo", correo+"");
        parametros.put("DNI", DNI+"");
        parametros.put("contraseña", contraseña+"");
        parametros.put("tipo", tipo+"");
    }
    @Override
    protected Map<String, String> getParams(){
        return parametros;
    }
}