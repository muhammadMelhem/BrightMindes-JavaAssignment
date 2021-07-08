package com.brightMinds.javaTest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Statement")
public class Statement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JoinColumn(name = "ACCOUNT_ID", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private Account account;

	@Column(name = "DATEFIELD")
	private String dateField;

	@Column(name = "AMOUNT")
	private String amount;

}
