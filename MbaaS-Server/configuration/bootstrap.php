<?php
/** Contain $entitymanager for handle the entities with doctrine
 **/
require_once __DIR__."/../vendor/autoload.php";
require_once "config.php";

use Doctrine\ORM\Tools\Setup;
use Doctrine\ORM\EntityManager;
use Doctrine\ORM\Configuration;

class EManager
{
    private $entityManager;
    public function __construct()
    {
        $paths = array("../entities");
        // Create a simple "default" Doctrine ORM configuration for Annotations
        $isDevMode = true;


        // database configuration parameters
        $conn = array(
        'driver' => 'mysqli',
        'user' => DB_USER,
        'password' => DB_PASSWORD,
        'dbname' => DB_DATABASE,
        'host' => DB_HOST
        );

        $config = Setup::createAnnotationMetadataConfiguration($paths, $isDevMode);
        // obtaining the entity manager
        $this->entityManager = EntityManager::create($conn, $config);
        //$config = new Configuration;
        //$mode = "Doctrine\Common\Proxy\AbstractProxyFactory::AUTOGENERATE_FILE_NOT_EXISTS";
        //$config->setAutoGenerateProxyClasses($mode);
    }

    public function getEntityManager()
    {
    return $this->entityManager;
    }

}