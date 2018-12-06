<?php

$Where = " WHERE 1 ";
$order = " Order By C_USERNAME ;";

if ($Fields) {
	$DefaultFields = $Fields;
}



if ($C_USERNAME) {
	$Where .= " AND (C_USERNAME = '$C_USERNAME' OR V_MAIL = '$C_USERNAME') ";
} else 
	$Where .= " AND (C_USERNAME = '$C_USERNAME' OR V_MAIL = '$C_USERNAME') ";


if ($V_PASSWORD) {
	$Where .= " AND V_PASSWORD = '$V_PASSWORD' ";
} else
	$Where .= " AND V_PASSWORD = '$V_PASSWORD' ";
	
if ($OrderBy) {
	$order = " ORDER BY {$OrderBy};";
}


if ($Mode == "Regular") {
	$SQL = "SELECT * FROM mst_user ";

} else {
	$SQL = "SELECT COUNT(*) as rowCount FROM mst_user ";
}


$SQL.=$Where.=$order;