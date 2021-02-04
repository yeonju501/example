<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<style>
	table, th, td {
	   border: 1px solid black;
	   border-collapse: collapse;
	}
	th, td {
	   padding: 5px;
	}
	a { text-decoration:none }
</style>
<meta charset="utf-8">
<center>
	<font color='gray' size='4' face='휴먼편지체'>
	<hr width='600' size='2' color='gray' noshade>
	<h3>Spring Board ( Spring5 + MyBatis )</h3>
	<font color='gray' size='4' face='휴먼편지체'>
	<a href='write.do'>글쓰기</a>
	</font>
	<hr width='600' size='2' color='gray' noshade>
	</font>
	
	<table border='2' width='600' align='center' noshade>
	<tr>
	<td width='20%' align='center'>No</td>
	<td>${board.seq}</td>
	</tr>
	<tr>
	<td width='20%' align='center'>Writer</td>
	<td>${board.writer}</td>
	</tr>
	<tr>
	<td align='center'>E-mail</td>
	<td>${board.email}</td>
	</tr>
	<tr>
	<td align='center'>Subject</td>
	<td>${board.subject}</td>
	</tr>
	<tr>
	<td align='center'>Contents</td>
	<td>${board.content}</td>
	</tr>
	<tr>
	<td align='center'>File</td>
    <td align="center">
      <a href="download.do?fname=${board.fname}">${board.fname}</a>
    </td>
    <td align="center">${board.fsize}</td>
 	</tr>

	</table>
	
	<hr width='600' size='2' color='gray' noshade>
	<font color='gray' size='4' face='휴먼편지체'>
	<a href='update.do?seq=${board.seq}'>수정</a>
	 &nbsp;&nbsp;| 
	<a href='del.do?seq=${board.seq}&fname=${board.fname}'>삭제</a>
	 &nbsp;&nbsp;| 
	<a href='list.do'>목록</a>
	</font>
	<hr width='600' size='2' color='gray' noshade>
</center>
