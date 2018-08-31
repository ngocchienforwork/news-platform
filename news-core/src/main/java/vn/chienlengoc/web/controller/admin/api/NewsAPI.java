package vn.chienlengoc.web.controller.admin.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.chienlengoc.dto.NewsDTO;
import vn.chienlengoc.service.INewsService;

@RestController
@RequestMapping("/ajax/news")
public class NewsAPI {

	@Autowired
	private INewsService newsService;

	@PostMapping
	public ResponseEntity<NewsDTO> createNews(@RequestBody NewsDTO newsDTO, HttpSession session) {
		try {
			if (session.getAttribute("addOrEditNews") != null) {
				NewsDTO result = newsService.insert(newsDTO);
				session.removeAttribute("addOrEditNews");
				return ResponseEntity.ok(result);
			}
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<NewsDTO> updateNews(@PathVariable("id") long id, @RequestBody NewsDTO newsDTO,
			HttpSession session) {
		try {
			if (session.getAttribute("addOrEditNews") != null) {
				NewsDTO result = newsService.update(id, newsDTO);
				session.removeAttribute("addOrEditNews");
				return ResponseEntity.ok(result);
			}
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteNews(@RequestBody long[] idList) {
		if (idList.length > 0) {
			newsService.delete(idList);
		}
		return ResponseEntity.noContent().build();
	}
}
