package com.example.springbootlab.portal.resp;

import com.example.springbootlab.SpringbootLabApplicationTests;
import com.example.springbootlab.portal.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QSort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest extends SpringbootLabApplicationTests {
    @Resource
    private UserRepository userRepository;

    @Test
    @Transactional(readOnly = true)
    void contextLoads() {
        List<User> all = userRepository.findAll();
        System.out.println(all);
        // System.out.println(userRepository.findById(22222222L));
        // System.out.println(userRepository.countByName("void"));

        // Enabling static ORDER BY for a query
        // List<User> findByLastnameOrderByFirstnameAsc(String lastname);
        // List<User> findByLastnameOrderByFirstnameDesc(String lastname);
        // List<User> findByEmailAddressAndLastname(EmailAddress emailAddress, String lastname);


        // sort
        Sort.TypedSort<User> user = Sort.sort(User.class);
        Sort sort = user.by(User::getName).ascending().and(user.by(User::getId).descending());

        // limit 
        // Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);
        // Slice<User> findTop3ByLastname(String lastname, Pageable pageable);
        // List<User> findFirst10ByLastname(String lastname, Sort sort);

        // custom sql 
        Stream<User> userStream = userRepository.streamAllPaged(PageRequest.of(1, 1));
        System.out.println(userStream.collect(Collectors.toSet()));


        // Example.of
        // https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.transactions
        User userExample = new User();
        userExample.setName("vvvv");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher(
                        "name",
                        ExampleMatcher.GenericPropertyMatcher.of(ExampleMatcher.StringMatcher.CONTAINING)
                );

        Example<User> example = Example.of(userExample, matcher);
        List<User> users = userRepository.findAll(example);
        System.out.println(users);


    }
}