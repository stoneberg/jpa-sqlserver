package com.stone.play.common.audit;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditorBaseEntity<U> {

	@CreatedBy
	@Column(name = "created_by", length = 75, updatable = false)
	protected U createdBy;

	@LastModifiedBy
	@Column(name = "updated_by", length = 75)
	protected U updatedBy;

	@CreatedDate
	@Column(name = "created_dt", updatable = false)
	protected LocalDateTime createdDt;

	@Column(name = "updated_dt")
	@LastModifiedDate
	protected LocalDateTime updatedDt;

}
