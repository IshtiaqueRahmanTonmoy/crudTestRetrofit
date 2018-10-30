
<?php

   # -----Firoz Mahmud-----

   $method = $_SERVER['REQUEST_METHOD'];


   switch ($method) {

   	     case 'GET':
   	
              getOperation();

              break;


   	     case 'POST':
   	  
   	          $data = json_decode(file_get_contents('php://input'), true);

   	          postOperation($data);

   	          break;


   	     case 'PUT':
   	  
   	          $data = json_decode(file_get_contents('php://input'), true);

   	          putOperation($data);

   	          break;


   	     case 'DELETE':
		 
		      # Note : We are not able to receive anything by using DELETE requiest
   	  
   	          $data = json_decode(file_get_contents("php://input"), true);

   	          deleteOperation($data);

   	          break;
			  
	     case 'PATCH':   
		 
		      # Now we use PATCH requiest to get id from app and delete the specific data from database
   	  
   	          $data = json_decode(file_get_contents("php://input"), true);

   	          deleteOperation($data);

   	          break;
   	
   	    
   	     default:
   		
   	         echo '{ "result" : "Not Supported" }';

   	         break;
   }






// function for get operation
function getOperation() {


	include "connect.php";  // for connection


	$sql = "SELECT * FROM test_table";

	$result = mysqli_query($conn, $sql);



	if (mysqli_num_rows($result) > 0) {
		

		$row = array();


		while ($r = mysqli_fetch_assoc($result)) {
			

			# $row['result'][] = $r;   // with result object

			 $row[] = $r; // only array


  		}

  		echo json_encode($row);
	}

	else {

		echo '{ "result": "No Data Found" }';
	}


	mysqli_close($conn);
}  
// end of function






// function for post operation
function postOperation($data) {


	include "connect.php";  // for connection


	$id = $data["id"];
	$name = $data["name"];
	$address = $data["address"];


	$sql = "INSERT INTO test_table (id, name, address) VALUES ($id, '$name', '$address')";

    
    if (mysqli_query($conn, $sql)) {
    	
    	echo '{ "result" : "Success" }';

    }

    else {

    	echo '{ "result" : "sql error" }';
    }
	
	
	mysqli_close($conn);

} 
// end of function






// function for put operation
function  putOperation($data) {


	include "connect.php";  // for connection

    
    $id = $data["id"];
	$name = $data["name"];
	$address = $data["address"];


	$sql = "UPDATE test_table SET name = '$name', address = '$address' WHERE id = $id";
	
    
    if (mysqli_query($conn, $sql)) {
    	
    	echo '{ "result" : "Success" }';

    }

    else {

    	echo '{ "result" : "sql error" }';
    }
	
	
	mysqli_close($conn);

} 
// end of function







// function for delete operation
function  deleteOperation($data) {


	include "connect.php";  // for connection

  
    $id = $data["id"];


	$sql = "DELETE FROM test_table WHERE id = $id";

	# $sql = "DELETE FROM test_table WHERE id = 6";
	
    
    if (mysqli_query($conn, $sql)) {
    	
    	echo '{ "result" : "Success" }'; 

    }

    else {

    	echo '{ "result" : "sql error" }';
    }
	

	mysqli_close($conn);
	
} 
// end of function


   #---- Firoz Mahmud-----

?>

