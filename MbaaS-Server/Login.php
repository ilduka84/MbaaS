<?php
require_once ("models/MBaaS.php");
require_once ("models/Message.php");
require_once ("configuration/config.php");

header('Content-Type: application/json');

$controller = new MBaaS();
$json = file_get_contents("php://input");
try {
    $token = $controller->login($json);
    $result = json_encode(array($token));
}catch(Exception $e){
    $result = json_encode(array("Exception"=>$e->getMessage()));
    header(call_user_func(MESSAGEFACTORY .'::getUnauthorized')->getMessage());
    echo($result);
    exit;
}

header(call_user_func(MESSAGEFACTORY .'::getStatusOk')->getMessage());
echo($result);