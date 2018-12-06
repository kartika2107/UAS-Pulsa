<?php

class tbl_trans extends model {
	function __construct() {
		parent::__construct();
		$this->SQLFile = "tbl_history.sql";
		$this->TableName = "tbl_trans";
	}


}