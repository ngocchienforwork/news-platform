package vn.chienlengoc.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import vn.chienlengoc.dto.UserDTO;

public interface IUserService {
	UserDTO findOneByUserName(String userName);
	UserDTO findUserById(long id);
	void delete(long[] ids);
	List<UserDTO> getUsers(String searchValue, Pageable pageable);
	int getTotalItems(String searchValue);
	UserDTO insert(UserDTO userDTO);
	UserDTO update(Long id, UserDTO userDTO);
}
