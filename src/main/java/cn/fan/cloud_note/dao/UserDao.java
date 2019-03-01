package cn.fan.cloud_note.dao;

import cn.fan.cloud_note.entity.User;

import java.util.Map;

/**
 * 对数据库中cn_user表的相关操作
 */
public interface UserDao {

    //根据名称name查询用户
    User findByName(String name);

    //向表中新增一条记录
    void saveUser(User user);

    //根据userId查询用户信息
    public User findById(String userId);

    //修改密码
    public int changePwd(Map<String,String> map);
}
