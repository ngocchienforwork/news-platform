package vn.chienlengoc.web.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.chienlengoc.constant.SystemConstant;
import vn.chienlengoc.dto.NewsDTO;
import vn.chienlengoc.service.ICategoryService;
import vn.chienlengoc.service.INewsService;
import vn.chienlengoc.utils.DisplayTagUtils;

@Controller(value = "newsControllerOfAdmin")
public class NewsController {

	@Autowired
	private INewsService newsService;

	@Autowired
	private ICategoryService categoryService;

	@RequestMapping(value = "/admin/news/list", method = RequestMethod.GET)
	public ModelAndView getNews(@ModelAttribute(SystemConstant.MODEL) NewsDTO model, HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView("admin/news/list");
		DisplayTagUtils.initSearchBean(request, model);
		List<NewsDTO> news = newsService.getNews(model.getTitle(),
				new PageRequest(model.getPage() - 1, model.getMaxPageItems()));
		model.setListResult(news);
		model.setTotalItems(newsService.getTotalItems(model.getTitle()));
		mav.addObject(SystemConstant.MODEL, model);
		if (session.getAttribute("addOrEditNews") != null) {
			session.removeAttribute("addOrEditNews");
		}  
		return mav;
	}

	// khi sửa xong trong javax gọi lại
	@RequestMapping(value = "/admin/news/{id}", method = RequestMethod.GET)
	public ModelAndView getNewsById(@PathVariable("id") Long id, HttpSession session) {
		ModelAndView mav = new ModelAndView("admin/news/edit");
		if (session.getAttribute("addOrEditNews") == null) {
			session.setAttribute("addOrEditNews", true);
		}  
		mav.addObject(SystemConstant.MODEL, newsService.findNewsById(id));
		return mav;
	}

	// khi tạo mới gọi tới
	@RequestMapping(value = "/admin/news/edit", method = RequestMethod.GET)
	public ModelAndView editNewsPage(HttpSession session) {
		ModelAndView mav = new ModelAndView("admin/news/edit");
		if (session.getAttribute("addOrEditNews") == null) {
			session.setAttribute("addOrEditNews", true);
		} 	
		NewsDTO news = new NewsDTO();
		news.setCategories(categoryService.getCategories());
		mav.addObject(SystemConstant.MODEL, news);
		return mav;
	}
}
