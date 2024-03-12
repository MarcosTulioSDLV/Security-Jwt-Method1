package com.springboot.securityjwtmethod1.repositories;

import com.springboot.securityjwtmethod1.enums.RoleName;
import com.springboot.securityjwtmethod1.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    boolean existsByRoleName(RoleName roleName);

}
