package com.dp.spring.template.services.implement.role;

import com.dp.spring.template.services.interfaces.role.RoleService;
import com.dp.spring.template.exceptions.NotFoundException;
import com.dp.spring.template.models.role.Role;
import com.dp.spring.template.repositories.role.RoleRepository;
import com.dp.spring.template.services.implement.base.BaseService;
import com.dp.spring.template.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseService implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findByName(Constants.RoleSecurity name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Not found Role: " + name));
    }
}
