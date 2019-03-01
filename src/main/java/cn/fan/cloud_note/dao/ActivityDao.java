package cn.fan.cloud_note.dao;

import cn.fan.cloud_note.entity.Activity;

import java.util.List;

/**
 * 对cn_activity表相关的操作
 */
public interface ActivityDao {
	//查询出所有的活动信息
	List<Activity> findAll();
}

