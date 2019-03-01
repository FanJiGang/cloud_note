package cn.fan.cloud_note.service;

import cn.fan.cloud_note.entity.Book;
import cn.fan.cloud_note.utils.NoteResult;

import java.util.List;


/**
 * 与notebook相关的业务操作接口
 */
public interface BookService {
	//根据cn_user_id,查询cn_notebook表中的数据
	NoteResult<List<Book>> LoadUserBooks(String userId);

	//向cn_notebook表中插入一条数据
	NoteResult<String> save(String userId,String bookName);

	//重命名笔记本
	NoteResult<Object> rename(String bookId,String afterBookName);

	//删除笔记本
	NoteResult<Object> delBook(String bookId);
}
