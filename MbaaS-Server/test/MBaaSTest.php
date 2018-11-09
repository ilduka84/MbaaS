<?php
require_once __DIR__."/../models/MBaaS.php";
require_once __DIR__."/../models/JsonWebToken.php";

use PHPUnit\Framework\TestCase;

class MBaaSTest extends TestCase
{
    private $mbaas;

    public function setUp()
    {
        $this->mbaas = new MBaaS();
    }

    public function testLogin():void
    {

        $request = '{"user":"a","password":"b"}';
        $token = new JsonWebToken($this->mbaas->login($request));

        $this->assertTrue($token->validate());

    }

    public function testPut()
    {
        $request = '{"array":["a","b"]}';
        $loginRequest =  '{"user":"a","password":"b"}';
        $token = new JsonWebToken($this->mbaas->login($loginRequest));
        $id = $this->mbaas->put($request,$token->toString());

        $this->assertTrue(gettype($id)=="integer");
    }

}
