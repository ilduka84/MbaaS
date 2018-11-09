<?php

require_once ("Object.php");
require_once ("User.php");
require_once __DIR__."/../interfaces/IEntity.php";

use Doctrine\Common\Collections\ArrayCollection;

/**
 * @Entity @Table(name = "instances")
 **/
class Instance implements IEntity
{
    /**
     * @var integer $id
     *
     * @Id @Column(name = "id", type = "integer") @GeneratedValue
     **/
    private $id;

    /**
     * @var Object $object
     *
     * Many Instances has One Object.
     * @ManyToOne(targetEntity="Object", inversedBy="instances", cascade={"persist"})
     * @JoinColumn(name="idObject")
     */
    private $object;

    /**
     * @var User $user
     *
     * Many Instances has One User.
     * @ManyToOne(targetEntity="User", inversedBy="instances", cascade={"persist"})
     * @JoinColumn(name="idUser")
     */
    private $user;

    public function __construct(Object $object, User $user)
    {

        $this->object = $object;
        $this->user = $user;
    }

    public function getId(): int
    {
    return $this->id;
    }
    public function setObject(Object $object)
    {
        $this->object = $object;
    }

    public function getObject(): Object
    {
        return $this->object;
    }

    public function setUser(User $user)
    {
        $this->user = $user;
    }

    public function getUser(): User
    {
        return $this->user;
    }

    public function setId(int $id)
    {
        $this->id = $id;
    }


}