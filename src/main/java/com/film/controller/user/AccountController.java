package com.film.controller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.film.services.CommentService;
import com.film.services.UserService;

@Controller
public class AccountController {
	LoadController loadController = new LoadController();
	
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	
	private AccountController(LoadController loadController) {
        this.loadController = loadController;
    }
	
	@GetMapping("/my-account")
	public String myAccount(Model model, RedirectAttributes redirectAttributes) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		if(redirectAttributes.getAttribute("notification") != null)
			model.addAttribute("notification", redirectAttributes.getAttribute("notification"));
		return "myAccount";
	}
	
	@GetMapping("/my-account/diem-danh")
	public String diemDanh(Model model) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		return "diem-danh";
	}
	
	@PostMapping("/my-account/upload-image")
	public String uploadImg(@RequestParam String img, @RequestParam int id) {
		if(id > 0 && img != null && !img.isEmpty()) {
			userService.updateAvatar(img, id);
			commentService.updateImage(img, id);
			loadController.csUser().getUser().setImg(img);
		}
		return "myAccount";
	}
	
	@GetMapping("/my-account/edit-pass")
	public String changePassword(Model model, RedirectAttributes redirectAttributes) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		return "editPass";
	}
	
	@GetMapping("/my-account/cap-nhat-biet-danh")
	public String udNName(Model model, RedirectAttributes redirectAttributes) {
		loadController.categoryShow(model);
		loadController.genresShow(model);
		if(redirectAttributes.getAttribute("notification") != null) 
			model.addAttribute("notification", redirectAttributes.getAttribute("notification"));
		return "updateNName";
	}
	
	@PostMapping("/my-account/cap-nhat-biet-danh")
	public String editName(RedirectAttributes redirectAttributes, @RequestParam String display_name, @RequestParam int point) {
		int idUser = loadController.getUserIdFromUserDetails().intValue();
		if(idUser > 0 && display_name != null && !display_name.isEmpty()) {
			Object[][] userModel = userService.queryByPoint(idUser);
			if((int) userModel[0][0] < 50)
				redirectAttributes.addFlashAttribute("notification", "Tu vi không đủ để thực hiện");
			else {
				if(!userModel[0][1].equals(display_name)) {
					userService.updateFullName(display_name, (point - 50), idUser);
					loadController.csUser().getUser().setFullName(display_name);
					loadController.csUser().getUser().setPoint(point-50);
					redirectAttributes.addFlashAttribute("notification", "");
				} else 
					redirectAttributes.addFlashAttribute("notification", "Biệt danh mới trùng với biệt danh hiện tại");
			}
		}
		return "redirect:/cap-nhat-biet-danh";
	}
	
	@PostMapping("/my-account/update-maxim")
	public String updateMaxim(@RequestParam String user_bio, RedirectAttributes redirectAttributes) {
		int idUser = loadController.getUserIdFromUserDetails().intValue();
		if(idUser > 0) {
			Object[][] userModel = userService.queryByPoint(idUser);
			if(!userModel[0][2].equals(user_bio.trim())) {			
				userService.updateMaxim(user_bio, idUser);
				loadController.csUser().getUser().setMaxim(user_bio);
				redirectAttributes.addFlashAttribute("notification", "Cập nhật thành công");
			}
		}
		return "redirect:/my-account";
	}
	
	@PostMapping("/updateIsActivity")
    public ResponseEntity<?> updateIsActivity(@RequestBody Map<String, Integer> requestBody) {
		int userId = requestBody.get("id");
		int notifical = requestBody.get("notifical");
		if(notifical == 0) userService.updateIsActivity(0, loadController.dateTime(), userId);
		else userService.updateIsActivity(1, "", userId);
        return ResponseEntity.ok().build();
    }
	
}
