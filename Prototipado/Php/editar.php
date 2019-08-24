
<?php 
//En ese codigo se realiza en cambio de clave del usuario ingresado y se retorna una respuesta de retroalimentacion
    $con=new mysqli("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
    $nombre_usuario =$_POST["nombre_usuario"];
    $clave=$_POST["contraseña"];
    $respuesta = array();
    if($con->query("UPDATE usuario SET contraseña= '$clave' WHERE nombre_usuario= '$nombre_usuario'")==TRUE){
        $respuesta["ok"]=true;
    }
    else{
        $respuesta["ok"]=false;
    }
    
    echo json_encode($respuesta);
?>