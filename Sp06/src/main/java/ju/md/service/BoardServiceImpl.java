package ju.md.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ju.md.domain.Board;
import ju.md.domain.BoardListResult;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import ju.md.domain.Path;
import ju.md.mapper.BoardMapper;
import ju.md.domain.BoardVo;

@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService {
	private BoardMapper boardMapper;

	@Override
	public BoardListResult getBoardListResult(int cp, int ps) {
		long totalCount = boardMapper.selectCount();
		BoardVo boardVo = new BoardVo(null, null, cp, ps);
		List<Board> list = boardMapper.selectPerPage(boardVo);
		
		return new BoardListResult(cp, totalCount, ps, list);
	}

	@Override
	public BoardListResult getBoardListResult(String catgo, String keyword, int cp, int ps) {
		BoardVo boardVo = new BoardVo(catgo, keyword, cp, ps);
		long totalCount = boardMapper.selectCountByCatgo(boardVo);
		List<Board> list = boardMapper.selectByCatgo(boardVo);
		
		return new BoardListResult(cp, totalCount, ps, list);
	}

	@Override
	public Board getBoard(long seq) {
		return boardMapper.selectBySeq(seq);
	}

	@Override
	public String write(Board board, MultipartFile file) {
		String ofname = file.getOriginalFilename();
		int idx = ofname.lastIndexOf(".");
		String ofheader = ofname.substring(0, idx);
		String ext = ofname.substring(idx);
		long ms = System.currentTimeMillis();
		StringBuilder sb = new StringBuilder();
		sb.append(ofheader);
		sb.append("_");
		sb.append(ms);
		sb.append(ext);
		String fname = sb.toString();
		board.setOfname(ofname);
		board.setFname(fname);
		long fsize= file.getSize();
		board.setFsize(fsize);
		boardMapper.insert(board);
		boolean flag = writeFile(file, fname);
		if(flag) {
			log.info("#업로드 성공");
		}else {
			log.info("#업로드 실패");
		}
		return Path.FILE_STORE + fname;
	}
	
	@Override
	public boolean writeFile(MultipartFile file, String fname) {
		File rDir = new File(Path.FILE_STORE);
		if(!rDir.exists()) rDir.mkdirs();
		
		FileOutputStream fos = null;
		try {
			byte data[] = file.getBytes();
			fos = new FileOutputStream(Path.FILE_STORE+fname);
			fos.write(data);
			fos.flush();
			
			return true;
		}catch(IOException ie) {
			return false;
		}finally {
			try {
				if(fos != null) fos.close();
			}catch(IOException ie) {}
		}
	}

	@Override
	public void edit(Board board) {
		boardMapper.update(board);

	}

	@Override
	public void remove(long seq) {
		boardMapper.delete(seq);

	}

}
