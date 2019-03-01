package cn.fan.cloud_note.dao;

import cn.fan.cloud_note.entity.NoteActivity;

import java.util.List;
import java.util.Map;

/**
 * 对cn_note_activity表的相关操作
 */
public interface NoteActivityDao {

    //查询指定activity_id的笔记信息
    List<NoteActivity> searchNoteActivity(Map<String,Object> map);

    //查询指定笔记内容
    NoteActivity searchNoteContent(String activityNoteId);

    //增加一条记录
    void addNoteActivity(Map map);

    //点赞/点踩
    void upOrDown(Map map);
}
