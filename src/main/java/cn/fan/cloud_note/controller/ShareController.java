package cn.fan.cloud_note.controller;


import cn.fan.cloud_note.entity.Share;
import cn.fan.cloud_note.service.ShareService;
import cn.fan.cloud_note.utils.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * share表相关的控制层
 */
@Controller
@RequestMapping("/share")
public class ShareController {
    @Resource(name="shareService")
    private ShareService shareService;

    @RequestMapping("/add.do")
    @ResponseBody
    //添加分享
    public NoteResult<Object> addShare(@RequestParam("noteId") String noteId){
        return shareService.addShare(noteId);
    }

    @RequestMapping("/search.do")
    @ResponseBody
    //查询所有符合条件的数据
    public NoteResult<List<Share>> searchNote(@RequestParam("keyword") String keyword,
                                              @RequestParam("page") int page){
        return shareService.searchNote(keyword,page);
    }

    @RequestMapping("/searchShareById.do")
    @ResponseBody
    //根据id查询指定的笔记内容
    public NoteResult<Share> searchShareById(@RequestParam("shareId") String shareId){
        return shareService.searchShareByID(shareId);
    }
}
