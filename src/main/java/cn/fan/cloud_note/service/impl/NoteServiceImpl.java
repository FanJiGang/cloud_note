package cn.fan.cloud_note.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.fan.cloud_note.dao.NoteActivityDao;
import cn.fan.cloud_note.dao.NoteDao;
import cn.fan.cloud_note.entity.Note;
import cn.fan.cloud_note.service.NoteService;
import cn.fan.cloud_note.utils.NoteResult;
import cn.fan.cloud_note.utils.Utils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 与note相关的具体业务逻辑
 */
@Service("noteService")
@Transactional
public class NoteServiceImpl implements NoteService {
	
	@Resource(name="noteDao")
	private NoteDao noteDao;
	@Resource(name="noteActivityDao")
	private NoteActivityDao noteActivityDao;

	/*
	 * 根据笔记本id查询相应的笔记
	 * status=0:查询到了相应的笔记
	 * status=1:当前笔记本中没有笔记
	 */
	public NoteResult<List<Map>> loadNoteByBookId(String bookId) {
		NoteResult<List<Map>> result=new NoteResult<List<Map>>();
		List<Map> notes=noteDao.findByBookId(bookId);
		if(notes.size()==0) {
			result.setStatus(1);
			result.setMsg("当前笔记本中没有笔记");
		}else {
			result.setStatus(0);
			result.setMsg("查询到了"+notes.size()+"条笔记");
			result.setData(notes);
		}
		return result;
	}

	public NoteResult<List<Note>> loadNotes(String userId, int userStatus) {
		NoteResult<List<Note>> result=new NoteResult<List<Note>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId",userId);
		map.put("userStatus",userStatus);
		List<Note> notes=noteDao.findNotes(map);
		if(notes.size()==0) {
			result.setStatus(1);
			result.setMsg("当前没有笔记");
		}else {
			result.setStatus(0);
			result.setMsg("查询到了"+notes.size()+"条笔记");
			result.setData(notes);
		}
		return result;
	}

	/*
	 * 根据笔记本id查询回收站的笔记
	 * status=0:查询到了相应的笔记
	 * status=1:当前笔记本中没有笔记
	 */
	public NoteResult<List<Map>> loadRollbackNotes(String userId) {
		NoteResult<List<Map>> result=new NoteResult<List<Map>>();
		List<Map> notes=noteDao.findRollbackNotes(userId);
		if(notes.size()==0) {
			result.setStatus(1);
			result.setMsg("当前笔记本中没有笔记");
		}else {
			result.setStatus(0);
			result.setMsg("查询到了"+notes.size()+"条笔记");
			result.setData(notes);
		}
		return result;
	}

	/*
	 * 根据笔记id查询相应的笔记内容
	 * status=0:查询到了相应的笔记内容
	 */
	public NoteResult<Note> loadBodyByNoteId(String noteId) {
		NoteResult<Note> result=new NoteResult<Note>();
		Note note=noteDao.findBodyByNoteId(noteId);
		if(note!=null) {
			result.setStatus(0);
			result.setMsg("查询到了笔记内容");
			result.setData(note);
		}else {
			result.setStatus(1);
			result.setMsg("无效的笔记");
		}
		return result;
	}

	/*
	 * 更新编辑后的笔记
	 * status=0:更新成功
	 */
	public NoteResult<Object> updateNote(String noteId, String noteTitle, String noteBody) {
		NoteResult<Object> result=new NoteResult<Object>();
		Note note=new Note();
		note.setCn_note_id(noteId);
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		int num=noteDao.updateNote(note);
		if(num==1) {
			result.setStatus(0);
			result.setMsg("保存成功!");
		}else{
			result.setStatus(1);
			result.setMsg("保存失败");
		}
		return result;

	}

	/*
	 * 新建一个笔记(包含内容)
	 * status=0:新建笔记成功
	 */
	public NoteResult<String> addLikeNote(String userId, String noteTitle,String noteBody,String status) {
		NoteResult<String> result=new NoteResult<String>();
		Note note=new Note();
		String noteId=Utils.createId();
		note.setCn_note_id(noteId);
		note.setCn_user_id(userId);
		note.setCn_note_status_id(status);
		note.setCn_note_type_id("1");
		note.setCn_note_title(noteTitle);
		note.setCn_note_body(noteBody);
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		noteDao.addNote(note);
		result.setStatus(0);
		result.setMsg("创建成功!");
		result.setData(noteId);
		return result;
	}

	/*
	 * 新建一个笔记
	 * status=0:新建笔记成功
	 */
	public NoteResult<String> addNote(String userId, String bookId, String noteTitle) {
		NoteResult<String> result=new NoteResult<String>();
		Note note=new Note();
		String noteId=Utils.createId();
		note.setCn_note_id(noteId);
		note.setCn_notebook_id(bookId);
		note.setCn_user_id(userId);
		//状态：1-normal  2-delete
		note.setCn_note_status_id("1");
		//类型：1-normal  2-house(收藏)  3-share
		note.setCn_note_type_id("1");
		note.setCn_note_title(noteTitle);
		note.setCn_note_body("");
		note.setCn_note_create_time(System.currentTimeMillis());
		note.setCn_note_last_modify_time(System.currentTimeMillis());
		noteDao.addNote(note);
		result.setStatus(0);
		result.setMsg("创建成功!");
		result.setData(noteId);
		return result;
	}

	/*
	 * 更新笔记状态
	 * status=0:更新成功
	 * status=1:未更新数据
	 */
	public NoteResult<Object> updateNoteStatus(String activityId,String noteId,String noteStatus) {
		NoteResult<Object> result=new NoteResult<Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("noteActivityId",Utils.createId());
		map.put("activityId",activityId);
		map.put("noteId",noteId);
		map.put("noteStatus",Integer.parseInt(noteStatus));
		int num=noteDao.updateNoteStatus(map);
		if(num==1) {
			if(Integer.parseInt(noteStatus)==2){
				result.setMsg("删除成功!");
			}else if(Integer.parseInt(noteStatus)==1){
				result.setMsg("撤销删除成功!");
			}else if(Integer.parseInt(noteStatus)==4){
				Note note = noteDao.findBodyByNoteId(noteId);
				map.put("title",note.getCn_note_title());
				map.put("body",note.getCn_note_body());
				noteActivityDao.addNoteActivity(map);
				result.setMsg("参与活动成功");
			}
			result.setStatus(0);
		}else if(num==0){
			result.setStatus(0);
			result.setMsg("操作失败!");
		}
		return result;
	}

	/*
	 * 批量删除笔记
	 */
	public NoteResult<Object> deleteNotes(String... ids) {
		NoteResult<Object> result=new NoteResult<Object>();
		for(String id:ids) {
			int num=noteDao.deleteNote(id);
			if(num!=1) {
				//抛出异常触发事务的回滚
				throw new RuntimeException("删错了");
			}
		}
		result.setStatus(0);
		result.setMsg("删除成功!");
		return result;
	}

}