package RCM.CRUDTest.service;

import RCM.CRUDTest.domain.Board;
import RCM.CRUDTest.domain.Member;
import RCM.CRUDTest.domain.MemberRole;
import RCM.CRUDTest.dto.DetailDTO;
import RCM.CRUDTest.dto.ListDTO;
import RCM.CRUDTest.dto.PostFormDTO;
import RCM.CRUDTest.repository.BoardRepository;
import RCM.CRUDTest.repository.MemberRepository;
import RCM.CRUDTest.service.interfaces.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity save(PostFormDTO formDTO) {

        Optional<Member> member = memberRepository.findById(formDTO.getMemberId());
        if(member.isPresent()){
            Member memberEntity = member.get();

            Board post = Board.builder()
                    .title(formDTO.getTitle())
                    .content(formDTO.getContent())
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .adminViews(0)
                    .userViews(0)
                    .likes(0)
                    .member(memberEntity)
                    .build();

            boardRepository.save(post);

            return new ResponseEntity("success", HttpStatus.OK);
        } else {
            return new ResponseEntity("fail", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<ListDTO> getAll() {

        List<Board> posts = boardRepository.findAll();
        List<ListDTO> list = new ArrayList<>();

        for(Board post : posts){
            Member member = post.getMember();

            ListDTO dto = ListDTO.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .createdAt(post.getCreatedAt())
                    .userViews(post.getUserViews())
                    .adminViews(post.getAdminViews())
                    .memberName(member.getName())
                    .build();

            list.add(dto);
        }

        return list;
    }

    @Override
    public DetailDTO getDetail(Long id, String memberId) {
        Optional<Board> board = boardRepository.findById(id);
        Board boardEntity = board.orElse(null);

        Member member = boardEntity.getMember();

        if(!memberId.equals(member.getId())){
            if(member.getRole().equals(MemberRole.ADMIN)){
                boardEntity.countAdmin();
            }else {
                boardEntity.countUser();
            }
        }

        DetailDTO detailDTO = DetailDTO.builder()
                .id(boardEntity.getId())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .createdAt(boardEntity.getCreatedAt())
                .updatedAt(boardEntity.getUpdatedAt())
                .userViews(boardEntity.getUserViews())
                .adminViews(boardEntity.getAdminViews())
                .memberName(member.getName())
                .build();

        return detailDTO;
    }
}
