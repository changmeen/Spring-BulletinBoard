package RCM.CRUDTest.service.interfaces;

import RCM.CRUDTest.dto.LoginDTO;
import RCM.CRUDTest.dto.SignUpFormDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {
    ResponseEntity signup(SignUpFormDTO formDTO);
    ResponseEntity login(LoginDTO loginDTO);
}
