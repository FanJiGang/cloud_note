package cn.fan.cloud_note.dao;

import cn.fan.cloud_note.entity.Emp;

/**
 * 对t_emp表的相关操作
 */
public interface EmpDao {

	//保存一条记录
	public void save(Emp emp);
}
