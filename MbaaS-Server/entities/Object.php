<?php

require_once ("Field.php");
require_once ("Instance.php");
require_once __DIR__."/../interfaces/IEntity.php";

use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
/**
 * @Entity @Table(name = "objects")
 **/
class Object implements IEntity
{    /**
     * @var int $id
     *
     * @Id @Column(name = "id", type = "integer") @GeneratedValue**/
    private $id;
    /**
     * @var string $name
     *
     * @Column(name = "name", type = "string", length = 20)**/
    private $name;

    /**
     * @var $fields[]
     * @OneToMany(targetEntity="Field", mappedBy="object", cascade={"persist", "remove"}, indexBy="name")
     */
    private $fields;

    /**
     * @var $instances[]
     * @OneToMany(targetEntity="Instance", mappedBy="object", cascade={"persist","remove"})
     */
    private $instances;

    public function __construct(string $name = null)
    {
        $this->instances = new ArrayCollection();
        $this->fields =  new ArrayCollection();
        $this->name = $name;
    }

    public function getName()
    {
        return $this->name;
    }

    public function setName($name)
    {
        $this->name = $name;
    }

    public function getId():int
    {
        return $this->id;
    }

    public function getFields()
    {
        return $this->fields;
    }

    public function getField($name)
    {
        if (!isset($this->fields[$name])) {
            throw new \InvalidArgumentException("No field for the object");
        }

        return $this->fields[$name];
    }

    public function addField(Field $field)
    {
        $this->fields[$field->getName()] = $field;
    }

    public function getInstances()
    {
        return $this->instances;
    }

    public function setId(int $id)
    {
        $this->id = $id;
    }

}