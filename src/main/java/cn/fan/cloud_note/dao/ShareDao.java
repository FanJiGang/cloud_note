package cn.fan.cloud_note.dao;

import cn.fan.cloud_note.entity.Share;

import java.util.List;
import java.util.Map;

/**
 * 对数据库中cn_share表的相关操作
 */
public interface ShareDao {
	//向cn_share表中新增一条记录
	void save(Share share);
	
	//查询所有符合条件的数据
	List<Share> searchNote(Map<String, Object> map);

	//根据id查询指定的笔记内容
	Share searchShareByID(String id);

}
