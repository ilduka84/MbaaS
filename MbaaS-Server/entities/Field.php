<?php
require_once ("Object.php");
require_once ("Value.php");
require_once __DIR__."/../interfaces/IEntity.php";


use Doctrine\Common\Collections\ArrayCollection;
/**
 * @Entity @Table(name = "fields")
 **/
class Field implements IEntity
{
    /**
     * @var integer $id
     *
     * @Id @Column(name = "id", type = "integer") @GeneratedValue**/
    private $id;

    /**
     * @var Object $object
     *
     * Many Field has One Object.
     * @ManyToOne(targetEntity = "Object", inversedBy = "fields")
     * @JoinColumn(name = "idObject")
     */
    private $object;

    /**
     * @var string $name
     *
     * @Column(name = "name", type = "string", length = 20)**/
    private $name;

    /**
     * @var string $type
     *
     * @Column(name = "type", type = "string", length = 20)**/
    private $type;

    /**
     * @var $values[]
     * @OneToMany(targetEntity="Value", mappedBy="field", cascade={"persist","remove"})
     */
    private $values;


    public function __construct(string $name=null, string $type=null, Object $object)
    {
        $this->values = new ArrayCollection();
        $this->object = $object;
        $this->name = $name;
        $this->type = $type;
        $this->object->addField($this);
    }

    public function getId(): int
    {
        return $this->id;
    }

    public function getName(): string
    {
        return $this->name;
    }

    public function getType(): string
    {
        return $this->type;
    }

    public function getObject(): Object
    {
        return $this->object;
    }

    public function getValues()
    {
        return $this->values;
    }

    public function getValue()
    {
        return $this->getValues()[0];
    }

    public function setName(string $name)
    {
        $this->name = $name;
    }



    public function setType(string $type)
    {
        $this->type = $type;
    }

    public function setObject(Object $object)
    {
        $this->object = $object;
    }

    public function addValue(Value $value=null,array $values = null)
    {
        if ($value != null)     $this->values[] = $value;
        if ($values != null)    $this->addArrayOfValue($values);

    }
    public function setValue(Value $value)
    {
        $this->values[0] = $value;
    }

    private function addArrayOfValue(array $values)
    {
        foreach ($values as $value) {
            if ($values instanceof Value) $this->value[] = $value;
            else throw new \InvalidArgumentException("array contains not type Value");
        }
    }

}