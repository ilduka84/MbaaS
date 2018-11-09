<?php
require_once ("Instance.php");
require_once __DIR__."/../interfaces/IEntity.php";

use Doctrine\Common\Collections\Collection;
use Doctrine\Common\Collections\ArrayCollection;

/**
 * @Entity @Table(name = "users")
 **/
class User implements IEntity
{
    /**
     * @var integer $id
     *
     * @Id @Column(name = "id", type = "integer") @GeneratedValue**/
    private $id;
    /**
     * @var string $name
     *
     * @Column(name = "name", type = "string", length = 20)**/
    private $name;
    /**
     * @var string $password
     *
     * @Column(name = "password", type = "string", length = 20)**/
    private $password;

    /**
     * @var ArrayCollection $instances
     * @OneToMany(targetEntity="Instance", mappedBy="user", cascade={"remove"})
     */
    private $instances;


    public function __construct(string $userName, string $password)
    {
        $this->name = $userName;
        $this->password = $password;
        $this->instances = new ArrayCollection();
    }

    public function getPassword():string
    {
        return $this->password;
    }

    public function setPassword(string $password)
    {
        $this->password = $password;
    }

    public function getName():string
    {
        return $this->name;
    }

    public function setName(string $name)
    {
        $this->name = $name;
    }

    public function getId(): int
    {
        return $this->id;
    }

    /*public function addInstance(Instance $instance):void
    {
        $this->instances[] = $instance;
    }
*/
    public function getInstances()
    {
        return $this->instances->toArray();
    }

    public function setId(int $id)
    {
        $this->id = $id;
    }
}