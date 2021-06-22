package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller("userControllerApi")
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@GetMapping("/checkid")
	public JsonResult checkid(@RequestParam(value="id", required=true, defaultValue="") String id) {
		
		UserVo userVo = userService.getUserById(id);
		System.out.println(id);
		return JsonResult.success(userVo !=null);
	}
}
