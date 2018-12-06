<?php

$Where = " WHERE 1 ";
$order = " Order By C_ID ;";

if ($Fields) {
	$DefaultFields = $Fields;
}

if ($OrderBy) {
	$order = " ORDER BY {$OrderBy};";
}


if ($Mode == "Regular") {
	$SQL = "SELECT * FROM mst_pulsa ";

} else {
	$SQL = "SELECT COUNT(*) as rowCount FROM mst_pulsa ";
}


$SQL.=$Where.=$order;