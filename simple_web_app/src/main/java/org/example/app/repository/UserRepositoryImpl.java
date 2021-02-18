package org.example.app.repository;

import org.apache.log4j.Logger;
import org.example.web.dto.LoginForm;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository<LoginForm> {
    private final Logger logger = Logger.getLogger(UserRepositoryImpl.class);

    private final List<LoginForm> repo = new ArrayList<>();

    @Override
    public List<LoginForm> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(LoginForm entity){
       logger.info("user has stored:" + entity.getUsername());
       entity.setId(entity.hashCode());
       repo.add(entity);
    }

    @Override
    public boolean removeItemById(Integer entityId) {
        for (LoginForm user: repo){
            if (user.getId().equals(entityId)){
                logger.info("user has removed:"  + user.getUsername());
                return repo.remove(user);
            }
        }
        return false;
    }

    @Override
    public boolean checkByName(LoginForm entity) {
        for (LoginForm user: repo){
            if (user.getUsername().equalsIgnoreCase(entity.getUsername())){
                logger.info("such user exists:" + user.getUsername());
                return repo.remove(user);
            }
        }
        return false;
    }

    @Override
    public boolean existsItem(LoginForm entity) {
        for (LoginForm user: repo){
            if (user.getUsername().equalsIgnoreCase(entity.getUsername())&&user.getPassword().equals(entity.getPassword())){
                logger.info("such user and pass exists:" + user);
                return repo.remove(user);
            }
        }
        return false;
    }
}
