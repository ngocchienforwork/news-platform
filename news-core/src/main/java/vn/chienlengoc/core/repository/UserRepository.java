package vn.chienlengoc.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import vn.chienlengoc.core.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findOneByUserName(String userName);
	Page<UserEntity> findByUserNameContainingIgnoreCase(String userName, Pageable pageable);
	long countByUserNameContainingIgnoreCase(String userName);
}
