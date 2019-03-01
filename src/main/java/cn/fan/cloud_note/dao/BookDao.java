package cn.fan.cloud_note.dao;

import cn.fan.cloud_note.entity.Book;

import java.util.List;
import java.util.Map;

/**
 * 对cn_notebook表的操作
 */
public interface BookDao {

	//根据cn_user_id,查询cn_notebook表中的数据
	List<Book> findByUserId(String userId);

	//向cn_notebook表中插入一条数据
	void save(Map<String,Object> map);

	//重命名笔记本
	int rename(Map<String,String> map);

	//删除笔记本
    void delBook(String bookId);
}

