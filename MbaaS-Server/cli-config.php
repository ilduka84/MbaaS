<?php
use Doctrine\ORM\Tools\Console\ConsoleRunner;
require_once 'configuration/bootstrap.php';

return ConsoleRunner::createHelperSet($entity_manager);