package com.example.sistemadesiguridad.Entidades;

public class usuario_datos {

    private Integer id_usuario;
    private String contraseña;
    private String correo;
    private Integer cedula;
    private String nombre_usuario;
    private String tipo;

    public usuario_datos(Integer cedula, String nombre_usuario, String contraseña, String tipo){
        this.cedula = cedula;
        this.nombre_usuario = nombre_usuario;
        this.contraseña = contraseña;
        this.tipo = tipo;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }
    public Integer getCedula() {
        return cedula;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}
