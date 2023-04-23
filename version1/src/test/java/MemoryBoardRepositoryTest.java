import gerrymandering.crud.domain.Board;
import gerrymandering.crud.repository.MemoryBoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MemoryBoardRepositoryTest {
    MemoryBoardRepository repository = new MemoryBoardRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        //given
        Board board = new Board();
        board.setTitle("Spring");
        board.setContent("I Love Spring!!");

        //when
        repository.save(board);

        //then
        Board result = repository.findById(board.getId()).get();
        Assertions.assertThat(result).isEqualTo(board);
    }

    @Test
    public void findByTitle() {
        //given
        Board board1 = new Board();
        board1.setTitle("Spring1");
        board1.setContent("스프링1");
        repository.save(board1);

        Board board2 = new Board();
        board2.setTitle("Spring2");
        board2.setContent("스프링2");
        repository.save(board2);

        //when
        Board result = repository.findByTitle("Spring1").get();

        //then
        Assertions.assertThat(result).isEqualTo(board1);
    }

    @Test
    public void findByContent() {
        //given
        Board board1 = new Board();
        board1.setTitle("Spring1");
        board1.setContent("스프링1");
        repository.save(board1);

        Board board2 = new Board();
        board2.setTitle("Spring2");
        board2.setContent("스프링2");
        repository.save(board2);

        //when
        Board result = repository.findByContent("스프링2").get();

        //then
        Assertions.assertThat(result).isEqualTo(board2);
    }

    @Test
    public void findAll() {
        //given
        Board board1 = new Board();
        board1.setTitle("Spring1");
        board1.setContent("스프링1");
        repository.save(board1);

        Board board2 = new Board();
        board2.setTitle("Spring2");
        board2.setContent("스프링2");
        repository.save(board2);

        //when
        List<Board> result = repository.findAll();

        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
