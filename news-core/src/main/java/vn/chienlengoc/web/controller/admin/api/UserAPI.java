package vn.chienlengoc.web.controller.admin.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.chienlengoc.dto.UserDTO;
import vn.chienlengoc.service.IUserService;

@RestController
@RequestMapping("/ajax/user")
public class UserAPI {

	@Autowired
	private IUserService userService;
	
	@PostMapping
	public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO newUser) {
		return ResponseEntity.ok(userService.insert(newUser));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUsers(@PathVariable("id") long id, @RequestBody UserDTO userDTO) {
		return ResponseEntity.ok(userService.update(id, userDTO));
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteUsers(@RequestBody long[] idList) {
		if (idList.length > 0) {
			userService.delete(idList);
		}
		return ResponseEntity.noContent().build();
	}
}
