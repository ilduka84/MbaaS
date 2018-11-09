<?php
require_once __DIR__."/../entities/Object.php";
require_once __DIR__."/../entities/Instance.php";
require_once __DIR__."/../models/JsonMapperIConverter.php";

class fromArrayOfInstancesToJson
{

    public static function convert(array $instances): string
    {
        $converter = new JsonMapperIConverter();
        try {
        $result = "[";
        foreach ($instances as $instance)
            $result .=
                "[".($instance->getId()-1).',{"'.$instance->getObject()->getName().'":'.$converter->toJson($instance)."}],";
        $result =substr($result,0,-1);
        $result.="]";
        return $result;
        }catch(Exception $e)
        {
            throw new Exception($e->getCode().": ".$e->getMessage());
            return null;
        }

    }
}