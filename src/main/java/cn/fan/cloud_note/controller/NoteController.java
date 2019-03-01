package cn.fan.cloud_note.controller;


import cn.fan.cloud_note.entity.Note;
import cn.fan.cloud_note.service.NoteService;
import cn.fan.cloud_note.utils.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * note表相关的控制层
 */
@Controller
@RequestMapping("/note")
public class NoteController {

    @Resource(name="noteService")
    private NoteService noteService;

    @RequestMapping("/addLikeNote.do")
    @ResponseBody
    //新增一条笔记
    public NoteResult<String> addLikeNote(@RequestParam("userId") String userId,
                                      @RequestParam("noteTitle") String noteTitle,
                                      @RequestParam("noteBody") String noteBody,
                                      @RequestParam("status") String status){
        return noteService.addLikeNote(userId,noteTitle,noteBody,status);
    }

    @RequestMapping("/add.do")
    @ResponseBody
    //新增一条笔记
    public NoteResult<String> addNote(@RequestParam("userId") String userId,
                                      @RequestParam("bookId") String bookId,
                                      @RequestParam("noteTitle") String noteTitle){
        return noteService.addNote(userId, bookId, noteTitle);
    }

    @RequestMapping("/loadbody.do")
    @ResponseBody
    //加载笔记具体内容
    public NoteResult<Note> loadBody(@RequestParam("noteId") String noteId){
        return noteService.loadBodyByNoteId(noteId);
    }

    @RequestMapping("/loadnote.do")
    @ResponseBody
    //加载相应笔记
    public NoteResult<List<Map>> loadNote(@RequestParam("bookId") String bookId){
        return noteService.loadNoteByBookId(bookId);
    }

    @RequestMapping("/loadRollback.do")
    @ResponseBody
    //加载相应笔记
    public NoteResult<List<Map>> loadRollbackNote(@RequestParam("userId") String userId){
        return noteService.loadRollbackNotes(userId);
    }

    @RequestMapping("/loadNotes.do")
    @ResponseBody
    //更新笔记内容
    public NoteResult<List<Note>> findNotes(@RequestParam("userId") String userId,
                                        @RequestParam("userStatus") int userStatus){
        return noteService.loadNotes(userId,userStatus);
    }

    @RequestMapping("/updateStatus.do")
    @ResponseBody
    //更新笔记状态
    public NoteResult<Object> updateNoteStatus(@RequestParam("activityId") String activityId,
                                               @RequestParam("noteId") String noteId,
                                               @RequestParam("noteStatus") String noteStatus){
        return noteService.updateNoteStatus(activityId,noteId,noteStatus);
    }

    @RequestMapping("/update.do")
    @ResponseBody
    //更新笔记内容
    public NoteResult<Object> updateNote(@RequestParam("noteId") String noteId,
                                      @RequestParam("noteTitle") String noteTitle,
                                      @RequestParam("noteBody") String noteBody){
        return noteService.updateNote(noteId, noteTitle, noteBody);
    }

    @RequestMapping("/delNotes.do")
    @ResponseBody
    //更新笔记内容
    public NoteResult<Object> delNotes(@RequestParam("noteId") String noteId){
        return noteService.deleteNotes(noteId);
    }
}
