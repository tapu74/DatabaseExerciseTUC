<?php

  $host = "pgsql.hrz.tu-chemnitz.de"; 
  $user = "";  
  $password = ""; 
  $port = "5432";
  $database_name = "";
  
    $db_handle = pg_connect("host=" . $host . " port=$port dbname=" . $database_name ." user=" . $user ." password=" . $password) 
	or die("Can't connected with database"); 
  
  // 1.1 Überprüfen, ob der Verbindungsaufbau geklappt hat. 
  $stat = pg_connection_status($db_handle);
  if ($stat === PGSQL_CONNECTION_OK) 
  { 
    // echo("Die Verbindung zur Datenbank wurde aufgebaut.<br/>");
    // echo("Inhalt des Datenbank-Handles:<br />"); 
    // echo($db_handle); 
  } 
  
  ?>