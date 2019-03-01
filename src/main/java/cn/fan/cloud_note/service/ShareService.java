package cn.fan.cloud_note.service;
import cn.fan.cloud_note.entity.Share;
import cn.fan.cloud_note.utils.NoteResult;

import java.util.List;
/**
 * 与share相关的业务操作接口
 */
public interface ShareService {
	//向cn_share表中存储一条数据
	NoteResult<Object> addShare(String noteId);

	//查询所有符合条件的数据
	NoteResult<List<Share>> searchNote(String keyword, int page);

	//根据id查询指定的笔记内容
	NoteResult<Share> searchShareByID(String id);


}
