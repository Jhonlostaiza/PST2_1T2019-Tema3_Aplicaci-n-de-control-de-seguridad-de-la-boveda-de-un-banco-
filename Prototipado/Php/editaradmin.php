<?php 
//En este codigo editamos todos los campos de un usuario debido a un cambio dentro del app movil
    $con=mysqli_connect("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
    $contraseña=$_POST["contraseña"];
    $correo=$_POST["correo"];
    $cedula=$_POST["cedula"];
	$nombre_usuario =$_POST["nombre_usuario"];
    $tipo=$_POST["tipo"];
    print_r($_POST);
    
    $envio= mysqli_query($con,"UPDATE usuario SET contraseña= '{$contraseña}', correo= '{$correo}', nombre_usuario= '{$nombre_usuario}', tipo= '{$tipo}'  WHERE cedula= '{$cedula}'");
	
    mysqli_stmt_bind_param($envio,"ssssi",$contraseña,$correo,$nombre_usuario,$tipo,$cedula);
    mysqli_stmt_execute($envio);
    $respuesta = array();
   $respuesta["listo"]=true;
   echo json_encode($respuesta);
?>