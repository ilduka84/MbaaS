<?php



interface IMessageFactory
{
    public static function getStatusOk(): Message;
    public static function getUnauthorized():Message;
    public static function getBadRequest():Message;
    public static function getMethodNotAllowed():Message;

}