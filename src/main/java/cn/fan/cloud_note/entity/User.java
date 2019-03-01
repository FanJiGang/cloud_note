package cn.fan.cloud_note.entity;

import java.io.Serializable;

/**
 * 对应数据库中的cn_user表
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1402425013850093254L;

    private String cn_user_id; //用户id
    private String cn_user_name;  //用户名称
    private String cn_user_password;  //用户密码
    private String cn_user_token;  //用户描述
    private String cn_user_nick;  //用户昵称

    public User() {
    }

    public User(String cn_user_id, String cn_user_name, String cn_user_password, String cn_user_token, String cn_user_nick) {

        this.cn_user_id = cn_user_id;
        this.cn_user_name = cn_user_name;
        this.cn_user_password = cn_user_password;
        this.cn_user_token = cn_user_token;
        this.cn_user_nick = cn_user_nick;
    }

    public String getCn_user_id() {

        return cn_user_id;
    }

    public void setCn_user_id(String cn_user_id) {
        this.cn_user_id = cn_user_id;
    }

    public String getCn_user_name() {
        return cn_user_name;
    }

    public void setCn_user_name(String cn_user_name) {
        this.cn_user_name = cn_user_name;
    }

    public String getCn_user_password() {
        return cn_user_password;
    }

    public void setCn_user_password(String cn_user_password) {
        this.cn_user_password = cn_user_password;
    }

    public String getCn_user_token() {
        return cn_user_token;
    }

    public void setCn_user_token(String cn_user_token) {
        this.cn_user_token = cn_user_token;
    }

    public String getCn_user_nick() {
        return cn_user_nick;
    }

    public void setCn_user_nick(String cn_user_nick) {
        this.cn_user_nick = cn_user_nick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return cn_user_id.equals(user.cn_user_id);
    }

    @Override
    public int hashCode() {
        return cn_user_id.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "cn_user_id='" + cn_user_id + '\'' +
                ", cn_user_name='" + cn_user_name + '\'' +
                ", cn_user_password='" + cn_user_password + '\'' +
                ", cn_user_token='" + cn_user_token + '\'' +
                ", cn_user_nick='" + cn_user_nick + '\'' +
                '}';
    }
}
