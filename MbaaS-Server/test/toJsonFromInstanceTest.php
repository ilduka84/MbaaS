<?php
require_once __DIR__."/../configuration/bootstrap.php";
require_once __DIR__."/../entities/Object.php";
require_once __DIR__."/../entities/Field.php";
require_once __DIR__."/../entities/User.php";
require_once __DIR__."/../entities/Instance.php";
require_once __DIR__."/../entities/Value.php";
require_once __DIR__ . "/../models/fromInstanceToJson.php";

use PHPUnit\Framework\TestCase;

class toJsonFromInstanceTest extends TestCase
{
    private $user;

    protected function setUp()
    {
        $this->user = new User("james", "123");
    }

    public function testJsonSimpleObject():void
    {
        $jsonExpected = '{"field1":2,"field2":"123"}';

        $object = new Object();
        $field1 = new Field("field1","integer", $object );
        $field2 = new Field("field2", "string", $object);
        $instanceCreated = new Instance($object, $this->user);
        $value1 = new Value($field1, $instanceCreated, 2);
        $value2 = new Value($field2, $instanceCreated, "123");

        $jsonCreated = fromInstanceToJson::convert($instanceCreated);

        $this->assertEquals($jsonExpected,$jsonCreated);
    }

    public function testJsonSimpleArray():void
    {
        $jsonExpected = '[0,1,2]';

        $object = new Object();
        $field1 = new Field("0","integer", $object );
        $field2 = new Field("1", "integer", $object);
        $field3 = new Field("2", "integer", $object);
        $instanceCreated = new Instance($object, $this->user);
        $value1 = new Value($field1, $instanceCreated, 0);
        $value2 = new Value($field2, $instanceCreated, 1);
        $value3 = new Value($field3, $instanceCreated, 2);

        $jsonCreated = fromInstanceToJson::convert($instanceCreated);

        $this->assertEquals($jsonExpected,$jsonCreated );
    }

    public function testJsonObjectArray():void
    {
        $jsonExpected = '{"array":[0,1,2]}';


        $object1 = new Object("array");
        $field0 = new Field("0", "integer", $object1);
        $field1 = new Field("1", "integer", $object1);
        $field2 = new Field("2", "integer", $object1);
        $instance1 = new Instance($object1, $this->user);
        $value1 = new Value($field0, $instance1, 0);
        $value2 = new Value($field1, $instance1, 1);
        $value3 = new Value($field2, $instance1, 2);
        $object = new Object();
        $field = new Field("array","object", $object);
        $instance = new Instance($object, $this->user);
        $value = new Value($field,$instance,$instance1);
        $jsonCreated = fromInstanceToJson::convert($instance);

        $this->assertEquals($jsonExpected,$jsonCreated );
    }

    public function testJsonObjectIntoObject():void
    {
        $jsonExpected = '{"object1":{"field1":"1","field2":"2"}}';

        $object1 = new Object("object1");
        $field1 = new Field("field1", "string", $object1);
        $field2 = new Field("field2", "string", $object1);
        $instance1Created = new Instance($object1, $this->user);
        $value1 = new Value($field1, $instance1Created, "1");
        $value2 = new Value($field2, $instance1Created, "2");

        $object = new Object();
        $field = new Field("object1","object", $object );
        $instanceCreated = new Instance($object, $this->user);
        $value = new Value($field, $instanceCreated, $instance1Created);

        $jsonCreated = fromInstanceToJson::convert($instanceCreated);

        $this->assertEquals($jsonExpected,$jsonCreated );
    }

    public function testJsonObjectIntoArray():void
    {
        $jsonExpected = '[{"object1":{"field1":"1","field2":"2"}},1,2]';

        $object1 = new Object("object1");
        $field1 = new Field("field1", "string", $object1);
        $field2 = new Field("field2", "string", $object1);
        $instanceObject1Created = new Instance($object1, $this->user);
        $value1 = new Value($field1, $instanceObject1Created, "1");
        $value2 = new Value($field2, $instanceObject1Created, "2");

        $innerObject0 = new Object("0");
        $innerFieldObject0 = new Field("object1","object",$innerObject0);
        $innerInstance0 = new Instance($innerObject0,$this->user);
        $innerValue0 = new Value($innerFieldObject0,$innerInstance0,$instanceObject1Created);

        $array = new Object();
        $field0 = new Field("0","object",$array);
        $field1 = new Field("1", "integer", $array);
        $field2 = new Field("2", "integer", $array);
        $arrayInstance = new Instance($array, $this->user);
        $value0 = new Value($field0, $arrayInstance, $innerInstance0);
        $value1 = new Value($field1, $arrayInstance, 1);
        $value2 = new Value($field2, $arrayInstance, 2);

        $jsonCreated = fromInstanceToJson::convert($arrayInstance);

        $this->assertEquals($jsonExpected,$jsonCreated );
    }

}
