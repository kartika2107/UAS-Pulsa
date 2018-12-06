<?php

$Where = " WHERE 1 ";
$order = " Order By d.C_ID ";

if ($Fields) {
	$DefaultFields = $Fields;
}

if ($C_USERNAME) {
	$Where.=" AND C_USERNAME = '$C_USERNAME' ";
}

if ($OrderBy) {
	$order = " ORDER BY {$OrderBy}";
}


if ($Mode == "Regular") {
	$SQL = "SELECT * FROM (
				SELECT a.*, b.`N_SELL` AS nominal FROM tbl_cart a
				JOIN mst_paket_data b ON a.C_ID=b.C_ID

				UNION

				SELECT a.*, b.N_SELL AS nominal FROM tbl_cart a
				JOIN mst_pulsa b ON a.C_ID=b.C_ID

				UNION 

				SELECT a.*, b.N_SELL AS nominal FROM tbl_cart a
				JOIN mst_token b ON a.C_ID=b.C_ID
			) d ";

} else {
	$SQL = "
	SELECT COUNT(*) FROM (
			SELECT a.*, b.`N_SELL` AS nominal FROM tbl_cart a
			JOIN mst_paket_data b ON a.C_ID=b.C_ID

			UNION

			SELECT a.*, b.N_SELL AS nominal FROM tbl_cart a
			JOIN mst_pulsa b ON a.C_ID=b.C_ID

			UNION 

			SELECT a.*, b.N_SELL AS nominal FROM tbl_cart a
			JOIN mst_token b ON a.C_ID=b.C_ID
		) d";
}

$SQL.=$Where.=$order;

