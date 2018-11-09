<?php

use Firebase\JWT\JWT;
require_once __DIR__."/../vendor/autoload.php";
require_once __DIR__."/../abstract/Token.php";
require_once __DIR__."/../configuration/config.php";
require_once __DIR__."/../interfaces/IMessageFactory.php";
require_once __DIR__."/../models/HtmlIMessageFactory.php";


class JsonWebToken extends Token
{


    public function __construct(string $jwt=null)
    {
        $this->token = $jwt;
        $this->message = call_user_func(MESSAGEFACTORY .'::getUnauthorized');
    }

    public function toString(): string
    {
        return (string)$this->token;
    }
    public function generate(string $userId): void
    {
        //for solving the problem with different clock between server
        JWT::$leeway = 5;

        //Generate token
        $tokenId    = base64_encode(random_bytes(32));
        $issuedAt   = time();
        $notBefore  = $issuedAt + 1;             //Adding 10 seconds
        $expire     = $notBefore + TIME;        // Adding TIME seconds
        $serverName = DB_HOST;                   // Retrieve the server name from config file

        /*
        * Create the token as an array
        */
        $data = [
            'iat'  => $issuedAt,         // Issued at: time when the token was generated
            'jti'  => $tokenId,          // Json Token Id: an unique identifier for the token
            'iss'  => $serverName,       // Issuer
            'nbf'  => $notBefore,        // Not before
            'exp'  => $expire,           // Expire
            'data' => [                  // Data related to the signer user
                'userId'   => $userId    // userid from the users table

            ]
        ];

        //key
        $secretKey = base64_decode(JWT_KEY);
        $jwt = JWT::encode(
            $data,      //Data to be encoded in the JWT
            $secretKey, // The signing key
            'HS512'     // Algorithm used to sign the token, see https://tools.ietf.org/html/draft-ietf-jose-json-web-algorithms-40#section-3
        );

        $this->token = $jwt;
    }
    public function validate(): bool
    {
        try {

            $secretKey = base64_decode(JWT_KEY);
            $token = JWT::decode($this->token, $secretKey, array('HS512'));

        } catch (Exception $e) {

        $this->message = call_user_func(MESSAGEFACTORY .'::getUnauthorized');
        return false;
        }
        $this->message = call_user_func(MESSAGEFACTORY .'::getStatusOk');
        return true;
    }

    public function getLastMessage(): Message
    {
        return $this->message;
    }

    public function getUserId():int
    {
        $secretKey = base64_decode(JWT_KEY);
        $tokenDec = JWT::decode($this->token, $secretKey, array('HS512'));
        $data=$tokenDec->{'data'};
        return $data->{'userId'};
    }
}


?>