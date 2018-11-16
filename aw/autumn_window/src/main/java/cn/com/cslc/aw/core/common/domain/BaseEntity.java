package cn.com.cslc.aw.core.common.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 抽象实体基类，提供统一的ID
 */
@MappedSuperclass
public  class BaseEntity{
	
	private Long id;

	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(generator = "ID_GENERATOR")
	@Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}

