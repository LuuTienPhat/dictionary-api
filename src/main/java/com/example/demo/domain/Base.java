package com.example.demo.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@MappedSuperclass
public class Base {
	@CreatedDate
	@Column(name = "created_date")
	private LocalDate createdAt;

	@LastModifiedDate
	@Column(name = "last_modified_date")
	private LocalDate updatedAt;

//    @CreatedBy
//    private Long createdBy;
//
//    @LastModifiedBy
//    private Long updatedBy;
}
