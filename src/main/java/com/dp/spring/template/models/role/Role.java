package com.dp.spring.template.models.role;

import com.dp.spring.template.utils.Constants;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private Constants.RoleSecurity name;

	public Role() {

	}

	public Role(Constants.RoleSecurity name) {
		this.name = name;
	}
}