package cn.fan.cloud_note.service.impl;

import cn.fan.cloud_note.dao.NoteActivityDao;
import cn.fan.cloud_note.entity.NoteActivity;
import cn.fan.cloud_note.service.NoteActivityService;
import cn.fan.cloud_note.utils.NoteResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 与cn_note_activity相关的具体业务逻辑
 */
@Service("noteActivityService")
public class NoteActivityServiceImpl implements NoteActivityService {

    @Resource(name = "noteActivityDao")
    private NoteActivityDao dao;

    //查询指定activity_id的笔记信息
    public NoteResult<List<NoteActivity>> loadActivityNotes(String activity_id, int page) {
        int begin=(page-1)*5;//计算抓取记录的起点
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("activity_id", activity_id);
        map.put("begin", begin);
        NoteResult<List<NoteActivity>> result=new NoteResult<List<NoteActivity>>();
        List<NoteActivity> list=dao.searchNoteActivity(map);
        if(list.size()==0) {  //查询到0条笔记
            result.setStatus(1);
            result.setMsg("没有更多的活动笔记了!");
        }else {  //查询到了笔记
            result.setStatus(0);
            result.setMsg("查询到了"+list.size()+"条活动笔记");
            result.setData(list);
        }
        return result;
    }

    //查询指定笔记内容
    public NoteResult<NoteActivity> searchNoteContent(String activityNoteId) {
        NoteResult<NoteActivity> result = new NoteResult<NoteActivity>();
        NoteActivity noteActivity = dao.searchNoteContent(activityNoteId);
        if (noteActivity != null) {
            //查询到了内容
            result.setStatus(0);
            result.setMsg("查询到了笔记内容");
            result.setData(noteActivity);
        } else {
            //未查到内容
            result.setStatus(1);
            result.setMsg("当前笔记已不存在!");
        }
        return result;
    }

    public NoteResult<Object> upAndDown(String id, int up, int down) {
        NoteResult<Object> result = new NoteResult<Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("up",up);
        map.put("down",down);
        dao.upOrDown(map);
        result.setStatus(0);
        if(up==1){
            result.setMsg("点赞成功");
        }else if(down==1){
            result.setMsg("点踩成功");
        }
        return result;
    }
}
