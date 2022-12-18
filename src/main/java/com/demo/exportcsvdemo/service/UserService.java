package com.demo.exportcsvdemo.service;

import com.demo.exportcsvdemo.annotation.MsgSender;
import com.demo.exportcsvdemo.domain.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@MsgSender(type = "user")
public class UserService {

    public List<Users> listUsers(){
        List<Users> users = new ArrayList<>();

        // create dummy users
        users.add(new Users(1, "Jack Lee", "jack@example.com", "Germany", 35));
        users.add(new Users(2, "Jack Lee2", "jack2@example.com", "Russia", 33));
        users.add(new Users(3, "Jack Lee3", "jack3@example.com", "Pakistan", 29));

        for (Users u: users) {
            System.out.println("=======================");
            System.out.println(u.id());
            System.out.println(u.name());
            System.out.println("=======================");
        }

        return users;
    }

}
