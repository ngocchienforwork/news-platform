package vn.chienlengoc.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import vn.chienlengoc.constant.SystemConstant;
import vn.chienlengoc.converter.NewsConverter;
import vn.chienlengoc.core.entity.CategoryEntity;
import vn.chienlengoc.core.entity.NewsEntity;
import vn.chienlengoc.core.entity.UserEntity;
import vn.chienlengoc.core.repository.CategoryRepository;
import vn.chienlengoc.core.repository.NewsRepository;
import vn.chienlengoc.dto.NewsDTO;
import vn.chienlengoc.service.ICategoryService;
import vn.chienlengoc.service.INewsService;
import vn.chienlengoc.utils.StringGenerate;
import vn.chienlengoc.utils.UploadFileUtils;

@Service
public class NewsService implements INewsService {

	@Autowired
	private NewsRepository newsRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private NewsConverter newsConverter;

	@Autowired
	private ICategoryService categoryService;

	public List<NewsDTO> getNews(String title, Pageable pageable) {
		Page<NewsEntity> newsPage = null;
		if (title != null) {
			newsPage = newsRepository.findByTitleContainingIgnoreCase(title, pageable);
		} else {
			newsPage = newsRepository.findAll(pageable);
		}
		List<NewsEntity> newsEntities = newsPage.getContent();
		List<NewsDTO> result = new ArrayList<NewsDTO>();
		for (NewsEntity newsEntity : newsEntities) {
			NewsDTO newsDTO = newsConverter.convertToDto(newsEntity);
			result.add(newsDTO);
		}
		return result;
	}

	@Override
	public int getTotalItems(String title) {
		int totalItem = 0;
		if (title != null) {
			totalItem = (int) newsRepository.countByTitleContainingIgnoreCase(title);
		} else {
			totalItem = (int) newsRepository.count();
		}
		return totalItem;
	}

	@Override
	@Transactional
	public NewsDTO insert(NewsDTO newsDTO) {
		String thumbnail = SystemConstant.THUMBNAIL_DIR + File.separator + newsDTO.getImageName();
		newsDTO.setThumbnail(thumbnail);
		UploadFileUtils.writeOrUpdate(thumbnail, newsDTO.getThumbnailBase64());
		NewsEntity newsEntity = newsConverter.convertToEntity(newsDTO);
		newsEntity.setCode(StringGenerate.generateValue(5));
		newsEntity.setCategory(categoryRepository.findOneByCode(newsDTO.getCategoryCode()));
		newsEntity = newsRepository.save(newsEntity);
		return newsConverter.convertToDto(newsEntity);
	}

	@Override
	public NewsDTO update(long id, NewsDTO newNews) {
		NewsEntity oldNews = newsRepository.findOne(id);
		oldNews.setTop(newNews.getTop());
		oldNews.setTitle(newNews.getTitle());
		oldNews.setDescription(newNews.getDescription());
		oldNews.setCategory(categoryRepository.findOneByCode(newNews.getCategoryCode()));
		if (!StringUtils.isEmpty(newNews.getImageName())) {
			String thumbnail = SystemConstant.THUMBNAIL_DIR + File.separator + newNews.getImageName();
			oldNews.setThumbnail(thumbnail);
			UploadFileUtils.writeOrUpdate(thumbnail, newNews.getThumbnailBase64());
		}
		oldNews = newsRepository.save(oldNews);
		return newsConverter.convertToDto(oldNews);
	}

	@Override
	public NewsDTO findNewsById(long id) {
		NewsEntity entity = newsRepository.findOne(id);
		NewsDTO dto = newsConverter.convertToDto(entity);
		dto.setCategoryCode(entity.getCategory().getCode());
		dto.setCategories(categoryService.getCategories());
		return dto;
	}

	@Override
	public List<NewsDTO> findNewsByCategory(long id, Pageable pageable, String title) {
		CategoryEntity categoryEntity = categoryRepository.findOne(id);
		List<NewsEntity> newEntities = new ArrayList<>();
		if (title == null) {
			title = "";
		}
		newEntities = newsRepository.findByCategoryAndTitleContainingIgnoreCase(categoryEntity, title, pageable)
				.getContent();
		List<NewsDTO> newsDTOs = new ArrayList<>();
		newEntities.forEach(item -> {
			NewsDTO newDTO = newsConverter.convertToDto(item);
			newsDTOs.add(newDTO);
		});
		return newsDTOs;
	}

	@Override
	public int getTotalItemsByCategoryAndTitle(String title, long id) {
		CategoryEntity category = categoryRepository.findOne(id);
		int totalItem = 0;
		if (title == null) {
			title = "";
		}
		totalItem = (int) newsRepository.countByTitleContainingIgnoreCaseAndCategory(title, category);
		return totalItem;
	}

	@Override
	public NewsDTO getNewsDetail(long id) {
		NewsEntity newsEntity = newsRepository.findOne(id);
		newsEntity.setView(newsEntity.getView() + 1);
		newsEntity = newsRepository.save(newsEntity);
		return newsConverter.convertToDto(newsEntity);
	}

	@Override
	public NewsDTO getAndSearchNews(String title, long id, String code) {
		NewsDTO model = new NewsDTO();
		model.setCategoryId(id);
		model.setCategoryCode(code);
		if (title != null && StringUtils.isNotEmpty(title)) {
			model.setPage(1);
		}
		Pageable pageable = new PageRequest(model.getPage() - 1, model.getMaxPageItems());
		model.setListResult(findNewsByCategory(id, pageable, title));
		model.setTotalItems(getTotalItemsByCategoryAndTitle(title, id));
		model.setTotalPages((int) Math.ceil((double) model.getTotalItems() / model.getMaxPageItems()));
		return model;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (Long id : ids) {
			newsRepository.delete(id);
		}
	}

}
