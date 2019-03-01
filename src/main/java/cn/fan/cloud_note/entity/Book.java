package cn.fan.cloud_note.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 对应数据库中的cn_notebook表
 */
public class Book implements Serializable{

	private static final long serialVersionUID = 8640734801831824076L;
	private String cn_notebook_id;
	private String cn_user_id;
	private String cn_notebook_type_id;
	private String cn_notebook_name;
	private String cn_notebook_desc;
	private Timestamp cn_notebook_createtime;
	private User user;
	
	public String getCn_notebook_id() {
		return cn_notebook_id;
	}
	public void setCn_notebook_id(String cn_notebook_id) {
		this.cn_notebook_id = cn_notebook_id;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_notebook_type_id() {
		return cn_notebook_type_id;
	}
	public void setCn_notebook_type_id(String cn_notebook_type_id) {
		this.cn_notebook_type_id = cn_notebook_type_id;
	}
	public String getCn_notebook_name() {
		return cn_notebook_name;
	}
	public void setCn_notebook_name(String cn_notebook_name) {
		this.cn_notebook_name = cn_notebook_name;
	}
	public String getCn_notebook_desc() {
		return cn_notebook_desc;
	}
	public void setCn_notebook_desc(String cn_notebook_desc) {
		this.cn_notebook_desc = cn_notebook_desc;
	}
	public Timestamp getCn_notebook_createtime() {
		return cn_notebook_createtime;
	}
	public void setCn_notebook_createtime(Timestamp cn_notebook_createtime) {
		this.cn_notebook_createtime = cn_notebook_createtime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Book [cn_notebook_id=" + cn_notebook_id + ", cn_user_id=" + cn_user_id + ", cn_notebook_type_id="
				+ cn_notebook_type_id + ", cn_notebook_name=" + cn_notebook_name + ", cn_notebook_desc="
				+ cn_notebook_desc + ", cn_notebook_createtime=" + cn_notebook_createtime + ", user=" + user + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cn_notebook_createtime == null) ? 0 : cn_notebook_createtime.hashCode());
		result = prime * result + ((cn_notebook_desc == null) ? 0 : cn_notebook_desc.hashCode());
		result = prime * result + ((cn_notebook_id == null) ? 0 : cn_notebook_id.hashCode());
		result = prime * result + ((cn_notebook_name == null) ? 0 : cn_notebook_name.hashCode());
		result = prime * result + ((cn_notebook_type_id == null) ? 0 : cn_notebook_type_id.hashCode());
		result = prime * result + ((cn_user_id == null) ? 0 : cn_user_id.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (cn_notebook_createtime == null) {
			if (other.cn_notebook_createtime != null)
				return false;
		} else if (!cn_notebook_createtime.equals(other.cn_notebook_createtime))
			return false;
		if (cn_notebook_desc == null) {
			if (other.cn_notebook_desc != null)
				return false;
		} else if (!cn_notebook_desc.equals(other.cn_notebook_desc))
			return false;
		if (cn_notebook_id == null) {
			if (other.cn_notebook_id != null)
				return false;
		} else if (!cn_notebook_id.equals(other.cn_notebook_id))
			return false;
		if (cn_notebook_name == null) {
			if (other.cn_notebook_name != null)
				return false;
		} else if (!cn_notebook_name.equals(other.cn_notebook_name))
			return false;
		if (cn_notebook_type_id == null) {
			if (other.cn_notebook_type_id != null)
				return false;
		} else if (!cn_notebook_type_id.equals(other.cn_notebook_type_id))
			return false;
		if (cn_user_id == null) {
			if (other.cn_user_id != null)
				return false;
		} else if (!cn_user_id.equals(other.cn_user_id))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
}
