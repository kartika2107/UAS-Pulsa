<?php

// print_r($_SERVER);
error_reporting(E_ALL & ~E_NOTICE & ~E_WARNING);

ini_set("session.gc_maxlifetime", 1800);
// Set the session cookie to timout
ini_set("session.cookie_lifetime", 1800);

include('core/includes.php');
include('application/'.DEFAULT_CONTROLLER.'.php');
require_once 'lib/XmlRequest.php';
require_once 'lib/JsonRequest.php';
require_once 'lib/FormDataRequest.php';
require_once 'lib/FormUrlEncodedRequest.php';


$app = new mobile();
$app->addRequestHandler('application/xml', new XmlRequest);
$app->addRequestHandler('application/json', new JsonRequest);
$app->addRequestHandler('multipart/form-data', new FormDataRequest);
$app->addRequestHandler('application/x-www-form-urlencoded', new FormUrlEncodedRequest);

$app->post('/register', function($request) use($app) {
	$model = $app->load->model('mst_user');

	return $model->putData($request->body);
});

$app->post('/login', function($request) use($app) {
	$model = $app->load->model('mst_user');
	$retData = $model->getData($request->body);
	if (!$retData->RowCount) {
		return array('success' => false, 'reason' => 'Wrong username or password');
	}
	return array('success'=>true, 'profile'=>$retData->DataRow[0]);
});

$app->post('/profile', function($request) use($app) {
	$model = $app->load->model('mst_user');
	$retData = $model->getData($request->body);
	return array('success'=>true, 'profile'=>$retData->DataRow[0]);
});

$app->post('/changeprofile', function($request) use($app) {
	$model = $app->load->model('mst_user');
	return $model->putData($request->body);
});


$app->post('/cart', function($request) use($app) {
	$model = $app->load->model('tbl_cart');
	$retData = $model->getData($request->body);
	return array('success'=>true, 'DataRow'=>$retData->DataRow);
});

$app->post('/changecart', function($request) use($app) {
	$model = $app->load->model('tbl_cart');
	return $model->putData($request->body);
});

$app->post('/history', function($request) use($app) {
	$model = $app->load->model('tbl_trans');
	$retData = $model->getData($request->body);
	return array('success'=>true, 'DataRow'=>$retData->DataRow);
});	

$app->post('/trans', function($request) use($app) {
	$model = $app->load->model('tbl_trans');
	$model->putData($request->body);
	$request->body->L_DELETE = 1;
	return $app->load->model('tbl_cart')->putData($request->body);
});



$app->post('/paketdata', function($request) use($app) {
	$model = $app->load->model('mst_paket_data');
	return $model->getData($request->body);
});

$app->post('/pulse', function($request) use($app) {
	$model = $app->load->model('mst_pulsa');
	return $model->getData($request->body);
});

$app->post('/token', function($request) use($app) {
	$model = $app->load->model('mst_token');
	return $model->getData($request->body);
});



$app->run();