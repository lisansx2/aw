package cn.com.cslc.aw.game.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.cslc.aw.core.common.domain.BaseEntity;

@Entity
@Table(name = "SYS_GAME_SYSTEM")
public class SysGameSystem extends BaseEntity {

	private String code;
	private String name;


	@Column(name = "CODE", length = 20)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", length = 20)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
