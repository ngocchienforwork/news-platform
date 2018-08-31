package vn.chienlengoc.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.chienlengoc.core.entity.NewsEntity;
import vn.chienlengoc.dto.NewsDTO;

@Component
public class NewsConverter {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public NewsDTO convertToDto(NewsEntity entity) {
		NewsDTO result = modelMapper.map(entity, NewsDTO.class);
        return result;
    }

    public NewsEntity convertToEntity(NewsDTO dto) {
    	NewsEntity result = modelMapper.map(dto, NewsEntity.class);
        return result;
    }
}
