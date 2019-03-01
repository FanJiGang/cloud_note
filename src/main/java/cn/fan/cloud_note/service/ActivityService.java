package cn.fan.cloud_note.service;

import cn.fan.cloud_note.entity.Activity;
import cn.fan.cloud_note.utils.NoteResult;

import java.util.List;


/**
 * 与activity相关的业务操作接口
 */
public interface ActivityService {
	//显示所有activity信息
	NoteResult<List<Activity>> loadActivity();
	
}
