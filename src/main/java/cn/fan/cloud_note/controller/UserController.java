package cn.fan.cloud_note.controller;

import cn.fan.cloud_note.entity.User;
import cn.fan.cloud_note.service.UserService;
import cn.fan.cloud_note.utils.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 用户表相关的控制层
 */
@Controller
@RequestMapping("/user")
public class UserController {

    //注入业务层
    @Resource(name = "userService")
    private UserService userService;

    @RequestMapping("/login.do")
    @ResponseBody
    //用户登录
    public NoteResult<User> login(@RequestParam("name") String name, @RequestParam("password") String password){
        return userService.login(name,password);
    }

    @RequestMapping("/regist.do")
    @ResponseBody
    //用户注册
    public NoteResult<Object> regist(@RequestParam("username") String username,
                                     @RequestParam("nickname") String nickname,
                                     @RequestParam("password") String password){
        return userService.regist(username,password,nickname);
    }

    @RequestMapping("/findById.do")
    @ResponseBody
    //根据userId查询用户信息
    public NoteResult<User> loadUserMenu(@RequestParam("userId") String userId){
        return userService.loadUserMenu(userId);
    }

    @RequestMapping("/changePwd.do")
    @ResponseBody
    //修改密码
    public NoteResult<Object> userChangePwd(@RequestParam("userId") String userId,
                                      @RequestParam("lastPwd") String lastPwd,
                                      @RequestParam("newPwd") String newPwd) {
        return userService.changePwd(userId, lastPwd, newPwd);
    }
}
