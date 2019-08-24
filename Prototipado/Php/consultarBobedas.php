<?php
//En este condigo se consulta a la base de datos si la bobeda existe y se retorna una respuesta 

$response = array();

// connecting to db
$db = mysqli_connect("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
 
// get all products from products table
$result = mysqli_query($db,"SELECT nombre_bobeda FROM bobedas "); 
 
// check for empty result
if (mysqli_num_rows($result) > 0) {
     $response["datos"] = array();
    while ($row = mysqli_fetch_array($result)) {

        $product = array();
        $product["nombre_bobeda"]=$row["nombre_bobeda"];
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