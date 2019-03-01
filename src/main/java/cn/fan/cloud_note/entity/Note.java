package cn.fan.cloud_note.entity;

import java.io.Serializable;

/**
 * 对应数据库中的cn_note表
 */
public class Note implements Serializable{

	private static final long serialVersionUID = -3859780190208806936L;
	private String cn_note_id;
	private String cn_notebook_id;
	private String cn_user_id;
	private String cn_note_status_id;
	private String cn_note_type_id;
	private String cn_note_title;
	private String cn_note_body;
	private Long cn_note_create_time;
	private Long cn_note_last_modify_time;
	
	public String getCn_note_id() {
		return cn_note_id;
	}
	public void setCn_note_id(String cn_note_id) {
		this.cn_note_id = cn_note_id;
	}
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
	public String getCn_note_status_id() {
		return cn_note_status_id;
	}
	public void setCn_note_status_id(String cn_note_status_id) {
		this.cn_note_status_id = cn_note_status_id;
	}
	public String getCn_note_type_id() {
		return cn_note_type_id;
	}
	public void setCn_note_type_id(String cn_note_type_id) {
		this.cn_note_type_id = cn_note_type_id;
	}
	public String getCn_note_title() {
		return cn_note_title;
	}
	public void setCn_note_title(String cn_note_title) {
		this.cn_note_title = cn_note_title;
	}
	public String getCn_note_body() {
		return cn_note_body;
	}
	public void setCn_note_body(String cn_note_body) {
		this.cn_note_body = cn_note_body;
	}
	public Long getCn_note_create_time() {
		return cn_note_create_time;
	}
	public void setCn_note_create_time(Long cn_note_create_time) {
		this.cn_note_create_time = cn_note_create_time;
	}
	public Long getCn_note_last_modify_time() {
		return cn_note_last_modify_time;
	}
	public void setCn_note_last_modify_time(Long cn_note_last_modify_time) {
		this.cn_note_last_modify_time = cn_note_last_modify_time;
	}
	
	@Override
	public String toString() {
		return "Note [cn_note_id=" + cn_note_id + ", cn_notebook_id=" + cn_notebook_id + ", cn_user_id=" + cn_user_id
				+ ", cn_note_status_id=" + cn_note_status_id + ", cn_note_type_id=" + cn_note_type_id
				+ ", cn_note_title=" + cn_note_title + ", cn_note_body=" + cn_note_body + ", cn_note_create_time="
				+ cn_note_create_time + ", cn_note_last_modify_time=" + cn_note_last_modify_time + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cn_note_body == null) ? 0 : cn_note_body.hashCode());
		result = prime * result + ((cn_note_create_time == null) ? 0 : cn_note_create_time.hashCode());
		result = prime * result + ((cn_note_id == null) ? 0 : cn_note_id.hashCode());
		result = prime * result + ((cn_note_last_modify_time == null) ? 0 : cn_note_last_modify_time.hashCode());
		result = prime * result + ((cn_note_status_id == null) ? 0 : cn_note_status_id.hashCode());
		result = prime * result + ((cn_note_title == null) ? 0 : cn_note_title.hashCode());
		result = prime * result + ((cn_note_type_id == null) ? 0 : cn_note_type_id.hashCode());
		result = prime * result + ((cn_notebook_id == null) ? 0 : cn_notebook_id.hashCode());
		result = prime * result + ((cn_user_id == null) ? 0 : cn_user_id.hashCode());
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
		Note other = (Note) obj;
		if (cn_note_body == null) {
			if (other.cn_note_body != null)
				return false;
		} else if (!cn_note_body.equals(other.cn_note_body))
			return false;
		if (cn_note_create_time == null) {
			if (other.cn_note_create_time != null)
				return false;
		} else if (!cn_note_create_time.equals(other.cn_note_create_time))
			return false;
		if (cn_note_id == null) {
			if (other.cn_note_id != null)
				return false;
		} else if (!cn_note_id.equals(other.cn_note_id))
			return false;
		if (cn_note_last_modify_time == null) {
			if (other.cn_note_last_modify_time != null)
				return false;
		} else if (!cn_note_last_modify_time.equals(other.cn_note_last_modify_time))
			return false;
		if (cn_note_status_id == null) {
			if (other.cn_note_status_id != null)
				return false;
		} else if (!cn_note_status_id.equals(other.cn_note_status_id))
			return false;
		if (cn_note_title == null) {
			if (other.cn_note_title != null)
				return false;
		} else if (!cn_note_title.equals(other.cn_note_title))
			return false;
		if (cn_note_type_id == null) {
			if (other.cn_note_type_id != null)
				return false;
		} else if (!cn_note_type_id.equals(other.cn_note_type_id))
			return false;
		if (cn_notebook_id == null) {
			if (other.cn_notebook_id != null)
				return false;
		} else if (!cn_notebook_id.equals(other.cn_notebook_id))
			return false;
		if (cn_user_id == null) {
			if (other.cn_user_id != null)
				return false;
		} else if (!cn_user_id.equals(other.cn_user_id))
			return false;
		return true;
	}
	
	
}
