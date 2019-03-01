package cn.fan.cloud_note.service;

import cn.fan.cloud_note.entity.NoteActivity;
import cn.fan.cloud_note.utils.NoteResult;

import java.util.List;
import java.util.Map;

/**
 * 与cn_note_activity相关的业务操作接口
 */
public interface NoteActivityService {

    //查询指定activity_id的笔记信息
    NoteResult<List<NoteActivity>> loadActivityNotes(String activity_id,int page);

    //查询指定笔记内容
    NoteResult<NoteActivity> searchNoteContent(String activityNoteId);

    //点赞/点踩
    NoteResult<Object> upAndDown(String id,int up,int down);
}
