package cn.fan.cloud_note.utils;

import java.io.Serializable;

/**
 * 结果集:业务层所有返回结果都封装成该对象
 *
 */
public class NoteResult<T> implements Serializable {
    private static final long serialVersionUID = 3132249601308600265L;

    //状态码,正常时为0
    private int status;
    //对此次处理结果的描述
    private String msg;
    //返回结果
    private T data;

    public NoteResult() {
    }

    public NoteResult(int status, String msg, T data) {

        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {

        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "NoteResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
