<?php 
//Con este codigo cambiamos la variable de estado de la bobeda por cada apertura o cierre de la puerta
    $con=new mysqli("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
    $estado =$_POST["estado"];
    $bobeda =$_POST["id_bobeda"];
    $respuesta = array();
    if($con->query("UPDATE bobedas SET estado='$estado' WHERE id_bobeda='$bobeda'")==TRUE){
        $respuesta["estado"]=$estado;
        $respuesta["sucess"]=true;
    }
    else{
        $respuesta["sucess"]=false;
    }
    
    echo json_encode($respuesta);
?>