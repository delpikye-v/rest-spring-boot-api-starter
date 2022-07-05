package com.dp.spring.template.dtos.role;

import com.dp.spring.template.models.role.Role;
import com.dp.spring.template.utils.Constants;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleDto {
    @NotBlank(message = "The role name is required.")
    private Constants.RoleSecurity name;

    public Role toRole() {
        return new Role(name);
    }
}
