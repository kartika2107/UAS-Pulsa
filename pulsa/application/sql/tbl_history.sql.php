<?php

$Where = " WHERE 1 ";
$order = " Order By N_TRANSAKSI ;";

if ($Fields) {
	$DefaultFields = $Fields;
}

if ($C_USERNAME) {
	$Where .= " AND C_USERNAME = '{$C_USERNAME}' ";
} else
	$Where .= " AND C_USERNAME = '{$C_USERNAME}' ";


if ($OrderBy) {
	$order = " ORDER BY {$OrderBy}";
}


if ($Mode == "Regular") {
	$SQL = "SELECT * FROM `tbl_trans`";

} else {
	$SQL = "SELECT COUNT(*) FROM `tbl_trans`";
}



$SQL.=$Where.=$order;
