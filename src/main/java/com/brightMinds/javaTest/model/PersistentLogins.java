package com.brightMinds.javaTest.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "persistent_logins")
public class PersistentLogins {

	@Id
	@Column(name = "series")
	private String series;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "token", nullable = false)
	private String token;

	@Column(name = "last_used", nullable = false)
	private Timestamp lastUsed;

}
