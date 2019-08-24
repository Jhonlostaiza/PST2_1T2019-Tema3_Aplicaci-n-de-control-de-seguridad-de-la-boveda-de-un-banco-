<?php
 

$response = array();// iniciamos la respuesta que se enviara al app

// connecting to db
$db = mysqli_connect("localhost","id10408286_odinpst","odin123456789","id10408286_odin");
 
// get all products from products table
$result = mysqli_query($db,"SELECT id_bobeda, nombre_bobeda, estado FROM bobedas "); //realizamos la consulta a la base de datos
 
// check for empty result
if (mysqli_num_rows($result) > 0) {//si existe se realiza la catura y se cambia el estado de dicha bobeda
     $response["datos"] = array();
    while ($row = mysqli_fetch_array($result)) {

        $product = array();

        $product["id_bobeda"]=$row["id_bobeda"];
        $product["nombre_bobeda"]=$row["nombre_bobeda"];
        $product["estado"]=$row["estado"];
        

        array_push($response["datos"],$product);
    }

    $response["success"] = 1;//mensaje de confirmancion 
 
    echo json_encode($response);
} else {
    $response["success"] = 0;//si no se logra se envia un mensage 
    $response["message"] = "No products found";
    echo json_encode($response);
}
?>