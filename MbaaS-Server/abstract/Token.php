<?php

abstract class Token
{
    protected $token;
    protected $message;

    public function set(string $token){
        $this->token = $token;
    }
    abstract public function toString(): string ;
    abstract public function generate(string $userId): void;
    abstract public function validate(): bool ;
    abstract public function getLastMessage(): Message;
    abstract public function getUserId():int;

}

?>