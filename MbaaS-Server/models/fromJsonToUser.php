<?php
require_once __DIR__."/../entities/User.php";

class fromJsonToUser
{
    public static function convert(string $json):User
    {
        $data = json_decode($json, true);

        if(($data==NULL) or !(array_key_exists("user",$data)) or !(array_key_exists("password",$data)))
            throw new \InvalidArgumentException("Impossible to convert: field user or password doesn't exist");

        $user = new User($data["user"], $data["password"]);
        return $user;
    }

}