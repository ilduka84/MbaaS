<?php
require_once __DIR__."/../configuration/bootstrap.php";
require_once __DIR__."/../entities/Object.php";
require_once __DIR__."/../entities/Field.php";
require_once __DIR__."/../entities/User.php";
require_once __DIR__."/../entities/Instance.php";
require_once __DIR__."/../entities/Value.php";

use PHPUnit\Framework\TestCase;

class InstanceTest extends TestCase
{
    private $user;
    private $object;
    private $instance;
    private $entityManager;

    protected function setUp()
    {
        $this->entityManager = new EManager();
        $this->entityManager = $this->entityManager->getEntityManager();

        $this->user = new User("administrator", "asdasd");

        $this->object = new Object("object for instance");
        $field = new Field("field1", "string", $this->object);
        $this->object->addField($field);

        $this->entityManager->persist($this->object);
        $this->entityManager->persist($this->user);
        $this->entityManager->flush();

        $this->instance = new Instance($this->object, $this->user);

        $this->entityManager->persist($this->instance);
        $this->entityManager->flush();

    }

    public function testAddInstance():void
    {


        $this->assertEquals($this->instance, $this->entityManager->find("Instance", $this->instance->getId()));
    }

    public function testRemoveInstance()
    {
        $fields = $this->object->getFields();

        foreach ( $fields as $name=>$value) {
            $value = new Value($value, $this->instance, "value1");
            $this->entityManager->persist($value);
            $this->entityManager->flush();
        }
        $idValue = $value->getId();
        $this->assertNotNull($idValue);
        $idInstance = $this->instance->getId();
        $this->assertNotNull($idInstance);

        $this->entityManager->remove($this->instance);
        $this->entityManager->flush();

        $this->assertNull($this->entityManager->find("Instance", $idInstance));
        $this->assertNull($this->entityManager->find("Value", $idValue));

    }
}
