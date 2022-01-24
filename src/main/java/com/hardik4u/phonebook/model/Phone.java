package com.hardik4u.phonebook.model;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Phone {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(
	        name = "UUID",
	        strategy = "org.hibernate.id.UUIDGenerator"
	)
	@ColumnDefault("random_uuid()")
	@Type(type = "uuid-char")
	private UUID phoneId;
	
	@Column(name = "phone_name",nullable = false)
	private String phoneName;
	
	@Column(name = "phone_number",nullable = false)
	private String phoneNumber;
	
	@Column(name = "model",nullable = false)
	private PhoneModel model;
	
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false,cascade=CascadeType.ALL)
	@JoinColumn(name="user_id")
	@MapsId 
	private User user;
	
	public void setUser(User user) {
		this.user =user;
		this.user.setUserId(user.getUserId());
	}
}
