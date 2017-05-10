<html>
<head>
<title>Show all Data</title>
</head>
<body>
<h1>Show All Data</h1>
<h2><a href="index.php">Goto to index page </a></h2>
<table>
<tr> <th>ID</th><th> Name </th> <th>Course</th></tr>
<?php
include ('connection.php');
$result = pg_query($db_handle, "select * from tu_studentinfo");
	if($result)
	{
		while($row = pg_fetch_row($result))
		{
			echo "<tr>";
			echo "<td>".$row[0]."</td>";
			echo "<td>".$row[1]."</td>";
			echo "<td>".$row[2]."</td>";
			echo "</tr>";
		}
		
	}
	else
	{
		echo "Some error";
	}

?>
</table>
</body>
</html>