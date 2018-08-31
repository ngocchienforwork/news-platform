package vn.chienlengoc.web.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import vn.chienlengoc.constant.SystemConstant;
import vn.chienlengoc.dto.NewsDTO;
import vn.chienlengoc.service.INewsService;

@Controller(value = "newsControllerOfWeb")
public class NewsController {

	@Autowired
	private INewsService newsService;

	@RequestMapping(value = "/tin-tuc/{id}/{code}", method = RequestMethod.GET)
	public ModelAndView getNewsByCategory(@ModelAttribute("model") NewsDTO model, 
										  @PathVariable("id") long id, @PathVariable("code") String code) {
		ModelAndView mav = new ModelAndView("web/news/news-list");
		model = newsService.getAndSearchNews(model.getTitle(), id, code);
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}
	
	@RequestMapping(value = "/tin-tuc/{categoryid}/{code}/{newsid}/{title}", method = RequestMethod.GET)
	public ModelAndView getNewsDetail(@ModelAttribute("model") NewsDTO model, 
										  @PathVariable("categoryid") long categoryId, 
										  @PathVariable("code") String code,
										  @PathVariable("newsid") long newsId) {
		ModelAndView mav = new ModelAndView("web/news/news-detail");
		model = newsService.getNewsDetail(newsId);
		mav.addObject(SystemConstant.MODEL, model);
		return mav;
	}
}
