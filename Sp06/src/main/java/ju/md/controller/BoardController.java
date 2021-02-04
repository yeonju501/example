package ju.md.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import ju.md.domain.Board;
import ju.md.domain.BoardListResult;
import ju.md.service.BoardService;
import lombok.AllArgsConstructor;
import ju.md.domain.Path;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	
	private BoardService service;//Spring 4.3 ~: AutoInjection (with @AllArgsConstructor )
	
	@RequestMapping("list.do")
	public ModelAndView list(HttpServletRequest request, HttpSession session) {//
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		//(1) cp 
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		
		//(2) ps 
		int ps = 3;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			
			ps = psParam;
		}
		session.setAttribute("ps", ps);
		
		//(3) ModelAndView 
		BoardListResult listResult = service.getBoardListResult(cp, ps);
		ModelAndView mv = new ModelAndView("board/list", "listResult", listResult); // board/list.do에 listResult이름으로 listResult를 보냄
		
		if(listResult.getList().size() == 0) {
			if(cp>1)
				return new ModelAndView("redirect:list.do?cp="+(cp-1));
			else
				return new ModelAndView("board/list", "listResult", null);
		}else {
			return mv;
		}
	}
	
	@RequestMapping("listcatgo.do")
	public ModelAndView listcatgo(HttpServletRequest request, HttpSession session) {//
		String cpStr = request.getParameter("cp");
		String psStr = request.getParameter("ps");
		String catgo = request.getParameter("catgo");
		String keyword = request.getParameter("keyword");
		session.setAttribute("catgo", catgo);
		session.setAttribute("keyword", keyword);
		//(1) cp 
		int cp = 1;
		if(cpStr == null) {
			Object cpObj = session.getAttribute("cp");
			if(cpObj != null) {
				cp = (Integer)cpObj;
			}
		}else {
			cpStr = cpStr.trim();
			cp = Integer.parseInt(cpStr);
		}
		session.setAttribute("cp", cp);
		
		//(2) ps 
		int ps = 3;
		if(psStr == null) {
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				ps = (Integer)psObj;
			}
		}else {
			psStr = psStr.trim();
			int psParam = Integer.parseInt(psStr);
			
			Object psObj = session.getAttribute("ps");
			if(psObj != null) {
				int psSession = (Integer)psObj;
				if(psSession != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}else {
				if(ps != psParam) {
					cp = 1;
					session.setAttribute("cp", cp);
				}
			}
			
			ps = psParam;
		}
		session.setAttribute("ps", ps);
		
		//(3) ModelAndView 
		BoardListResult listResult = service.getBoardListResult(catgo,keyword,cp, ps);
		ModelAndView mv = new ModelAndView("board/listcatgo", "listResult", listResult); // board/list.do에 listResult이름으로 listResult를 보냄
		
		if(listResult.getList().size() == 0) {
			if(cp>1)
				return new ModelAndView("redirect:listcatgo.do?cp="+(cp-1));
			else
				return new ModelAndView("board/listcatgo", "listResult", null);
		}else {
			return mv;
		}
	}
	
	@GetMapping("write.do")
	public String write() {
		return "board/write";
	}
	
	@PostMapping("write.do")
	public String write(Board board, @RequestParam MultipartFile file) {
		String ofname = file.getOriginalFilename();
		if(ofname.length() != 0) {
			String url = service.write(board, file);
		}
		return "redirect:list.do";
	}
	
	@GetMapping("download.do")
	public ModelAndView download(@RequestParam String fname) {
		File file = new File(Path.FILE_STORE, fname);
    	if(file.exists()) {
    		return new ModelAndView("fileDownloadView", "downloadFile", file);
    	}else {
    		return new ModelAndView("redirect:list.do");
    	}
	}
	
	
	@GetMapping("content.do")
	public ModelAndView content(long seq) {
		Board board = service.getBoard(seq);
		ModelAndView mv = new ModelAndView("board/content", "board", board);
		return mv;
	}
	
	@GetMapping("update.do")
	public ModelAndView update(long seq) {
		Board board = service.getBoard(seq);
		ModelAndView mv = new ModelAndView("board/update", "board", board);	
		return mv;
	}
	
	@PostMapping("update.do")
	public String update(Board board) {
		service.edit(board);
		return "redirect:list.do";
	}
	
	@GetMapping("del.do")
	public String delete(long seq, String fname) {
		File file = new File(Path.FILE_STORE, fname);
		if(file.exists()) file.delete();
		service.remove(seq);
		return "redirect:list.do";
	}

}
