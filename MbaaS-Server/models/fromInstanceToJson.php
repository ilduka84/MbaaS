<?php
require_once __DIR__."/../entities/User.php";
require_once __DIR__."/../entities/Object.php";
require_once __DIR__."/../entities/Instance.php";
require_once __DIR__."/../entities/Field.php";

class fromInstanceToJson
{
    public static function convert(Instance $instance): string
    {
        $json = "";
        $fields = $instance->getObject()->getFields();

        $instanceIsArray = true;
        $numberOfField = 0;
        foreach ($fields as $field)
        {
            $fieldName = $field->getName();
            $instanceIsArray = $instanceIsArray && ($fieldName == (string)$numberOfField++);
        }
        if($instanceIsArray) $json.='[';
        else $json.='{';

        foreach ($fields as $field)
        {   $fieldIsObject = ($field->getType() == "object");
            $fieldIsArray =($field->getType() == "array");
            $fieldName = $field->getName();
            if (($fieldIsObject)or($fieldIsArray)) $value = self::convert($field->getValues()[0]->getValue());
            else $value = $field->getValues()[0]->getValue();
            if(!$instanceIsArray and !$fieldIsArray ) $json.='"'.$fieldName.'"'.":";
            if ($field->getType()=="string") $json.='"'.$value.'"'.",";
            else $json.=$value.",";

        }
        $json = substr($json,0,-1);
        if($instanceIsArray) $json.=']';
        else $json.='}';

        return $json;
    }

}