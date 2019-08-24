<?php
 // En ese codigo se retorna la informacion de un  usuario existente en una base de datos, caso contrario retorna un 0 y un mensaje de error

$response = array();

// connecting to db
$db = mysqli_connect("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
 
// get all products from products table
$result = mysqli_query($db,"SELECT contraseña, cedula, nombre_usuario, tipo,correo FROM usuario "); 
 
// check for empty result
if (mysqli_num_rows($result) > 0) {
     $response["datos"] = array();
    while ($row = mysqli_fetch_array($result)) {

        $product = array();

        $product["cedula"]=$row["cedula"];
        $product["nombre_usuario"]=$row["nombre_usuario"];
        $product["tipo"]=$row["tipo"];
        $product["correo"]=$row["correo"];
        $product["contraseña"]=$row["contraseña"];
        

        array_push($response["datos"],$product);
    }

    $response["success"] = 1;
 
    echo json_encode($response);
} else {
    $response["success"] = 0;
    $response["message"] = "No products found";
    echo json_encode($response);
}
?>