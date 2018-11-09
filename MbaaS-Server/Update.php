<?php
require_once ("models/MBaaS.php");
require_once ("models/Message.php");
require_once ("configuration/config.php");

use Zend\Http\PhpEnvironment\Request;

header('Content-Type: application/json');

$controller = new MBaaS();
$request = new Request();
$token = null;

if ($request->isPost()) {
    $authHeader = $request->getHeader('authorization');
    if ($authHeader) {
        list($token) = sscanf($authHeader->toString(), 'Authorization: Bearer %s');
    }
}
//SYNTAX FOR UPDATE [ID,INSTANCE]
if ($token) {
    $json = file_get_contents("php://input");
    try {
        $id = json_decode($json)[0];
        $instance = json_decode($json)[1];
        $instanceJsonFormat = json_encode($instance);
        $controller->update($instanceJsonFormat, $token, $id);
        header(call_user_func(MESSAGEFACTORY . '::getStatusOk')->getMessage());
        exit;
    }catch(Exception $e) {
        $result = json_encode(array("Exception" => $e->getMessage()));
        header(call_user_func(MESSAGEFACTORY . '::getBadRequest')->getMessage());
        echo($result);
        exit;
    }
}
else{
    header(call_user_func(MESSAGEFACTORY .'::getUnauthorized')->getMessage());
    exit;
}
