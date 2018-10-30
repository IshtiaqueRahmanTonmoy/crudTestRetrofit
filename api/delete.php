

<?php

    # We can use this file for delete   
	# Here we use POST request

	include "connect.php";  // for connection

    $data = json_decode(file_get_contents('php://input'), true);
	 
    $id = $data["id"];
	$name = $data["name"];
	$address = $data["address"];


	$sql = "DELETE FROM test_table WHERE id = $id";
	
    
    if (mysqli_query($conn, $sql)) {
    	
    	echo '{ "result" : "Success" }';

    }

    else {

    	echo '{ "result" : "sql error" }';
    }
	
	
	mysqli_close($conn);
	
	?>