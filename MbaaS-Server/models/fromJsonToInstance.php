<?php
require_once __DIR__."/../entities/User.php";
require_once __DIR__."/../entities/Object.php";
require_once __DIR__."/../entities/Instance.php";
require_once __DIR__."/../entities/Field.php";

class fromJsonToInstance
{

    public static function convert($json, User $user): Instance
    {
        if (gettype($json) == "string") $data = json_decode($json, false);
        else $data = $json;
        if ($data == NULL) throw new \InvalidArgumentException("Impossible to convert: ".var_dump($json));

        $object = new Object();
        $instance = new Instance($object, $user);
        foreach ($data as $field => $value) {
            $fieldObject = new Field($field, gettype($value), $object);
            if ((gettype($value) == "object") or (gettype($value) == "array")) {
                $fieldObject->setType("object");
                $value = self::convert($value, $user);
                $value->getObject()->setName($fieldObject->getName());
            }

            $valueObject = new Value($fieldObject, $instance, $value);

        }
        return $instance;
    }
}