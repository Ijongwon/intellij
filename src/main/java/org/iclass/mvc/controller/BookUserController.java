package org.iclass.mvc.controller;


import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.BookUser;
import org.iclass.mvc.service.BookUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/member")
public class BookUserController {
    private final BookUserService service;
    private BookUserController(BookUserService service) { this.service = service;}

    @GetMapping("/join")
    public void join(){

    }

    @PostMapping("/join")
    public String joinid(BookUser dto, RedirectAttributes reAttr){
        service.join(dto);

        reAttr.addFlashAttribute("message","회원가입이 완료 되었습니다.");
        return "redirect:/";

    }

}
