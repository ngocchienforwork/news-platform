package vn.chienlengoc.dto;

import java.io.Serializable;
import java.util.List;

public class HomeDTO implements Serializable {
	
	private static final long serialVersionUID = -912592755673071348L;
	
	private List<CategoryDTO> categories;
	private List<NewsDTO> slides;
	private NewsDTO firstItemSlides;
	private List<NewsDTO> tops;
	private List<NewsDTO> createdNews;
	
	public List<CategoryDTO> getCategories() {
		return categories;
	}

	public void setCategories(List<CategoryDTO> categories) {
		this.categories = categories;
	}

	public List<NewsDTO> getSlides() {
		return slides;
	}

	public void setSlides(List<NewsDTO> slides) {
		this.slides = slides;
	}

	public List<NewsDTO> getTops() {
		return tops;
	}

	public void setTops(List<NewsDTO> tops) {
		this.tops = tops;
	}

	public List<NewsDTO> getCreatedNews() {
		return createdNews;
	}

	public void setCreatedNews(List<NewsDTO> createdNews) {
		this.createdNews = createdNews;
	}

	public NewsDTO getFirstItemSlides() {
		return firstItemSlides;
	}

	public void setFirstItemSlides(NewsDTO firstItemSlides) {
		this.firstItemSlides = firstItemSlides;
	}
}
