package bit.hibooks.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class BoardqVo {
	String catgo;
	String keyword;
	private int cp;
	private int ps;
	
	{
		cp=1;
		ps=10;
	}
	
	public int getStartRow() {
		return (cp-1)*ps;
	}
	public int getEndRow() {
		return cp*ps;
	}

}
