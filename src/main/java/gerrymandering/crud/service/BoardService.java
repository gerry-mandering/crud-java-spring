package gerrymandering.crud.service;

import gerrymandering.crud.domain.Board;
import gerrymandering.crud.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class BoardService {

    private final BoardRepository boardRepository;

    //@Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시글 작성
    public Long create(Board board) {
        validateDuplicateBoard(board);  //제목과 내용 둘다 같으면 중복
        boardRepository.save(board);
        return board.getId();
    }

    private void validateDuplicateBoard(Board board) {  //??????????????????????????

        Optional<Board> result = boardRepository.findByTitle(board.getTitle());
        if (result.isPresent()) {
            boardRepository.findByContent(board.getContent())
                    .ifPresent(m -> {
                        throw new IllegalStateException("게시글이 이미 존재합니다.");
                    });
        }
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 제목입니다.");
//                });
    }

    //게시글 전체조회
    public List<Board> readBoards() {
        return boardRepository.findAll();
    }

    //게시글 조회
    public Optional<Board> readOne(Long boardId) {
        return boardRepository.findById(boardId);
    }
}
