package cn.fan.cloud_note.controller;


import cn.fan.cloud_note.entity.Book;
import cn.fan.cloud_note.service.BookService;
import cn.fan.cloud_note.utils.NoteResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * notebook表相关的控制层
 */
@Controller
@RequestMapping("/book")
public class NoteBookController {

    @Resource(name="bookService")
    private BookService bookService;

    @RequestMapping("/rename.do")
    @ResponseBody
    //重命名笔记本
    public NoteResult<Object> rename(@RequestParam("bookId") String bookId,
                                     @RequestParam("afterBookName") String afterBookName){
        return bookService.rename(bookId, afterBookName);
    }

    @RequestMapping("/add.do")
    @ResponseBody
    //新增一个笔记本
    public NoteResult<String> save(@RequestParam("userId") String userId,
                                   @RequestParam("bookName") String bookName){
        return bookService.save(userId, bookName);
    }

    @RequestMapping("/loadBooks.do")
    @ResponseBody
    //加载笔记本
    public NoteResult<List<Book>> loadUserBooks(@RequestParam("userId") String userId){
        return bookService.LoadUserBooks(userId);
    }

    @RequestMapping("/del.do")
    @ResponseBody
    //删除笔记本
    public NoteResult<Object> delBook(@RequestParam("bookId") String bookId){
        return bookService.delBook(bookId);
    }

}
