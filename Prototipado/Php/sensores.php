<?php 
//Con ese codigo realizamos el cambio de estado de los sensores
    $con=new mysqli("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
    $sensor =$_POST["sensor"];
    $estado =$_POST["estado"];
    $cambio1=mysqli_query($con,"UPDATE sensores SET estado='$estado' WHERE tipo_sensor='$sensor'");
    $respuesta = array();
    $respuesta["respuesta_externo"]=true;	
    echo json_encode($respuesta);
?>