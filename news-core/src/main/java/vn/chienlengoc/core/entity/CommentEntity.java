package vn.chienlengoc.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class CommentEntity extends BaseEntity {
	
	private static final long serialVersionUID = 5011239044558073745L;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	@Column(nullable = false)
	private String newsCode;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}	
}
