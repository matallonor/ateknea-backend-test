package com.ateknea.services;

import com.ateknea.entities.User;
import com.ateknea.entities.UserEntity;
import com.ateknea.mappers.UserEntityMapper;
import com.ateknea.repositories.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserDBService {

    @Autowired
    private UserEntityMapper entityMapper;

    @Autowired
    private UserJpaRepository repository;

    public List<User> getAll() {
        return entityMapper.toUsersList(repository.findAll());
    }

    public User create(User user) {
        UserEntity entity = entityMapper.toEntity(user);
        repository.save(entity);
        user.setId(entity.getId());
        return user;
    }

    public User getById(Long userId) {
        try {
            UserEntity entity = repository.findById(userId).get();
            return entityMapper.toUser(entity);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public User update(Long userId, User user) {
        // Get current database entity
        UserEntity dbEntity = null;
        try {
            dbEntity = repository.findById(userId).get();
        } catch (NoSuchElementException e) {
            return null;
        }

        // Update database entity
        UserEntity entity = entityMapper.toEntity(user);
        entity.setId(dbEntity.getId());
        return entityMapper.toUser(repository.save(entity));
    }


    public User delete(Long userId) {
        try {
            // Get current database entity
            UserEntity dbEntity = repository.findById(userId).get();
            // Delete
            repository.delete(dbEntity);
            return entityMapper.toUser(dbEntity);
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}

