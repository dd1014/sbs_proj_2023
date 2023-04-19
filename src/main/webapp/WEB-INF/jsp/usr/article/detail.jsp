<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="pageTitle" value="게시물 리스트"/>
<%@ include file="../common/head.jspf"  %>	

<%-- 	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>작성날짜</th>
				<th>수정날짜</th>
				<th>작성자</th>
				<th>제목</th>
			</tr>
		</thead>
	<tbody>
		<tr>
		<td>${article.id}</td>
		<td>${article.regDate.substring(2, 16)}</td>
		<td>${article.updateDate.substring(2, 16)}</td>
		<td>${article.memberId}</td>
		<td>${article.title }</td>
		</tr>
	</tbody>
	
	
	</table> --%>
	
	<section class="mt-5">
		<table border="1">
			<tbody>
				<tr>
					<th>번호</th>
					<td>${article.id }</td>
				</tr>
				<tr>
					<th>작성날짜</th>
					<td>${article.regDate.substring(2, 16) }</td>
				</tr>
				<tr>
					<th>수정날짜</th>
					<td>${article.updateDate.substring(2, 16) }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${article.memberId }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${article.title }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${article.body }</td>
				</tr>
			</tbody>
		</table>
		<div>
			<button type="button" onclick="history.back();">뒤로가기</button>
		</div>
	</section>
	
	
	
<%@ include file="../common/foot.jspf"  %>	