package vn.chienlengoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.chienlengoc.converter.UserConverter;
import vn.chienlengoc.core.entity.RoleEntity;
import vn.chienlengoc.core.entity.UserEntity;
import vn.chienlengoc.core.repository.RoleRepository;
import vn.chienlengoc.core.repository.UserRepository;
import vn.chienlengoc.dto.UserDTO;
import vn.chienlengoc.service.IUserService;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO findOneByUserName(String userName) {
		UserEntity userEntity = userRepository.findOneByUserName(userName);
		UserDTO userDTO = userConverter.convertToDto(userEntity);
		return userDTO;
	}

	@Override
	public List<UserDTO> getUsers(String userName, Pageable pageable) {
		Page<UserEntity> newsPage = null;
		if (userName != null) {
			newsPage = userRepository.findByUserNameContainingIgnoreCase(userName, pageable);
		} else {
			newsPage = userRepository.findAll(pageable);
		}
		List<UserEntity> newsEntities = newsPage.getContent();
		List<UserDTO> result = new ArrayList<UserDTO>();
		for (UserEntity userEntity : newsEntities) {
			UserDTO userDTO = userConverter.convertToDto(userEntity);
			result.add(userDTO);
		}
		return result;
	}

	@Override
	public int getTotalItems(String userName) {
		int totalItem = 0;
		if (userName != null) {
			totalItem = (int) userRepository.countByUserNameContainingIgnoreCase(userName);
		} else {
			totalItem = (int) userRepository.count();
		}
		return totalItem;
	}

	@Override
	@Transactional
	public UserDTO insert(UserDTO newUser) {
		RoleEntity role = roleRepository.findOneByCode(newUser.getRoleCode());
		List<RoleEntity> roles = new ArrayList<>();
		roles.add(role);
		UserEntity userEntity = userConverter.convertToEntity(newUser);
		userEntity.setRoles(roles);
		userEntity.setStatus(1);
		userEntity.setPassword(passwordEncoder.encode(newUser.getPassword()));
		return userConverter.convertToDto(userRepository.save(userEntity));
	}

	@Override
	public UserDTO findUserById(long id) {
		UserEntity entity = userRepository.findOne(id);
		List<RoleEntity> roles = entity.getRoles();
		UserDTO dto = userConverter.convertToDto(entity);
		roles.forEach(item -> {
			dto.setRoleCode(item.getCode());
		});
		return dto;
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for (Long item : ids) {
			UserEntity userEntity = userRepository.findOne(item);
			userEntity.setStatus(0);
			userRepository.save(userEntity);
		}
	}

	@Override
	@Transactional
	public UserDTO update(Long id, UserDTO updateUser) {
		RoleEntity role = roleRepository.findOneByCode(updateUser.getRoleCode());
		List<RoleEntity> roles = new ArrayList<>();
		roles.add(role);
		UserEntity oldUser = userRepository.findOne(id);
		oldUser.setEmail(updateUser.getEmail());
		oldUser.setFullName(updateUser.getFullName());
		oldUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));
		oldUser.setPhoneNumber(updateUser.getPhoneNumber());
		oldUser.setStatus(updateUser.getStatus());
		oldUser.setRoles(roles);
		return userConverter.convertToDto(userRepository.save(oldUser));
	}
}
