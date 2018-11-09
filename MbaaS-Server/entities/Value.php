<?php

require_once ("Instance.php");
require_once ("Field.php");
require_once __DIR__."/../interfaces/IEntity.php";

/**
 *
 * @Entity @Table(name = "valves")
 **/
class Value implements IEntity
{
    /**
     * @var integer $id
     *
     * @Id @Column(name = "id", type = "integer") @GeneratedValue**/
    private $id;

    /**
     * @var Instance $instance
     *
     * Many value has One Instance.
     * @ManyToOne(targetEntity = "Instance", inversedBy = "values")
     * @JoinColumn(name = "idInstance")
     */
     private $instance;

    /**
     * @var Field $field
     *
     * Many value has One Field.
     * @ManyToOne(targetEntity = "Field", inversedBy = "values")
     * @JoinColumn(name = "idField")
     */
    private $field;

    /**
     * @var $value
     *
     * @Column(name = "value", type = "string")
     **/
    private $value = null;

    /**
     * @var Instance $valueInstance
     *
     * One Value might be an Instance.
     * @OneToOne(targetEntity = "Instance" ,cascade={"persist"})
     * @JoinColumn(name="valueInstance", referencedColumnName="id")
     */
    private $valueInstance = null;


    public function __construct(Field $field,Instance $instance,$value)
    {
        if($field->getType() == gettype($value)) {
            if((gettype($value)== "object")or(gettype($value)=="array")) $this->valueInstance = $value;
            else $this->value = $value;
            if($field->getObject()->getName() == $instance->getObject()->getName()) {
                $this->instance = $instance;
                $this->field = $field;
                $this->field->addValue($this);
                }
            else throw new \InvalidArgumentException("Field and Instance don't belong same object");
        }
        else throw
        new \InvalidArgumentException("Different type ".$field->getType()." ". $field->getName()." and Value: ".var_dump($value));
    }

    public function getId(): int
    {
        return $this->id;
    }

    public function setValue($value)
    {
        if(gettype($value)=="object") $this->valueInstance = $value;
        else $this->value = $value;
    }

    public function setInstance(Instance $instance)
    {
        $this->instance = $instance;
    }

    public function getValue()
    {
        if(!is_null($this->value)) return $this->value;
        if(!is_null($this->valueInstance))  return $this->valueInstance;
    }

}