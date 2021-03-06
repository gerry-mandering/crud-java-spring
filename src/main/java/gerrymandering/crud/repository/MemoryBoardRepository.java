package gerrymandering.crud.repository;

import gerrymandering.crud.domain.Board;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryBoardRepository implements BoardRepository {

    private static Map<Long, Board> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Board save(Board board) {
        board.setId(++sequence);
        store.put(board.getId(), board);
        return board;
    }

    @Override
    public Optional<Board> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Board> findByTitle(String title) {
        return store.values().stream()
                .filter(board -> board.getTitle().equals(title))
                .findAny();
    }

    @Override
    public Optional<Board> findByContent(String content) {
        return store.values().stream()
                .filter(board -> board.getContent().equals(content))
                .findAny();
    }

    @Override
    public List<Board> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Board update(Board board, Long id) {
        board.setId(id);
        store.put(id, board);
        return board;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    public void clearStore() {
        store.clear();
    }
}
