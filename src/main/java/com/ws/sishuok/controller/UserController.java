package com.ws.sishuok.controller;

import com.ws.user.dao.UserRepository;
import com.ws.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 13-12-22
 * <p>Version: 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public User view(@PathVariable("name") String name) {
        return userRepository.findByName(name);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void add(){
        userRepository.save(new com.ws.user.model.User("123", (int)(1+Math.random()*(100-1+1))));
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<User> list(){
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "age"));
        return userRepository.findAll(sort);
    }




}
