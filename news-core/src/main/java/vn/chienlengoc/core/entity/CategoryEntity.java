package vn.chienlengoc.core.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class CategoryEntity extends BaseEntity {
	
	private static final long serialVersionUID = -1382942222111183561L;
	
	@Column(unique = true)
    private String code;
	
	@Column
    private String name;
	
	@OneToMany(mappedBy="category", fetch = FetchType.EAGER)
	private List<NewsEntity> news;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<NewsEntity> getNews() {
		return news;
	}

	public void setNews(List<NewsEntity> news) {
		this.news = news;
	}	
}
