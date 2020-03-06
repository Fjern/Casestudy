package com.github.fjern.ChatApp.Service;

import com.github.fjern.ChatApp.model.User;
import com.github.fjern.ChatApp.repositroy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        User userCreated = repository.save(user);
        return userCreated;
    }

    public User read(Long id) {
        Optional<User> potentialPerson = repository.findById(id);
        User user = potentialPerson.get();
        return user;
    }

    public User update(Long id, User user) {
        User userInDataBase = read(id);
        String userName = user.getUserName();
        String passWord = user.getPassWord();

        userInDataBase.setUserName(userName);
        userInDataBase.setPassWord(passWord);
        repository.save(userInDataBase);
        return userInDataBase;
    }

    public User delete(Long id) {
        User user = read(id);
        repository.delete(user);
        return user;
    }

    public List<User> readAll() {
        List<User> userList = new ArrayList<>();
        repository.findAll().forEach(userList::add);
        return userList;
    }

    public Boolean validateLogin(User user) {
        List<User> allUsers = readAll();

        for(User userInDB : allUsers){
            if(userInDB.getUserName().equals(user.getUserName()) && userInDB.getPassWord().equals(user.getPassWord())){
                return true;
            }
        }
        return false;
    }
}
