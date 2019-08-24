<?php 
	$con=mysqli_connect("localhost","id10408286_odinpst","odin123456789","id10408286_odin");//datos de login de la base de datos
    $nombre_bobeda =$_POST["nombre_bobeda"];//Retenemos los datos enviados desde la boveda
    $id_bobeda=$_POST["id_bobeda"];
    $busqueda=mysqli_prepare($con,"SELECT * FROM bobedas WHERE nombre_bobeda=? AND id_bobeda=?");//Preparamos la consulta de la base de datos
    mysqli_stmt_bind_param($busqueda,"ss",$nombre_bobeda,$id_bobeda);
    mysqli_stmt_execute($busqueda);
    mysqli_stmt_store_result($busqueda);
    mysqli_stmt_bind_result($busqueda,$id_bobeda,$nombre_bobeda,$estado);
    $respuesta = array();//Iniciamos el array de retorno con la repuesta de la consulta a la base de datos
    $respuesta["sucess"]=false;//por defecto enviamos una respuesta de falso
    while(mysqli_stmt_fetch($busqueda)){//si la consulta es existosa enviamos una respuesta en true y el estado de la boveda
        $respuesta["comando"]=$estado;
        $respuesta["sucess"]=true;
    }	
    echo json_encode($respuesta);
?>