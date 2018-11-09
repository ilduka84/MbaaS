<?php

require_once ("Message.php");

class HtmlIMessageFactory implements IMessageFactory
{
    public static function getStatusOk(): Message
    {
        return new Message(200, 'HTTP/1.0 200 Status Ok');
    }
    public static function getBadRequest(): Message
    {
        return new Message(400, 'HTTP/1.0 400 Bad Request');
    }
    public static function getUnauthorized(): Message
    {
        return new Message(401, 'HTTP/1.0 401 Unauthorized');
    }
    public static function getMethodNotAllowed(): Message
    {
        return new Message(405, 'HTTP/1.0 405 Method Not Allowed');
    }

}