
<?php

   $servername = "localhost";
   $username = "root";
   $password = "";
   $dbname = "retrofit_test";


   // Create connection
   $conn = new mysqli($servername, $username, $password, $dbname);


   if ($conn -> connect_error) {
   	
   	die("Error : " . $conn -> connect_error);
   }
   

  # echo "Success";
 
?>