package com.mytest.register.mapper;

import com.mytest.register.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author gdufeZLYL
 * @date 2019/9/14 0:32
 */
public interface UserMapper {

    @Select("SELECT * FROM t_user")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "name",  column = "name"),
            @Result(property = "userId",  column = "user_id"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "createTime", column = "create_time"),
    @Result(property = "updateTime", column = "update_time")

})
        List<User> getAll();

@Select("SELECT * FROM t_user WHERE id = #{id}")
@Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "name",  column = "name"),
        @Result(property = "userId",  column = "user_id"),
        @Result(property = "password", column = "password"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
})
    User selectByPrimaryKey(Integer id);

@Select("SELECT * FROM t_user WHERE phone = #{phone}")
@Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "name",  column = "name"),
        @Result(property = "userId",  column = "user_id"),
        @Result(property = "password", column = "password"),
        @Result(property = "phone", column = "phone"),
        @Result(property = "createTime", column = "create_time"),
        @Result(property = "updateTime", column = "update_time")
})
    User selectByPhone(String phone);

@Insert("INSERT INTO t_user(name,user_id,password,phone) VALUES(#{name}, #{userId}, #{password}, #{phone})")
    void insert(User user);

@Update("UPDATE t_user SET name=#{name},password=#{password},phone=#{phone} WHERE id =#{id}")
    void update(User user);

@Delete("DELETE FROM t_user WHERE id =#{id}")
    void delete(Integer id);
}
