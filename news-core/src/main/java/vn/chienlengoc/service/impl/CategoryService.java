package vn.chienlengoc.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.chienlengoc.core.entity.CategoryEntity;
import vn.chienlengoc.core.repository.CategoryRepository;
import vn.chienlengoc.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Map<String, String> getCategories() {
		Map<String,String> categories = new LinkedHashMap<String,String>();
		List<CategoryEntity> entites = categoryRepository.findAll();
		for (CategoryEntity item: entites) {
			categories.put(item.getCode(), item.getName());
		}
		return categories;
	}
}
