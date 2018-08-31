package vn.chienlengoc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.chienlengoc.core.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findOneByCode(String code);
}
