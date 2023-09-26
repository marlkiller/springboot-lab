package com.example.springbootlab.portal.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbootlab.SpringbootLabApplicationTests;
import com.example.springbootlab.portal.entity.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserMapperTest extends SpringbootLabApplicationTests {

    @Resource
    private UserMapper userMapper;
    @Test
    void test() {
        // QueryWrapper
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        System.out.println(userMapper.selectList(queryWrapper));

        User user = new User();
        user.setName("void");
        queryWrapper.like(user.getName() != null, "name", user.getName());
        System.out.println(userMapper.selectList(queryWrapper));
        
        // LambdaQueryWrapper
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getName, "v").lt(User::getId, 30);
        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
        
        // 原生 mybatis sql
        System.out.println(userMapper.selectRaw());
        // 原生 mybatis sql xml
        System.out.println(userMapper.selectRawXML());
        
        // page 分页 依赖 Bean MybatisPlusInterceptor
        // 分页查询总共发出了2次SQL，一次查总记录数，一次查具体数据。
        // 若希望不查总记录数，仅查分页结果。可以通过Page的重载构造函数，指定isSearchCount为false即可
        LambdaQueryWrapper<User> pageWrapper = new LambdaQueryWrapper<>();
        pageWrapper.ge(User::getId, 0);
        // 设置分页信息, 查第3页, 每页2条数据
        Page<User> page = new Page<>(2, 1);
        // 执行分页查询
        Page<User> userPage = userMapper.selectPage(page, pageWrapper);
        System.out.println("总记录数 = " + userPage.getTotal());
        System.out.println("总页数 = " + userPage.getPages());
        System.out.println("当前页码 = " + userPage.getCurrent());
        // 获取分页查询结果
        List<User> records = userPage.getRecords();
        records.forEach(System.out::println);
        
        // TODO JOIN
        // <!-- join 查询 (mapper 继承 MPJBaseMapper) -->
        //         <dependency>
        //             <groupId>com.github.yulichang</groupId>
        //             <artifactId>mybatis-plus-join</artifactId>
        //             <version>1.4.6</version>
        //         </dependency>
        // public interface BucketMapper extends MPJBaseMapper<Bucket> {
        // 
        // }
        // MPJLambdaWrapper<Bucket> wrapper = JoinWrappers.lambda(Bucket.class);
        //         wrapper.selectAll(Bucket.class);
        // wrapper.selectAs(Ossserver::getName, BucketVo::getOssServerName);
        //         wrapper.selectAs(Ossserver::getVendor, BucketVo::getVendor);
        //         wrapper.selectAs(PublicRegion::getName, BucketVo::getPublicCloudRegionName);
        //         wrapper.selectAs(Enterprise::getName, BucketVo::getEnterpriseName);
        //         wrapper.selectAs(Region::getName, BucketVo::getRegionName);
        //         // 关联表
        //         wrapper.innerJoin(Ossserver.class, Ossserver::getId, Bucket::getOssServerId);
        //         wrapper.leftJoin(PublicRegion.class, PublicRegion::getUuid, Bucket::getPublicCloudRegion);
        //         wrapper.leftJoin(Enterprise.class, Enterprise::getId, Bucket::getEnterpriseId);
        //         wrapper.leftJoin(Region.class, Region::getId, Ossserver::getRegionid);
        // 
        //         // 过滤条件
        //         buildQueryWrapper(wrapper, req);
        // 
        //         // 分页
        //         Page<BucketVo> bucketPage = bucketMapper.selectJoinPage(
        //                 new Page<>(req.getPageNo(), req.getPageSize()),
        //                 BucketVo.class,
        //                 wrapper
        //         );
        // TODO AR模式 , ActiveRecord


    }
}