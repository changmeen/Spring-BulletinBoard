package RCM.CRUDTest.controller;

import RCM.CRUDTest.dto.LoginDTO;
import RCM.CRUDTest.dto.PostFormDTO;
import RCM.CRUDTest.dto.SignUpFormDTO;
import RCM.CRUDTest.service.interfaces.BoardService;
import RCM.CRUDTest.service.interfaces.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final MemberService memberService;
    private final BoardService boardService;
    @PostMapping("/signup")
    public ResponseEntity userSignup(@RequestBody SignUpFormDTO formDTO){
        return memberService.signup(formDTO);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO){
        return memberService.login(loginDTO);
    }

    @PostMapping("/posts")
    public ResponseEntity save(@RequestBody PostFormDTO formDTO){
        ResponseEntity responseEntity = boardService.save(formDTO);
        return responseEntity;
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity remove(@PathVariable Long id){
        ResponseEntity responseEntity = boardService.remove(id);
        return responseEntity;
    }
}
