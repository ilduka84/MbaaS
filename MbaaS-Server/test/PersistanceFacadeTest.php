<?php
require_once __DIR__."/../entities/User.php";
require_once __DIR__."/../entities/Object.php";
require_once __DIR__."/../entities/Instance.php";
require_once __DIR__."/../entities/Field.php";

require_once __DIR__."/../models/fromJsonToInstance.php";
require_once __DIR__."/../models/PersistanceFacade.php";
require_once __DIR__."/../models/JsonMapperIConverter.php";

use PHPUnit\Framework\TestCase;

class PersistanceFacadeTest extends TestCase
{
    private $persistanceFacade;
    private $converter;

    public function setUp()
    {
        $this->persistanceFacade = new PersistanceFacade();
        $this->converter = new JsonMapperIConverter();
    }

    public function testUpdate()
    {
        $user = new User("james", "123");
        $idUser = $this->persistanceFacade->put($user);
        $jsonOld = '[{"object1":{"field1":"1","field2":"2"}},1,3]';
        $jsonNewExpected = '[{"object1":{"field1":"2","field2":"3"}},2,1]';
        $instanceOld = $this->converter->fromJson("Instance", $jsonOld, $user);
        $idInstanceOld = $this->persistanceFacade->put($instanceOld);

        $this->assertEquals($this->converter->toJson($instanceOld),$jsonOld);

        $instanceNew = $this->converter->fromJson("Instance",$jsonNewExpected, $user);
        $instanceNew->setId($idInstanceOld);

        $this->persistanceFacade->update($instanceNew);

        $instanceNewFromDb = $this->persistanceFacade->get("Instance",$idInstanceOld);

        $this->assertEquals($this->converter->toJson($instanceNewFromDb),$jsonNewExpected);
    }

    public function testGetAllInstancesFromName()
    {
        $name =   "instance5";
        $instanceNumber = 11;
        $result = $this->persistanceFacade->getAllInstancesFromName($name);

        $this->assertEquals(count($result), $instanceNumber);
    }

}
