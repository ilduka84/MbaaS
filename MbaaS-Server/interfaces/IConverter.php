<?php
require_once __DIR__."/../interfaces/IEntity.php";

interface IConverter
{
    public function fromJson(string $objectName,string $json);
    public function toJson($object):string;

}