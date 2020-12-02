package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.revature.utilities.Hasher;

@Entity
@Table(name="Users")
public class Users {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@Column(name="username", unique=true)
	private String username;
	
	@Column(name="password_hash")
	private String password;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email", unique=true)
	private String email;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Role_FK", referencedColumnName="role_id")
	private Role role;
	
	public Users() {}

	//All args - id constructor.
	public Users(String username, String password, String firstName, String lastName, String email, Role role) {
		super();
		this.username = username;
		//Encrypt the password.
		setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	//All args constructor.
	public Users(int userId, String username, String password, String firstName, String lastName, String email,
			Role role) {
		super();
		this.userId = userId;
		this.username = username;
		//Encrypt the password.
		setPassword(password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}

	//username and password constructor.
	public Users(String username, String password) {
		super();
		this.username = username;
		setPassword(password);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	
	//Make sure to encrypt the password upon setting it.
	public void setPassword(String password) {
		String hashedPass = new Hasher(password).getHashed();
		this.password = hashedPass;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	//Without password, even if it's just a hash, bad security practice.
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", username=" + username + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", role=" + role.getRoleId() + "]";
	}
	
	

}