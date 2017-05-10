<html>
<head>
<title>Insert - Welcome to Excercise of Database and Web Application</title>
</head>
<body>
<h1>Here, you can insert your data</h1>
<h2><a href="index.php">Goto to index page </a></h2>

<?php
if($_POST['check']=='yes')
{
	
	$name=$_POST['name'];
	$course=$_POST['course'];
	
if($name!==null or $course!==null)
{
	include('connection.php');

	
	$sql="insert into tu_studentinfo (id,name,course) values (nextval('tu_stu_id_seq'),'".$name."','".$course."')";
	
	$result = pg_query($db_handle, $sql);
	if($result)
	{
		echo "Your data has been succesfully saved in database.<br />";
		echo "<a href='showalldata.php'>Show all saved data</a>";
		
	}
	else{
		echo "Some error happend";
	}
	
}
else
{
	echo "Null data cann't be saved, Pelase try again !!!";
}
}

?>

<form method="post" name ="form_data" action="insert.php">
<input type="hidden" name="check" value="yes" />
<table>
<tr>
<td> Name</td><td>
<input type="text" name="name"  required /></td></tr>
<tr><td> Course </td><td>
<input type="text" name="course" required/></td></tr>
<tr><td></td><td>
<input type ="submit" value="Save" /> </td></tr>

</form>
