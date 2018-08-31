package vn.chienlengoc.web.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.chienlengoc.constant.SystemConstant;
import vn.chienlengoc.dto.UserDTO;
import vn.chienlengoc.service.IUserService;
import vn.chienlengoc.service.impl.RoleService;
import vn.chienlengoc.utils.DisplayTagUtils;

@Controller(value = "usersControllerOfAdmin")
public class UserController {

	@Autowired
	private IUserService userService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/admin/user/list", method = RequestMethod.GET)
	public ModelAndView getAllUser(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/list");
		DisplayTagUtils.initSearchBean(request, model);

		List<UserDTO> news = userService.getUsers(model.getSearchValue(),
				new PageRequest(model.getPage() - 1, model.getMaxPageItems()));
		model.setListResult(news);
		model.setTotalItems(userService.getTotalItems(model.getSearchValue()));
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}

	@RequestMapping(value = "/admin/user/edit", method = RequestMethod.GET)
	public ModelAndView addUser(@ModelAttribute(SystemConstant.MODEL) UserDTO model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/user/edit");
		model.setRoleDTOs(roleService.getRoles());
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}

	@RequestMapping(value = "/admin/user/edit/{id}", method = RequestMethod.GET)
	public ModelAndView updateUser(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView("admin/user/edit");
		UserDTO model = userService.findUserById(id);
		model.setRoleDTOs(roleService.getRoles());
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}
}
