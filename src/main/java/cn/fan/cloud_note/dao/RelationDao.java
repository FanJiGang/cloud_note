package cn.fan.cloud_note.dao;

import cn.fan.cloud_note.entity.Book;
import cn.fan.cloud_note.entity.User;

import java.util.List;

/**
 * 模拟关联映射
 */
public interface RelationDao {
	//关联多个对象
	public User findUserAndBooks(String userId);
	public User findUserAndBooks1(String userId);

	//关联单个对象
	public List<Book> findBookAndUser();
	public List<Book> findBookAndUser1();
}
