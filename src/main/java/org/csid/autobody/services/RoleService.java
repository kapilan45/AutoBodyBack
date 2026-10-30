package org.csid.autobody.services;

import org.csid.autobody.entity.RoleEntity;
import org.csid.autobody.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public RoleEntity findByRoleName(String role){
        return roleRepository.findByRole(role);
    }
}
