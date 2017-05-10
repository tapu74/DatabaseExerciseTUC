
<html>
<head>
<title>Create a Table</title>
</head>
<body>
<h1>Create your own table</h1>
<?php
if($_GET['create']=='Yes')
{
include('connection.php');

$drop_res=pg_query($db_handle,"drop SEQUENCE tu_stu_id_seq cascade;");
$drop_result=pg_query($db_handle,"drop table tu_studentinfo;");



$res=pg_query($db_handle,"CREATE SEQUENCE tu_stu_id_seq;");


$result = pg_query($db_handle, "CREATE TABLE tu_studentinfo(
   id  INTEGER PRIMARY KEY,
   name           TEXT      NOT NULL,
   course            TEXT       NOT NULL
);");



if ($result) {
    echo "Table tu_studentinfo created successfully";
} else {
    echo "Error creating table: " .  pg_last_error($db_handle);
}

if(pg_close($db_handle))
  {
     //echo "Db Colose";
  }
  else
  {
    echo("Error<br/>");
  }
  echo "<a href='index.php'>Goto Home page</a>";

}
else
{
?>


<p>
Table Name : <strong> tu_studentinfo </strong><br />
Column : id <br />
Column : name <br />
Column : course </p>

<br />
<p> Here is the table creation code </p>
<br />
<p> CREATE SEQUENCE tu_stu_id_seq; </p>
<p> CREATE TABLE tu_studentinfo(
   id  INTEGER PRIMARY KEY  ,
   name           TEXT      NOT NULL,
   course            TEXT       NOT NULL
);
</p>

<br />
<p>Do you want to create same table in your database then click below link </p>
<a href ="createTable.php?create=Yes" >Create Table </a>
<?php
}
?>

