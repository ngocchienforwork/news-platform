package vn.chienlengoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vn.chienlengoc.converter.CategoryConverter;
import vn.chienlengoc.converter.NewsConverter;
import vn.chienlengoc.core.entity.CategoryEntity;
import vn.chienlengoc.core.entity.NewsEntity;
import vn.chienlengoc.core.repository.CategoryRepository;
import vn.chienlengoc.core.repository.NewsRepository;
import vn.chienlengoc.dto.CategoryDTO;
import vn.chienlengoc.dto.HomeDTO;
import vn.chienlengoc.dto.NewsDTO;
import vn.chienlengoc.service.IHomeService;

@Service
public class HomeService implements IHomeService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private CategoryConverter categoryConverter;
	
	@Autowired
	private NewsRepository newRepository; 
	
	@Autowired
	private NewsConverter newConverter;

	@Override
	public HomeDTO getHomeDetail() {
		HomeDTO homeDetail = new HomeDTO();
		homeDetail.setCategories(getAllCategory());
		homeDetail.setTops(getTops());
		homeDetail.setCreatedNews(getCreatedNews());
		return homeDetail;
	}
	
	private List<NewsDTO> getCreatedNews() {
		List<NewsDTO> result = new ArrayList<>();
		List<NewsEntity> tops = getNewsByPropertiesOrderDesc(4, "createdDate");
		tops.forEach(itemEntity -> {
			NewsDTO itemDTO = newConverter.convertToDto(itemEntity);
			itemDTO.setCategoryId(itemEntity.getCategory().getId());
			itemDTO.setCategoryCode(itemEntity.getCategory().getCode());
			itemDTO.setCategoryName(itemEntity.getCategory().getName());
			result.add(itemDTO);
		});
		return result;
	}

	private List<NewsDTO> getTops() {
		List<NewsDTO> result = new ArrayList<>();
		List<NewsEntity> tops = getNewsByPropertiesOrderDesc(4, "top");
		tops.forEach(itemEntity -> {
			NewsDTO itemDTO = newConverter.convertToDto(itemEntity);
			itemDTO.setCategoryId(itemEntity.getCategory().getId());
			itemDTO.setCategoryCode(itemEntity.getCategory().getCode());
			itemDTO.setCategoryName(itemEntity.getCategory().getName());
			result.add(itemDTO);
		});
		return result;
	}

	private List<CategoryDTO> getAllCategory() {
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOs = new ArrayList<>();
		categoryEntities.forEach(item -> {
			categoryDTOs.add(categoryConverter.convertToDto(item));
		});
		return categoryDTOs;
	}
	
	private List<NewsEntity> getNewsByPropertiesOrderDesc(int maxItems, String sortField) {
		Pageable pageable = new PageRequest(0, maxItems, new Sort(Sort.Direction.DESC, sortField));
		Page<NewsEntity> page = newRepository.findAll(pageable);
		return page.getContent();
	}
}
