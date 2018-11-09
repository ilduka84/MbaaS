<?php


class Message
{
    protected $code;
    protected $message;


    public function __construct(int $code = null, string $message = null)
    {
        $this->code = $code;
        $this->message = $message;
    }

    public function setCode(int $code)
    {
        $this->code = $code;
    }


    public function getCode():int
    {
        return $this->code;
    }


    public function getMessage():string
    {
        return $this->message;
    }


    public function setMessage(string $message)
    {
        $this->message = $message;
    }
}