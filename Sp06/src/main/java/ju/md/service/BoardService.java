package ju.md.service;

import org.springframework.web.multipart.MultipartFile;

import ju.md.domain.Board;
import ju.md.domain.BoardListResult;

public interface BoardService {
	BoardListResult getBoardListResult(int cp, int ps);
	BoardListResult getBoardListResult(String catgo, String keyword, int cp, int ps);
	Board getBoard(long seq);
	
	String write(Board board, MultipartFile file);
	boolean writeFile(MultipartFile file, String fname);
	void edit(Board board);
	void remove(long seq);

}
