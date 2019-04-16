package com.ateknea.services;

import com.ateknea.entities.User;
import com.ateknea.mappers.UserEntityMapper;
import com.ateknea.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDBService {

    @Autowired
    private UserEntityMapper entityMapper;

    @Autowired
    private UserJpaRepository repository;

    public List<User> getAll() {
        return entityMapper.toUsersList(repository.findAll());
    }


    /*public Long save(User user) {
        UserEntity entity = entityMapper.toEntity(user);
        repository.save(entity);
        user.setId(entity.getId());
        return entity.getId();
    }*/

}

