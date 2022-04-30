package gerrymandering.crud;

import gerrymandering.crud.domain.Board;
import gerrymandering.crud.repository.BoardRepository;
import gerrymandering.crud.repository.MemoryBoardRepository;
import gerrymandering.crud.service.BoardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public BoardService boardService() {
        return new BoardService(boardRepository());
    }

    @Bean
    public BoardRepository boardRepository() {
        return new MemoryBoardRepository();
    }
}
