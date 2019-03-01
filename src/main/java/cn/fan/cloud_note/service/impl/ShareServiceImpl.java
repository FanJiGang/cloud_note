package cn.fan.cloud_note.service.impl;

import cn.fan.cloud_note.dao.NoteDao;
import cn.fan.cloud_note.dao.ShareDao;
import cn.fan.cloud_note.entity.Note;
import cn.fan.cloud_note.entity.Share;
import cn.fan.cloud_note.service.ShareService;
import cn.fan.cloud_note.utils.NoteResult;
import cn.fan.cloud_note.utils.Utils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 与share相关的具体业务逻辑
 */
@Repository("shareService")
@Transactional  //事务管理注解
public class ShareServiceImpl implements ShareService {

	@Resource(name="shareDao")
	private ShareDao shareDao;
	@Resource(name="noteDao")
	private NoteDao noteDao;

	//向cn_share表中存储一条记录
	public NoteResult<Object> addShare(String noteId) {
		NoteResult<Object> result=new NoteResult<Object>();
		Share share=new Share();
		//根据noteId查询出对应的标题和内容
		Note note=noteDao.findBodyByNoteId(noteId);
		if(note!=null) {
			String shareTitle=note.getCn_note_title();
			String shareBody=note.getCn_note_body();
			share.setCn_note_id(noteId);
			share.setCn_share_id(Utils.createId());
			share.setCn_share_title(shareTitle);
			share.setCn_share_body(shareBody);
			shareDao.save(share);
			result.setStatus(0);
			result.setMsg("分享成功!");

			//模拟异常
			//String str=null;
			//System.out.println(str.length());
		}else {
			result.setStatus(1);
			result.setMsg("分享失败!");
		}
		return result;
	}

	//查询所有符合条件的数据
	public NoteResult<List<Share>> searchNote(String keyword,int page) {
		keyword="%"+keyword+"%";
		int begin=(page-1)*5;//计算抓取记录的起点
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("keyword", keyword);
		map.put("begin", begin);
		NoteResult<List<Share>> result=new NoteResult<List<Share>>();
		List<Share> list=shareDao.searchNote(map);
		if(list.size()==0) {  //查询0条笔记
			result.setStatus(1);
			result.setMsg("未查询到符合条件的笔记!");
		}else {  //查询到了笔记
			result.setStatus(0);
			result.setMsg("查询到了"+list.size()+"条笔记");
			result.setData(list);
		}
		return result;
	}

	//根据id查询指定的笔记内容
	public NoteResult<Share> searchShareByID(String id) {
		NoteResult<Share> result = new NoteResult<Share>();
		Share share=shareDao.searchShareByID(id);
		if(share!=null) {
			result.setStatus(0);
			result.setMsg("查询到了分享笔记内容");
			result.setData(share);
		}else {
			result.setStatus(1);
			result.setMsg("无效的分享笔记");
		}
		return result;
	}

}