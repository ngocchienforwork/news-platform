package vn.chienlengoc.dto;

public class CategoryDTO extends AbstractDTO<CategoryDTO> {

	private static final long serialVersionUID = 1306354172746578698L;
	
    private String code;
    private String name;
    
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
}
