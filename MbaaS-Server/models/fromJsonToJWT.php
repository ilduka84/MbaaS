<?php
require_once __DIR__."/../models/JsonWebToken.php";

class fromJsonToJWT
{
    public static function convert(string $jwt):JsonWebToken
    {

        $token = new JsonWebToken($jwt);
        if($token->validate()) return $token;
        else throw new \InvalidArgumentException("Token not valid");

    }
}