package cn.fan.cloud_note.controller;

import cn.fan.cloud_note.entity.NoteActivity;
import cn.fan.cloud_note.service.NoteActivityService;
import cn.fan.cloud_note.utils.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * cn_note_activity表相关的控制层
 */
@Controller
@RequestMapping("/noteActivity")
public class NoteActivityController {

    @Resource(name = "noteActivityService")
    private NoteActivityService service;

    //点赞/点踩
    @RequestMapping("/upOrDown.do")
    @ResponseBody
    public NoteResult<Object> upOrDown(@RequestParam("id") String id,
                                       @RequestParam("up") int up,
                                       @RequestParam("down") int down){
        return service.upAndDown(id,up,down);
    }

    @RequestMapping("/loadActivityNotes.do")
    @ResponseBody
    //查询指定activity_id的笔记信息
    public NoteResult<List<NoteActivity>> loadActivityNotes(@RequestParam("activity_id") String activity_id,
                                                            @RequestParam("activity_page") int activity_page){
        return service.loadActivityNotes(activity_id,activity_page);
    }

    @RequestMapping("/showNoteContent.do")
    @ResponseBody
    //查询指定笔记的内容
    public NoteResult<NoteActivity> showNoteContent(@RequestParam("activityNoteId") String activityNoteId){
        return service.searchNoteContent(activityNoteId);
    }
}
