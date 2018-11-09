<?php
require_once ("fromJsonToInstance.php");
require_once ("fromJsonToUser.php");
require_once ("fromJsonToJWT.php");
require_once("fromInstanceToJson.php");
require_once("fromTokenToJson.php");
require_once("fromArrayOfInstancesToJson.php");
require_once __DIR__."/../interfaces/IConverter.php";
require_once __DIR__."/../interfaces/IEntity.php";

class JsonMapperIConverter implements IConverter
{
    public function fromJson(string $objectName, string $json,User $user=null)
    {
        if(($objectName == "Instance")and($user!=null)) return fromJsonToInstance::convert($json, $user);
        if($objectName == "User") return fromJsonToUser::convert($json);
        if($objectName == "Token") return fromJsonToJWT::convert($json);

        throw new \InvalidArgumentException("Invalid objectName: ".$objectName);
    }

    public function toJson($entity):string
    {
        if (is_array($entity)) return fromArrayOfInstancesToJson::convert($entity);
        if ($entity == null) throw new \InvalidArgumentException("Entity null, impossible to convert");
        if(get_class($entity) == "Instance") return fromInstanceToJson::convert($entity);
        //if(get_class($object) == "User") return toJsonfromInstance($object);
        if(get_class($entity) == "Token") return fromTokenToJson::convert($entity);

        throw new \InvalidArgumentException("Invalid Entity");

    }

}