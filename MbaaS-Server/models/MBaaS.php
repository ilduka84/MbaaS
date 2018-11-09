<?php
require_once __DIR__."/../models/JsonMapperIConverter.php";
require_once __DIR__."/../interfaces/IConverter.php";
require_once __DIR__."/../entities/User.php";
require_once __DIR__."/../models/PersistanceFacade.php";
require_once __DIR__."/../abstract/Token.php";
require_once __DIR__."/../models/JsonWebToken.php";


class MBaaS
{
    private $converter;
    private $persistanceFacade;

    public function __construct()
    {
        $this->converter = new JsonMapperIConverter();
        $this->persistanceFacade = new PersistanceFacade();
    }

    public function login(string $request):string
    {
        $user = $this->converter->fromJson("User", $request);
        if($this->persistanceFacade->isUserExist($user)) {
            $token = new JsonWebToken();
            $token->generate($this->persistanceFacade->getUserId($user));
            return $token->toString();
        }
        throw new \InvalidArgumentException("User or Password Invalid");
    }

    public function put(string $request, string $token):int
    {
        $tokenJWT = new JsonWebToken($token);
        if ($tokenJWT->validate()){
            $user = $this->persistanceFacade->get("User",$tokenJWT->getUserId());
            try{
                $instance = $this->converter->fromJson("Instance", $request,$user);
                $id = $this->persistanceFacade->put($instance);
                return $id;
            }catch (Exception $e){
                throw new Exception($e->getCode().": ".$e->getMessage());
                return -1;
            }
        }
        throw new \InvalidArgumentException("Token not valid");
        return -1;
    }

    public function get(int $id, string $token):string
    {
        $tokenJWT = new JsonWebToken($token);
        if ($tokenJWT->validate()) {
            try {
                $instance = $this->persistanceFacade->get("Instance", $id);
                if ($instance == null) throw new \InvalidArgumentException("Instance with id: ".$id." doesn't exist");
                return $this->converter->toJson($instance);
            } catch (Exception $e) {
                throw new Exception($e->getCode().": ".$e->getMessage());
                return null;
            }
        }
        throw new \InvalidArgumentException("Token not valid");
        return null;
    }

    public function getAllInstancesFromName(string $name,string $token):string
    {
        $tokenJWT = new JsonWebToken($token);
        if ($tokenJWT->validate()) {
            try {
                $instances = $this->persistanceFacade->getAllInstancesFromName($name);
                if ($instances == null) throw new \InvalidArgumentException("Instance with name ".$name." doesn't exist");
                return $this->converter->toJson($instances);
            } catch (Exception $e) {
                throw new Exception($e->getCode().": ".$e->getMessage());
                return null;
            }
        }
        throw new \InvalidArgumentException("Token not valid");
        return null;
    }

    public function update(string $request, string $token,int $idInstance):void
    {
        $tokenJWT = new JsonWebToken($token);
        if ($tokenJWT->validate()) {
            $user = $this->persistanceFacade->get("User",$tokenJWT->getUserId());
            try {
                $instance = $this->converter->fromJson("Instance",$request,$user);
                $instance->setId($idInstance);
                $this->persistanceFacade->update($instance);
                return;
            } catch (Exception $e) {
                throw new Exception($e->getCode().": ".$e->getMessage());
                return;
            }
        }
        throw new \InvalidArgumentException("Token not valid");
        return;
    }

    public function delete(string $token,int $id):void
    {
        $tokenJWT = new JsonWebToken($token);
        if ($tokenJWT->validate()) {
            try {
                $entity = $this->persistanceFacade->get("Instance",$id);
                $this->persistanceFacade->delete($entity);
                return;
            } catch (Exception $e) {
                throw new Exception($e->getCode().": ".$e->getMessage());
                return;
            }
        }
        throw new \InvalidArgumentException("Token not valid");
        return;
    }

}