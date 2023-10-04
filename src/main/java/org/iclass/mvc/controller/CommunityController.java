package org.iclass.mvc.controller;

import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.PageRequestDTO;
import org.iclass.mvc.dto.PageResponseDTO;
import org.iclass.mvc.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Log4j2
@Controller
@RequestMapping("/community")
public class CommunityController {
    private final CommunityService service;

    private CommunityController(CommunityService service){
        this.service = service;
    }

   /* @GetMapping("/list")
    public void list(
            @RequestParam(defaultValue = "1") int page,
            Model model){
        model.addAttribute("list", service.pagelist(page).get("list"));
        model.addAttribute("paging", service.pagelist(page).get("paging"));
        model.addAttribute("today", LocalDate.now());

    }*/
    @GetMapping("/read")
    public void read(long idx, @ModelAttribute("page") int page, Model model){
        model.addAttribute("vo",service.read(idx));
        model.addAttribute("cmtlist",service.commentsList(idx));
    }

    @GetMapping("/write")
    public void write(){

    }

    @PostMapping("/write")
    public String save(Community dto, RedirectAttributes reAttr){
        service.insert(dto);

        reAttr.addFlashAttribute("message","글 등록이 완료 되었습니다.");
        return "redirect:/community/list";
    }

    @GetMapping("/list")
    public void pagelist(PageRequestDTO pageRequestDTO,Model model){
        log.info(">>>>>>> pageRequestDTO : {}" , pageRequestDTO);
        PageResponseDTO responseDTO = service.listWithSearch(pageRequestDTO);
        responseDTO.getList().forEach(i ->{
            log.info(">>>>> 글 : {}", i);
        });
        model.addAttribute("list",responseDTO.getList());
        model.addAttribute("paging",responseDTO);
        model.addAttribute("page",pageRequestDTO.getPage());

    }

}
