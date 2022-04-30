package gerrymandering.crud.repository;

import gerrymandering.crud.domain.Board;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {

    Board save(Board board);
    Optional<Board> findById(Long id);
    Optional<Board> findByTitle(String title);
    Optional<Board> findByContent(String content);
    List<Board> findAll();
    Board update(Board board, Long id);
    void delete(Long id);
}
