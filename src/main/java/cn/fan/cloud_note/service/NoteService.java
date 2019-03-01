package cn.fan.cloud_note.service;

import cn.fan.cloud_note.entity.Note;
import cn.fan.cloud_note.utils.NoteResult;

import java.util.List;
import java.util.Map;

/**
 * 与note相关的业务操作接口
 */
public interface NoteService {
	//根据笔记本id查询相应的笔记
	NoteResult<List<Map>> loadNoteByBookId(String bookId);

	//查询符合条件的笔记本
	NoteResult<List<Note>> loadNotes(String userId,int status);

	//根据笔记本id查询回收站的笔记
	NoteResult<List<Map>> loadRollbackNotes(String userId);

	//根据笔记id查询对应的笔记内容
	NoteResult<Note> loadBodyByNoteId(String noteId);

	//更新编辑后的笔记
	NoteResult<Object> updateNote(String noteId,String noteTitle,String noteBody);

	//新建一个笔记
	NoteResult<String> addNote(String userId,String bookId,String noteTitle);

	//新建一个笔记(包含内容)
	NoteResult<String> addLikeNote(String userId, String noteTitle,String noteBody,String status);

	//更改笔记状态
	NoteResult<Object> updateNoteStatus(String activityId,String noteId,String noteStatus);

	//批量删除笔记
	//String... 动态参数,就是String[] 数组
	NoteResult<Object> deleteNotes(String... ids);
}
