package com.ms.svcUser.service.impl;

import com.ms.svcUser.models.UserModel;
import com.ms.svcUser.producers.UserProducer;
import com.ms.svcUser.repositories.UserRepository;
import com.ms.svcUser.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    UserProducer userProducer;

    @Override
    @Transactional
    public UserModel saveUser(UserModel userModel) {
        userModel =  repository.save(userModel);
        //envia mensagem com o usuario criado
        userProducer.publishMessageEmail(userModel);
        return userModel;
    }
}
