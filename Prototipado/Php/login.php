<?php 
//Este codigo realiza la consulta de un usuario a una base de datos, y retorna los datos ademas del tipo de usuario al que pertenece
    $con=mysqli_connect("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
    $nombre_usuario =$_POST["nombre_usuario"];
    $contraseña=$_POST["contraseña"];
    $busqueda=mysqli_prepare($con,"SELECT * FROM usuario WHERE nombre_usuario=? AND contraseña=?");
    mysqli_stmt_bind_param($busqueda,"ss",$nombre_usuario,$contraseña);
    mysqli_stmt_execute($busqueda);
    mysqli_stmt_store_result($busqueda);
    mysqli_stmt_bind_result($busqueda,$id_usuario,$contraseña,$correo,$cedula,$nombre_usuario,$tipo);
    $bobeda=mysqli_query($con,"SELECT * FROM bobedas INNER JOIN odin ON bobedas.id_bobeda=odin.bobeda INNER JOIN usuario ON usuario.id_usuario=odin.usuario");
    $respuesta = array();
    $respuesta["sucess"]=false;
    while(mysqli_stmt_fetch($busqueda)){
        $respuesta["sucess"]=true;
        $respuesta["contraseña"]=$contraseña;
        $respuesta["correo"]=$correo;
        $respuesta["cedula"]=$cedula;
        $respuesta["nombre_usuario"]=$nombre_usuario;
        $respuesta["tipo"]=$tipo;
    }
    while($busqueda=mysqli_fetch_array($bobeda)){
        $respuesta["id_bobeda"]=$busqueda["id_bobeda"];
        $respuesta["nombre_bobeda"]=$busqueda["nombre_bobeda"];
        $respuesta["estado"]=$busqueda["estado"];
    }
    echo json_encode($respuesta);
?>