<?php 
//En este codigo realizamos el registro de un usuario
    $con=mysqli_connect("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
    $nombre_usuario=$_POST["usuario"];
    $correo=$_POST["correo"];
    $cedula=$_POST["DNI"];
    $contraseña=$_POST["contraseña"];
    $tipo=$_POST["tipo"];
    $gerente="gerente";
    $respuesta=array();
    $respuesta["sucess"]=false;
    $consulta="SELECT * FROM  usuario WHERE nombre_usuario='$nombre_usuario'";
    $administrador="administrador";
    $resultado=mysqli_query($con,$consulta);
    //consultamos el tipo de usuario que es para proceder a registrar
if(strcasecmp ($tipo ,$gerente )==0){
    //Realizamos la consulta si existe otro usuario con el mismo nombre
 if(mysqli_num_rows($resultado)>=1){
    //Si existe un usuario igual mandamos un mensaje negativo
        $respuesta["sucess"]=false;
        $respuesta["mensaje"]="repetido";
    }
    else{
        //Si no existe un usuario igual se procede a realizar el registro
        $envio=mysqli_prepare($con,"INSERT INTO usuario (contraseña,correo,cedula,nombre_usuario,tipo) VALUES (?,?,?,?,?)");
        mysqli_stmt_bind_param($envio,"ssiss",$contraseña,$correo,$cedula,$nombre_usuario,$tipo);
        mysqli_stmt_execute($envio);
        $respuesta["sucess"]=true;
    }
}
if(strcasecmp ($tipo ,$administrador )==0){
     if(mysqli_num_rows($resultado)>=1){
        $respuesta["sucess"]=false;
    }
    else{
        $envio=mysqli_prepare($con,"INSERT INTO usuario (contraseña,correo,cedula,nombre_usuario,tipo) VALUES (?,?,?,?,?)");
        mysqli_stmt_bind_param($envio,"ssiss",$contraseña,$correo,$cedula,$nombre_usuario,$tipo);
        mysqli_stmt_execute($envio);
        $respuesta["sucess"]=true;
    }
}
     
    echo json_encode($respuesta);
?>