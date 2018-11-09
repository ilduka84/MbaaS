<?php
require_once __DIR__."/../entities/User.php";
require_once __DIR__."/../entities/Object.php";
require_once __DIR__."/../entities/Instance.php";
require_once __DIR__."/../entities/Field.php";
require_once __DIR__."/../configuration/bootstrap.php";
require_once __DIR__."/../interfaces/IEntity.php";

class PersistanceFacade
{
    private $entityManager;

    public function __construct()
    {
        $this->entityManager = new EManager();
        $this->entityManager = $this->entityManager->getEntityManager();
    }

    public function get(string $entityName, int $id)
    {
        return $this->entityManager->find($entityName, $id);
    }

    public function put(IEntity $entity): int
    {
        $this->entityManager->persist($entity);
        $this->entityManager->flush();
        return $entity->getId();
    }

    public function update(IEntity $entityNew)
    {
        $entityOld = $this->get(get_class($entityNew), $entityNew->getId());
        if ($entityOld != null) {
            if (get_class($entityNew) == "Instance"){
                $this->updateInstance($entityOld, $entityNew);
                return;
            }
            if (get_class($entityNew) == "User") return;
            if (get_class($entityNew) == "Object") return;
        }
        throw new \InvalidArgumentException("The id of entity doesn't exist. Make sure you have insert a correct id into entity");
    }

    private function updateInstance(Instance $instanceOld, Instance $instanceNew)
    {
        $fieldsOld = $instanceOld->getObject()->getFields();
        $fieldsNew = $instanceNew->getObject()->getFields();
        foreach ($fieldsOld as $fieldOld) {
            $fieldNew = $fieldsNew[$fieldOld->getName()];
            $valueOld = $fieldOld->getValue();
            $valueNew = $fieldNew->getValue();
            if ($fieldOld->getType() == "object") $this->updateInstance($valueOld->getValue(), $valueNew->getValue());
            else {
                if ($valueOld->getValue() != $valueNew->getValue()) $valueOld->setValue($valueNew->getValue());
            }
        }
        $this->entityManager->flush();
        return;
    }

    public function delete(IEntity $entity)
    {
        if ($entity != null) {
            $this->entityManager->remove($entity);
            $this->entityManager->flush();
            return;
        }
        throw new \InvalidArgumentException("The id of entity doesn't exist. Make sure you have insert a correct id into entity");
    }

    public function isUserExist(User $user):bool
    {
        $query = $this->entityManager->createQuery
        ('SELECT u.id FROM User u WHERE u.name = :name and u.password = :password');
        $query->setParameters(array('name'=>$user->getName(),
                                    'password'=>$user->getPassword()));
        return ($query->getResult()!=null);
    }

    public function getUserId(User $user):int
    {
        $query = $this->entityManager->createQuery
        ('SELECT u.id FROM User u WHERE u.name = :name and u.password = :password');
        $query->setParameters(array('name'=>$user->getName(),
            'password'=>$user->getPassword()));
        if ($query->getResult()!=null) return $query->getResult()[0]['id'];
        else throw new \InvalidArgumentException("User doesn't exist");
    }

    public function getAllInstancesFromName(string $name)
    {
        $query = $this->entityManager->createQuery
        ('SELECT o FROM Object o WHERE o.name = :name');
        $query->setParameters(array('name'=>$name));

        $results = $query->getResult();


        $instances = array();
        foreach($results as $object)
            foreach ($object->getInstances() as $instance) $instances[] = $instance;

        if ($results!=null) return $instances;
        else return null;
    }

}