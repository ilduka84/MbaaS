<?php

require_once ("models/JsonWebToken.php");
use PHPUnit\Framework\TestCase;

class JsonWebTokenTest extends TestCase
{
    private $json;

    protected function setUp(){
        $this->json = new JsonWebToken();
    }
    public function testValidationTrue(): void
    {
        $token = $this->json;
        $token->generate("123");
        $this->assertTrue($token->validate());
    }
    public function testValidationFalse(): void
    {
        $token = $this->json;

        $this->assertFalse($token->validate());
    }
}
