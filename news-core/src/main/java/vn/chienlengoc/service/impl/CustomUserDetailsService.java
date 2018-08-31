package vn.chienlengoc.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.chienlengoc.constant.CustomMessages;
import vn.chienlengoc.dto.MyUserDetail;
import vn.chienlengoc.dto.RoleDTO;
import vn.chienlengoc.dto.UserDTO;
import vn.chienlengoc.service.IUserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final Logger log = Logger.getLogger(CustomUserDetailsService.class);

	@Autowired
	private IUserService userService;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String userName) {
		UserDTO userDTO = userService.findOneByUserName(userName);

		if (userDTO.getStatus() == 0 || userDTO == null) {
			log.error(CustomMessages.ERR_USER_NOT_FOUND);
			throw new UsernameNotFoundException(CustomMessages.ERR_USER_NOT_FOUND);
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (RoleDTO role : userDTO.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}

		MyUserDetail myUserDetail = new MyUserDetail(userName, userDTO.getPassword(), true, true, true, true,
				authorities);
		BeanUtils.copyProperties(userDTO, myUserDetail);
		return myUserDetail;
	}
}
