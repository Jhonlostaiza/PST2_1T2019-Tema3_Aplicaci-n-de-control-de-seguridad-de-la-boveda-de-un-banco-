package com.example.sistemadesiguridad;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//Jhonny Lopez
//Esta clase realiza el envio de un objeto json con los datos de usuario y clave al archivo login.php , el cual nos retorna un boolean
//de respuesta si ingresamos la clave y el usuario almacenado en la bade de datos, Ademas si esta consulta es correcta nos retorna el tipo
//De usuario ingresado tipo gerente o tipo administrador.
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