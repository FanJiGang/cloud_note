package cn.fan.cloud_note.entity;

import java.io.Serializable;

/**
 * 对应数据库中的cn_note_activity表
 */
public class NoteActivity implements Serializable {
    private static final long serialVersionUID = -2172210652593624552L;

    private String cn_note_activity_id;
    private String cn_activity_id;
    private String cn_note_id;
    private int cn_note_activity_up;
    private int cn_note_activity_down;
    private String cn_note_activity_title;
    private String cn_note_activity_body;

    public NoteActivity() {
    }

    public NoteActivity(String cn_activity_id, String cn_note_id, int cn_note_activity_up, int cn_note_activity_down, String cn_note_activity_title, String cn_note_activity_body) {

        this.cn_activity_id = cn_activity_id;
        this.cn_note_id = cn_note_id;
        this.cn_note_activity_up = cn_note_activity_up;
        this.cn_note_activity_down = cn_note_activity_down;
        this.cn_note_activity_title = cn_note_activity_title;
        this.cn_note_activity_body = cn_note_activity_body;
    }

    public String getCn_note_activity_id() {

        return cn_note_activity_id;
    }

    public void setCn_note_activity_id(String cn_note_activity_id) {
        this.cn_note_activity_id = cn_note_activity_id;
    }

    public String getCn_activity_id() {
        return cn_activity_id;
    }

    public void setCn_activity_id(String cn_activity_id) {
        this.cn_activity_id = cn_activity_id;
    }

    public String getCn_note_id() {
        return cn_note_id;
    }

    public void setCn_note_id(String cn_note_id) {
        this.cn_note_id = cn_note_id;
    }

    public int getCn_note_activity_up() {
        return cn_note_activity_up;
    }

    public void setCn_note_activity_up(int cn_note_activity_up) {
        this.cn_note_activity_up = cn_note_activity_up;
    }

    public int getCn_note_activity_down() {
        return cn_note_activity_down;
    }

    public void setCn_note_activity_down(int cn_note_activity_down) {
        this.cn_note_activity_down = cn_note_activity_down;
    }

    public String getCn_note_activity_title() {
        return cn_note_activity_title;
    }

    public void setCn_note_activity_title(String cn_note_activity_title) {
        this.cn_note_activity_title = cn_note_activity_title;
    }

    public String getCn_note_activity_body() {
        return cn_note_activity_body;
    }

    public void setCn_note_activity_body(String cn_note_activity_body) {
        this.cn_note_activity_body = cn_note_activity_body;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteActivity that = (NoteActivity) o;

        return cn_note_activity_id != null ? cn_note_activity_id.equals(that.cn_note_activity_id) : that.cn_note_activity_id == null;
    }

    @Override
    public int hashCode() {
        return cn_note_activity_id != null ? cn_note_activity_id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NoteActivity{" +
                "cn_note_activity_id='" + cn_note_activity_id + '\'' +
                ", cn_activity_id='" + cn_activity_id + '\'' +
                ", cn_note_id='" + cn_note_id + '\'' +
                ", cn_note_activity_up=" + cn_note_activity_up +
                ", cn_note_activity_down=" + cn_note_activity_down +
                ", cn_note_activity_title='" + cn_note_activity_title + '\'' +
                ", cn_note_activity_body='" + cn_note_activity_body + '\'' +
                '}';
    }
}
