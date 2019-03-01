package cn.fan.cloud_note.entity;

import java.io.Serializable;
/**
 * 对应数据库中的cn_share表
 */
public class Share implements Serializable{

	private static final long serialVersionUID = 7594306609581688162L;
	private String cn_share_id;
	private String cn_share_title;
	private String cn_share_body;
	private String cn_note_id;
	
	public String getCn_share_id() {
		return cn_share_id;
	}
	public void setCn_share_id(String cn_share_id) {
		this.cn_share_id = cn_share_id;
	}
	public String getCn_share_title() {
		return cn_share_title;
	}
	public void setCn_share_title(String cn_share_title) {
		this.cn_share_title = cn_share_title;
	}
	public String getCn_share_body() {
		return cn_share_body;
	}
	public void setCn_share_body(String cn_share_body) {
		this.cn_share_body = cn_share_body;
	}
	public String getCn_note_id() {
		return cn_note_id;
	}
	public void setCn_note_id(String cn_note_id) {
		this.cn_note_id = cn_note_id;
	}
	
	@Override
	public String toString() {
		return "Share [cn_share_id=" + cn_share_id + ", cn_share_title=" + cn_share_title + ", cn_share_body="
				+ cn_share_body + ", cn_note_id=" + cn_note_id + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cn_note_id == null) ? 0 : cn_note_id.hashCode());
		result = prime * result + ((cn_share_body == null) ? 0 : cn_share_body.hashCode());
		result = prime * result + ((cn_share_id == null) ? 0 : cn_share_id.hashCode());
		result = prime * result + ((cn_share_title == null) ? 0 : cn_share_title.hashCode());
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
		Share other = (Share) obj;
		if (cn_note_id == null) {
			if (other.cn_note_id != null)
				return false;
		} else if (!cn_note_id.equals(other.cn_note_id))
			return false;
		if (cn_share_body == null) {
			if (other.cn_share_body != null)
				return false;
		} else if (!cn_share_body.equals(other.cn_share_body))
			return false;
		if (cn_share_id == null) {
			if (other.cn_share_id != null)
				return false;
		} else if (!cn_share_id.equals(other.cn_share_id))
			return false;
		if (cn_share_title == null) {
			if (other.cn_share_title != null)
				return false;
		} else if (!cn_share_title.equals(other.cn_share_title))
			return false;
		return true;
	}
	
	
}
