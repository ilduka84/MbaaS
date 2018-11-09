<?php

require_once __DIR__."/../entities/Object.php";
require_once __DIR__."/../configuration/bootstrap.php";

use PHPUnit\Framework\TestCase;

class ObjectTest extends TestCase
{
    private $object;
    private $entityManager;

    protected function setUp()
    {
        $this->entityManager = new EManager();
        $this->entityManager = $this->entityManager->getEntityManager();

    }
    public function testInsert(): void
    {
        $object = new Object("PROVA");
        $this->entityManager->persist($object);
        $this->entityManager->flush();
        $this->assertEquals($this->entityManager->find("Object",$object->getId()),$object);
    }
    public function testDelete():void
    {
        $object = new Object("CIAO");
        $this->entityManager->persist($object);
        $this->entityManager->flush();
        $this->assertEquals($this->entityManager->find("Object", $object->getId()),$object);

        $this->entityManager->remove($object);
        $this->entityManager->flush();

        $this->assertNull($object->getId());
    }
    public function testUpdate():void
    {
        $object = new Object("CIAO");
        $this->entityManager->persist($object);
        $this->entityManager->flush();
        $this->assertEquals($this->entityManager->find("Object", $object->getId()),$object);

        $object->setName("HELLO");
        $this->entityManager->flush();

        $this->assertEquals($this->entityManager->find("Object", $object->getId()),$object);

    }
    public function testFields():void
    {
    $object = new Object("Con Field");
    $field = new Field("Field1" ,"integer", $object );
    $this->entityManager->persist($object);
    $this->entityManager->persist($field);
    $this->entityManager->flush();

    $this->assertEquals($object->getFields()[$field->getName()], $field);
    }


}
