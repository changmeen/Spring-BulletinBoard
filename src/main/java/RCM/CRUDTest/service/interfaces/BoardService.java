package RCM.CRUDTest.service.interfaces;

import RCM.CRUDTest.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BoardService {
    ResponseEntity save(PostFormDTO formDTO);
    ResponseEntity remove(Long id);
    ResponseEntity update(Long id, UpdateFormDTO updateFormDTO);

    List<ListDTO> getAll();

    DetailDTO getDetail(Long id, String memberId);
    UpdateDTO getUpdateDTO(Long id);
}
