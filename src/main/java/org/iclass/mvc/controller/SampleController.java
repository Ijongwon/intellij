package org.iclass.mvc.controller;

import org.iclass.mvc.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//url 이 community로 시작하면 DispatcherServlet 으로 부터 CommuityController 가 위임받아 처리합니다.
@Controller
@RequestMapping("/sample")	

public class SampleController {

	private final CommunityService service;

	private SampleController(CommunityService service) {
		this.service = service;
	}

	//여기서부터는 요청을 처리하는 핸들러 메소드 입니다.
	//URL 설게 : 글 목록 조회 : /community/list , 글쓰기 /community/writer , 글 읽기 /community/read


	@GetMapping("/hello")
	public void list(Model model) {
		model.addAttribute("message", "하이 스프링");
		model.addAttribute("list", List.of("모모", "나연", "nana", "쯔위"));


	}
}

