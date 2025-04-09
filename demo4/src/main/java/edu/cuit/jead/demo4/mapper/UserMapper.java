package edu.cuit.jead.demo4.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.cuit.jead.demo4.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    // 动态条件批量查询（需在XML中实现）
    List<User> selectByConditions(@Param("comditions") Map<String, Object> conditions);
}

// 使用MyBatis-Plus的BaseMapper接口继承通用CRUD方法，并扩展自定义查询方法