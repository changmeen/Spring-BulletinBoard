package RCM.CRUDTest.service.interfaces;

import RCM.CRUDTest.dto.DetailDTO;
import RCM.CRUDTest.dto.ListDTO;
import RCM.CRUDTest.dto.PostFormDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {
    ResponseEntity save(PostFormDTO formDTO);

    ResponseEntity remove(Long id);

    List<ListDTO> getAll();

    DetailDTO getDetail(Long id, String memberId);
}
