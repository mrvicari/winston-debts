package com.mrvicari.winstondebts.repository;

import com.mrvicari.winstondebts.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
