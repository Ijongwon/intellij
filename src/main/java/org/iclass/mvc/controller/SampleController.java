package org.iclass.mvc.controller;

import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

//url 이 community로 시작하면 DispatcherServlet 으로 부터 CommuityController 가 위임받아 처리합니다.
@Controller
@RequestMapping("/sample")	
@Log4j2
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
	@GetMapping("/spring")
	public void spring(Community community,
					   @RequestParam(required = false) String name,
					   @RequestParam(required = false) Integer age, Model model){
		log.info("파라미터 name :{}", name);
		log.info("파라미터 age :{}",age);
		log.info("Community dto : {}" , community);		//Community 클래스의 모든 필드들이 파라미터로 전달 될 수 있습니다.
		//required = false 로 하면 파라미터 값이 null 이 되어야 하므로
		//int,long 들은 Integer, Long 과 같이 래퍼(Wrapper) 타입으로 선언합니다.
		model.addAttribute("name", name);
	}
	//복습 : 10월 4일 *****중요*****
	//DTO 클래스는 파라미터를 받고 다시 view 의 Model(뷰로 전달하는 데이터 저장 객체)로 전달도 할 수 있습니다.
	// 				ㄴ 이 때 model 저장된 데이터의 이름은 dto 변수명과 같음.
	//int, long 등 기본형과 String은 파라미터 받은 것을 Model에 직접 저장해야 view에 전달됩니다.

}


