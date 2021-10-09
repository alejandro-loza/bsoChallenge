package com.bitso.challenge.service;

import com.bitso.challenge.entity.User;
import com.bitso.challenge.model.UserModel;
import com.bitso.challenge.model.ram.UserModelImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

/**
 * Entry point and configuration provider.
 */
@SpringBootApplication
public class ServiceApplication {

    @Bean
    public UserModel userModel() {
        UserModelImpl um = new UserModelImpl();
        //Populate
        LongStream.rangeClosed(1, 10).forEach(id -> {
            User u = new User();
            u.setId(id);
            u.setEmail("user" + id + "@bitso.com");
            u.setPassword("password" + id);
            um.add(u);
        });
        return um;
    }



    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }
}
