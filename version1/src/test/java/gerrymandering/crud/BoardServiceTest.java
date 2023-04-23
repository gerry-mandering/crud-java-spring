package gerrymandering.crud;

import gerrymandering.crud.domain.Board;
import gerrymandering.crud.repository.MemoryBoardRepository;
import gerrymandering.crud.service.BoardService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class BoardServiceTest {

    BoardService boardService;
    MemoryBoardRepository boardRepository;

    @BeforeEach
    public void beforeEach() {
        boardRepository = new MemoryBoardRepository();
        boardService = new BoardService(boardRepository);
    }

    @AfterEach
    public void afterEach() {
        boardRepository.clearStore();
    }

    @Test
    public void 게시글작성() throws Exception {
        //given
        Board board = new Board();
        board.setTitle("Spring");
        board.setContent("I Love Spring!!");

        //when
        Long saveId = boardService.create(board);

        //then
        Board result = boardRepository.findById(saveId).get();
        assertEquals(board.getTitle(), result.getTitle());
    }

    @Test
    public void 중복_제목_예외() throws Exception {
        //given
        Board board1 = new Board();
        board1.setTitle("Spring");
        board1.setContent("I Love Spring!!");

        Board board2 = new Board();
        board2.setTitle("Spring");
        board2.setContent("I Love Spring!!");

        //when
        boardService.create(board1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> boardService.create(board2));

        //then
        assertThat(e.getMessage()).isEqualTo("게시글이 이미 존재합니다.");
    }
}
