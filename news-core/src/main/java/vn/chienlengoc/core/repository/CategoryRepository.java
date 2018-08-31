package vn.chienlengoc.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.chienlengoc.core.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
	CategoryEntity findOneByCode(String code);
}
