package vn.chienlengoc.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.chienlengoc.core.entity.CategoryEntity;
import vn.chienlengoc.dto.CategoryDTO;

@Component
public class CategoryConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public CategoryDTO convertToDto(CategoryEntity entity) {
		CategoryDTO result = modelMapper.map(entity, CategoryDTO.class);
        return result;
    }

    public CategoryEntity convertToEntity(CategoryDTO dto) {
    	CategoryEntity result = modelMapper.map(dto, CategoryEntity.class);
        return result;
    }
}
