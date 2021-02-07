package bit.hibooks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boardq/*")
public class BoardqController {
	@RequestMapping("list.do")
	public String list(){
		return "boardq/list";
	}
	@RequestMapping("write.do")
	public String write() {
		return "boardq/write";
	}
}
