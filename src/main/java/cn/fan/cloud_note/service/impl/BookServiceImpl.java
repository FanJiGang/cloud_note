package cn.fan.cloud_note.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.fan.cloud_note.dao.BookDao;
import cn.fan.cloud_note.entity.Book;
import cn.fan.cloud_note.service.BookService;
import cn.fan.cloud_note.utils.NoteResult;
import cn.fan.cloud_note.utils.Utils;
import org.springframework.stereotype.Service;

/**
 * 与notebook相关的具体业务逻辑
 */
@Service("bookService")
public class BookServiceImpl implements BookService {

	@Resource(name="bookDao")
	private BookDao bookDao;

	/*
	 * 根据userId查询cn_notebook中的记录(non-Javadoc)
	 * status=0:查询到数据并返回
	 * status=1:当前用户账户中,未存在记事本
	 */
	public NoteResult<List<Book>> LoadUserBooks(String userId) {
		List<Book> books=bookDao.findByUserId(userId);
		//构建返回结果result
		NoteResult<List<Book>> result=new NoteResult<List<Book>>();
		if(books.size()==0) {
			result.setStatus(1);
			result.setMsg("尚未创建笔记本");
		}else {
			result.setStatus(0);
			result.setMsg("查询到"+books.size()+"个笔记本");
			result.setData(books);
		}
		return result;
	}

	/*
	 * 向cn_notebook表中插入一条数据
	 * status=0:插入成功
	 */
	public NoteResult<String> save(String userId, String bookName) {
		NoteResult<String> result=new NoteResult<String>();
		Map<String,Object> map=new HashMap<String,Object>();
		String bookId=Utils.createId();
		map.put("bookId", bookId);
		map.put("userId", userId);
		map.put("bookName", bookName);
		map.put("bookCreateTime", new Timestamp(System.currentTimeMillis()));
		bookDao.save(map);
		result.setStatus(0);
		result.setMsg("创建成功!");
		result.setData(bookId);
		return result;
	}

	/*
	 * 重命名笔记本
	 * status=0:重命名成功
	 * status=1:未重命名数据
	 */
	public NoteResult<Object> rename(String bookId, String afterBookName) {
		NoteResult<Object> result=new NoteResult<Object>();
		Map<String,String> map=new HashMap<String,String>();
		map.put("bookId", bookId);
		map.put("bookName", afterBookName);
		int num=bookDao.rename(map);
		if(num==1) {
			result.setStatus(0);
			result.setMsg("重命名成功!");
		}else {
			result.setStatus(1);
			result.setMsg("未重命名数据!");
		}
		return result;
	}

	/*
	 * 删除笔记本
	 * status=0:删除成功
	 */
	public NoteResult<Object> delBook(String bookId) {
        NoteResult<Object> result = new NoteResult<Object>();
        bookDao.delBook(bookId);
        result.setStatus(0);
        result.setMsg("删除成功!");
        return result;
    }
}