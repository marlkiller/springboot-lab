package com.example.springbootlab.portal.resp;

import com.example.springbootlab.SpringbootLabApplicationTests;
import com.example.springbootlab.portal.entity.User;
import com.mongodb.client.result.UpdateResult;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.regex.Pattern;

class UserRepositoryTest extends SpringbootLabApplicationTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void mongoTemplateTest() {

        User objectToSave = new User();
        long id = System.currentTimeMillis();
        // objectToSave.setId(id+"");
        objectToSave.setName("void" + id);
        mongoTemplate.save(objectToSave);

        System.out.println(mongoTemplate.findAll(User.class));


        Query query = new Query(Criteria.where("name").is("void"));
        List<User> users = mongoTemplate.find(query, User.class);
        for (User user : users) {
            System.out.println(user);
        }
        // and 查询
        query = new Query(Criteria
                .where("name").is("void")
                .and("id").is(1));
        users = mongoTemplate.find(query, User.class);
        for (User user : users) {
            System.out.println(user);
        }

        // or 查询
        Criteria criteria = new Criteria();
        query = new Query(criteria.orOperator(
                Criteria.where("name").is("void"),
                Criteria.where("name").is("ysl")));
        users = mongoTemplate.find(query, User.class);
        for (User user : users) {
            System.out.println(user);
        }

        // regex 模糊
        Pattern pattern = Pattern.compile(".*void.*");
        query = new Query(Criteria.where("name").regex(pattern));
        users = mongoTemplate.find(query, User.class);
        for (User user : users) {
            System.out.println(user);
        }

        // update

        Update update = new Update();
        update.set("name", "update_name");
        query = new Query(Criteria.where("name").is("void"));
        UpdateResult updateResult = mongoTemplate.upsert(query, update, User.class);
        if (updateResult.getModifiedCount() > 0) {
            System.out.println("更新成功");
        }
    }

    @Test
    public void pageLikeUserList() {
        int pageNo = 1;// 设置当前页
        int pageSize = 3;// 设置每页显示的记录数

        // 条件构建
        String name = "est";
        String regex = String.format("%s%s%s", "^.*", name, ".*$");
        // 1、在使用Pattern.compile函数时，可以加入控制正则表达式的匹配行为的参数：
        // Pattern Pattern.compile(String regex, int flag)
        // 2、regex设置匹配规则
        // 3、Pattern.CASE_INSENSITIVE,这个标志能让表达式忽略大小写进行匹配。
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        // 创建一个query对象（用来封装所有条件对象)，再创建一个criteria对象（用来构建条件）
        Query query = new Query(// 构建查询条件
                Criteria.where("name").regex(pattern));

        // 分页构建
        // 查询数来集合（表）中的总记录数
        long count = mongoTemplate.count(query, User.class);
        List<User> users = mongoTemplate.find(
                query.skip((pageNo - 1) * pageSize).limit(pageSize), User.class);
        System.out.println(count);
        System.out.println(users);
    }

    @Resource
    private UserRepository userRepository;

    @Test
    void jpaMongoTest() {

        System.out.println(userRepository.findUserById(String.valueOf(1)));

        User insertUser = new User();
        insertUser.setName("ysl");
        userRepository.insert(insertUser);

        // custom query
        System.out.println(userRepository.findByName("void"));

        // page
        System.out.println(userRepository.findAll(PageRequest.of(1, 1)));

        // limit
        // Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);
        // Slice<User> findTop3ByLastname(String lastname, Pageable pageable);
        // List<User> findFirst10ByLastname(String lastname, Sort sort);

        // sort
        Sort.TypedSort<User> user = Sort.sort(User.class);
        Sort sort = user.by(User::getName).ascending().and(user.by(User::getId).descending());


        // Example.of
        // https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.transactions
        User userExample = new User();
        userExample.setName("v");

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