package com.osa.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
	@Id
	private String userName;
	private String firstName;
	private String lastName;
	private String userPassword;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name="user_role",
	joinColumns = {
			@JoinColumn(name="User_Id")
	},
	inverseJoinColumns = {
			@JoinColumn(name="Role_Id")
	}
	)
	private Set<Role> role;

}
