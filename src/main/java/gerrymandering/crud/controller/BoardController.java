package gerrymandering.crud.controller;

import gerrymandering.crud.domain.Board;
import gerrymandering.crud.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/boards/new")
    public String createForm() {
        return "boards/createBoardForm";
    }

    @PostMapping("/boards/new")
    public String create(BoardForm form) {
        Board board = new Board();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());

        boardService.create(board);

        return "redirect:/";
    }


    @GetMapping("/boards")
    public String boardList(Model model) {
        List<Board> boards = boardService.readBoards();     //boards s왜 붙이는지?
        model.addAttribute("boardList", boards);
        return "boards/boardList";
    }
}
