package vn.chienlengoc.service;

import java.util.List;
import java.util.Map;

import vn.chienlengoc.dto.RoleDTO;

public interface IRoleService {
	List<RoleDTO> findAll();
	Map<String,String> getRoles();
}
