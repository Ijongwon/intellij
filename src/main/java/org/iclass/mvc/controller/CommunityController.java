package org.iclass.mvc.controller;

import lombok.extern.log4j.Log4j2;
import org.iclass.mvc.dto.Community;
import org.iclass.mvc.dto.CommunityComments;
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
    @GetMapping({"/read","/update"})
    public void read(PageRequestDTO pageRequestDTO,long idx,Model model) {
        Community community = service.read(idx);
        model.addAttribute("dto",community);
        //요청이 /read 이면 뷰 리졸버가 read.html로 요청 전달
        //요청이 /update 이면 뷰 리졸버가 update.html로 요청 전달
    }
    @PostMapping("/update") //pageRequestDTO를 받아서 수정 후에도 검색이 유지되도록 함
    public String update(PageRequestDTO pageRequestDTO, Community community
            ,RedirectAttributes re){
        String link = pageRequestDTO.getLink();
        log.info(">>>>>>>>>link : {}", link);
        service.update(community);
        re.addFlashAttribute("result","글 수정이 완료되었습니다.");
        re.addAttribute("idx",community.getIdx());
        return "redirect:/community/read?"+link;
    }


    @GetMapping("/write")
    public void write(){

    }

    @PostMapping("/write")
    public String save(Community dto, RedirectAttributes reAttr){
        service.insert(dto);

        reAttr.addFlashAttribute("result","글 등록이 완료 되었습니다.");
        return "redirect:/community/list";
    }

    @GetMapping("/list")
    private void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO responseDTO = service.listWithSearch(pageRequestDTO);

        //list.html에 전달할 model 관련코드 작성하고 list.html 완성, 레이아웃 적용
        log.info(">>>>>>>>DTO : {}" , pageRequestDTO);
        model.addAttribute("list",responseDTO.getList());
        model.addAttribute("pageRequestDTO",pageRequestDTO);
        model.addAttribute("currentPage",pageRequestDTO.getPage());
        model.addAttribute("paging",responseDTO);
        model.addAttribute("today", LocalDate.now());


    }

    @PostMapping("/comments")
    public String comments(int page, int f , CommunityComments dto,
                           RedirectAttributes redirectAttributes) {
        log.info(">>>>>>> dto : {}",dto);
        service.comments(dto,f);
        redirectAttributes.addAttribute("page",page);
        redirectAttributes.addAttribute("idx",dto.getMref());
        if(f==1) {
            redirectAttributes.addFlashAttribute("message","댓글 등록이 완료되었습니다.");
        }
        else if(f==2) {
            redirectAttributes.addFlashAttribute("message","댓글 삭제가 완료되었습니다.");
        }
        //return "redirect:/community/read?page="+page+"&idx="+dto.getMref();
        return "redirect:/community/read"; //리다이렉트 애트리뷰트 사용하므로 쿼리스트링 사용X
    }

    @PostMapping("/delete") //pageRequestDTO를 받아서 수정 후에도 검색이 유지되도록 함
    public String delete(PageRequestDTO pageRequestDTO, Long idx, RedirectAttributes re){
        service.delete(idx);
        re.addFlashAttribute("result",
                "글 삭제가 완료되었습니다.("+idx+"번)");
        return "redirect:/community/list?"+pageRequestDTO.getLink();
    }



}
