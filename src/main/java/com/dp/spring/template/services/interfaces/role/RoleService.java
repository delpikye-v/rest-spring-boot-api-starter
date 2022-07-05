package com.dp.spring.template.services.interfaces.role;

import com.dp.spring.template.models.role.Role;
import com.dp.spring.template.utils.Constants;

public interface RoleService {

    Role findByName(Constants.RoleSecurity name);
}
