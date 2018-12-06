<?php

class tbl_cart extends model {
	function __construct() {
		parent::__construct();
		$this->TableName = "tbl_cart";
		$this->SQLFile = "tbl_cart.sql";	
	}

	function putData($parameter) {
		if ($parameter->L_DELETE) {
			return parent::deleteData($parameter);
		}
		return parent::putData($parameter);
	}

}