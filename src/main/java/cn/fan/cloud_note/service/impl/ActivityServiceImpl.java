package cn.fan.cloud_note.service.impl;

import java.util.List;

import javax.annotation.Resource;

import cn.fan.cloud_note.dao.ActivityDao;
import cn.fan.cloud_note.entity.Activity;
import cn.fan.cloud_note.service.ActivityService;
import cn.fan.cloud_note.utils.NoteResult;
import org.springframework.stereotype.Service;

/**
 * 与activity相关的具体业务逻辑
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

	@Resource(name="activityDao")
	private ActivityDao activityDao;

	/*
	 * 显示所有activity信息
	 * status=0:成功查询到数据
	 * status=1:当前没有活动
	 */
	public NoteResult<List<Activity>> loadActivity() {
		NoteResult<List<Activity>> result=new NoteResult<List<Activity>>();
		List<Activity> list=activityDao.findAll();
		if(list.size()==0) {
			result.setStatus(1);
			result.setMsg("当前没有正在进行的活动...");
		}else {
			result.setStatus(0);
			result.setMsg("当前有"+list.size()+"个活动正在进行");
			result.setData(list);
		}
		return result;
	}

}
