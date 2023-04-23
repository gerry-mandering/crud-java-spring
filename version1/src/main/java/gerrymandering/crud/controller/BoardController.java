package gerrymandering.crud.controller;

import gerrymandering.crud.domain.Board;
import gerrymandering.crud.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //생성하기
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

        return "redirect:/boards";
    }

    //읽기
    @GetMapping("/boards")
    public String boardList(Model model) {
        List<Board> boards = boardService.readBoards();     //boards s왜 붙이는지?
        model.addAttribute("boardList", boards);
        return "boards/boardList";
    }

    @GetMapping("/boards/view")
    public String boardView(@RequestParam("id") Long id, Model model) { //위에서의 모델과 이거 모델과 다른가?
        Board board = boardService.readOne(id).get();
        model.addAttribute("board", board);
        return "boards/boardDetail";
    }

    //수정하기
    @GetMapping("/boards/update/{id}")
    public String updateForm(@PathVariable Long id, Model model) {
        Board board = boardService.readOne(id).get();
        model.addAttribute("boardUpdate", board);
        return "boards/updateBoardForm";
    }

    @PostMapping("/boards/update/{id}")
    public String update(@PathVariable Long id, BoardForm form) {
        Board board = new Board();
        board.setTitle(form.getTitle());
        board.setContent(form.getContent());

        boardService.update(board, id);

        return "redirect:/boards";
    }

    @DeleteMapping("boards/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);

        return "redirect:/boards";
    }
}
