package bit.hibooks.service;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import bit.hibooks.domain.Boardq;
import bit.hibooks.domain.BoardqContentResult;
import bit.hibooks.domain.BoardqListResult;

public interface BoardqService {
	BoardqListResult getBoardqListResult(int cp, int ps);
	BoardqContentResult getBoardq(long qb_seq);
	void write(Boardq boardq, ArrayList<MultipartFile> files);
	void writeFile(MultipartFile file, String fname);
	void reply(Boardq boardq);
	void edit(Boardq boardq);
	void remove(long qb_seq);

}
