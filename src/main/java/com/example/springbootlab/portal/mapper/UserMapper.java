package com.example.springbootlab.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbootlab.portal.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    List<User> selectRaw();
    List<User> selectRawXML();
}