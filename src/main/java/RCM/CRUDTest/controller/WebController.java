package RCM.CRUDTest.controller;

import RCM.CRUDTest.dto.DetailDTO;
import RCM.CRUDTest.dto.ListDTO;
import RCM.CRUDTest.dto.UpdateDTO;
import RCM.CRUDTest.dto.UpdateFormDTO;
import RCM.CRUDTest.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class WebController {

    private final BoardServiceImpl boardService;

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, @CookieValue("id") String memberId){
        DetailDTO post = boardService.getDetail(id, memberId);
        model.addAttribute("post", post);

        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable Long id, Model model){
        UpdateDTO post = boardService.getUpdateDTO(id);
        model.addAttribute("post", post);

        return "update";
    }

    @GetMapping("/")
    public String index(Model model){
        List<ListDTO> posts = boardService.getAll();
        model.addAttribute("posts", posts);

        return "home";
    }

    @GetMapping("/signup")
    public String signup(){

        return "signup";
    }

    @GetMapping("/signin")
    public String signin(){

        return "signin";
    }

    @GetMapping("/new")
    public String newPost() {

        return "new";
    }
}
