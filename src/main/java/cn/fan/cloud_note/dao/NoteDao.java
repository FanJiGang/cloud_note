package cn.fan.cloud_note.dao;


import cn.fan.cloud_note.entity.Note;

import java.util.List;
import java.util.Map;


/**
 * 对数据库中cn_note表的相关操作
 */
public interface NoteDao {
	//根据cn_notebook_id,查询对应的笔记note
	List<Note> findNotes(Map map);

	//根据cn_notebook_id,查询对应的笔记note
	List<Map> findByBookId(String bookId);

	//根据cn_user_id,查询回收站的note
	List<Map> findRollbackNotes(String user);

	//根据cn_note_id，查询相应的笔记内容
	Note findBodyByNoteId(String noteId);

	//更新页面信息
	int updateNote(Note note);

	//更新页面信息(使用Map参数类型)
	int updateNoteByMap(Map<String,Object> map);

	//新建笔记
	void addNote(Note note);

	//更改笔记状态
	int updateNoteStatus(Map<String,Object> map);

	/*
	 * map中需要添加参数
	 * map={"ids":[id1,id2,id2...],"status":2}
	 * ids代表被删除笔记的id列表
	 * status代表被删除笔记的状态属性id
	 */
	int deleteNotes(Map<String,Object> map);

	/*
	 * 删除一条笔记
	 */
	int deleteNote(String id);
}

