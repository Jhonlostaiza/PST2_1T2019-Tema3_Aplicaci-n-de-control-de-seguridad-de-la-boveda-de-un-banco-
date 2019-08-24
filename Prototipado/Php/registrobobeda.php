<?php 
//Con ese codigo procedemos a registrar las bobedas dentro de la base de datos
    $con=mysqli_connect("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
    $bobeda=$_POST["nombre_bobeda"];
    $estado=$_POST["estado"];
    $respuesta=array();
    $respuesta["sucess"]=false;
    $consulta="SELECT * FROM  bobedas WHERE nombre_bobeda='$bobeda'";
    $resultado=mysqli_query($con,$consulta);
 if(mysqli_num_rows($resultado)>=1){
        $respuesta["sucess"]=false;
        $respuesta["mensaje"]="repetido";
    }
    else{
        $envio=mysqli_prepare($con,"INSERT INTO bobedas (nombre_bobeda,estado) VALUES (?,?)");
        mysqli_stmt_bind_param($envio,"ss",$bobeda,$estado);
        mysqli_stmt_execute($envio);
        $respuesta["sucess"]=true;
    }

    echo json_encode($respuesta);
?>