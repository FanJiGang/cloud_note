package cn.fan.cloud_note.service;

import cn.fan.cloud_note.entity.User;
import cn.fan.cloud_note.utils.NoteResult;

/**
 * 与user相关的业务操作接口
 */
public interface UserService {

    //用户登录
    NoteResult<User> login(String username,String password);

    //用户注册
    NoteResult<Object> regist(String username,String password,String nick);

    //根据userId查询用户信息
    NoteResult<User> loadUserMenu(String userId);

    //修改用户密码
    NoteResult<Object> changePwd(String userId,String lastPwd,String newPwd);
}
