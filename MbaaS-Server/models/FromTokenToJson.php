<?php
require_once __DIR__."/../abstract/Token.php";

class FromTokenToJson
{
    public static function convert(Token $object):string
    {
        return $object->toString();
    }

}