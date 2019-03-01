package cn.fan.cloud_note.service.impl;

import cn.fan.cloud_note.dao.UserDao;
import cn.fan.cloud_note.entity.User;
import cn.fan.cloud_note.service.UserService;
import cn.fan.cloud_note.utils.NoteResult;
import cn.fan.cloud_note.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 与user相关的具体业务逻辑
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    //注入userDao
    @Resource(name = "userDao")
    private UserDao dao;

    //用户登录
    public NoteResult<User> login(String username, String password) {
        NoteResult<User> result = new NoteResult<User>();
        User user = dao.findByName(username);
        if(user==null){
            //用户名不存在
            result.setStatus(1);
            result.setMsg("用户名不存在!");
        } else {
            if(Utils.md5(password).equals(user.getCn_user_password())){
                //密码正确
                result.setStatus(0);
                result.setMsg("验证通过");
                result.setData(user);
            }else {
                //用户名存在,但密码错误
                result.setStatus(2);
                result.setMsg("密码错误!");
            }
        }
        return result;
    }

    //用户注册
    @Transactional
    public NoteResult<Object> regist(String username, String password, String nick) {
        NoteResult<Object> result = new NoteResult<Object>();
        if(dao.findByName(username)==null){
            //用户名可用
            User user = new User(Utils.createId(), username, Utils.md5(password), null, nick);
            dao.saveUser(user);
            result.setStatus(0);
            result.setMsg("注册成功,点击确定返回登录界面!");
        }else {
            //用户名已存在
            result.setStatus(1);
            result.setMsg("用户名被占用");
        }
        return result;
    }

    /*
     * 根据userId获取用户信息
     * status=0:获取成功
     * status=1:无效的用户id
     */
    public NoteResult<User> loadUserMenu(String userId) {
        NoteResult<User> result=new NoteResult<User>();
        User user=dao.findById(userId);
        if(user==null) {
            result.setStatus(1);
            result.setMsg("无效的用户id");
        }else{
            result.setStatus(0);
            result.setMsg("获取用户信息成功");
            result.setData(user);
        }
        return result;
    }

    /*
     * 修改账号密码
     * status=0:修改成功
     * status=1:密码错误
     */
    public NoteResult<Object> changePwd(String userId, String lastPwd, String newPwd) {
        NoteResult<Object> result=new NoteResult<Object>();
        Map<String,String> map=new HashMap<String,String>();
        map.put("userId", userId);
        map.put("lastPwd", Utils.md5(lastPwd));
        map.put("newPwd", Utils.md5(newPwd));
        int num=dao.changePwd(map);
        if(num==1) {
            result.setStatus(0);
            result.setMsg("密码修改成功,请重新登录!");
        }else {
            result.setStatus(1);
            result.setMsg("密码错误");
        }
        return result;

    }

}
