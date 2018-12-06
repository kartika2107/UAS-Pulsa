<?php

class mst_token extends model {
	function __construct() {
		parent::__construct();
		$this->TableName = "mst_token";
		$this->SQLFile = "mst_token.sql";
	}




}