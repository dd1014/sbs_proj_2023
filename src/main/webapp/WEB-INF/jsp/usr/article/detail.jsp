<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시물 리스트" />
<%@ include file="../common/head.jspf"%>

<script>
	const params = {}
	params.id = parseInt('${param.id}');
</script>

<script>
	function ArticleDetail_increseHitCount() {
		const localStorageKey = 'article_' + params.id + '_viewDone';

		if (localStorage.getItem(localStorageKey)) {
			return;
		}

		localStorage.setItem(localStorageKey, true);

		$.get('../article/doIncreaseHitCountRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail_hit-count').empty().html(data.data1)
		}, 'json');
	}
	$(function() {
		//실전
		ArticleDetail_increseHitCount();
		//   임시코드
		//   setTimeout(ArticleDetail_increseHitCount, 3000);
	})
</script>

<section class="mt-5">
		<div class="container mx-auto px-3">
				<div class="table-box-type-1">
						<table>
								<colgroup>
										<col width="200" />
								</colgroup>
								<tbody>
										<tr>
												<th>번호</th>
												<td>${article.id}</td>
										</tr>
										<tr>
												<th>작성날짜</th>
												<td>${article.getRegDateForPrint()}</td>
										</tr>
										<tr>
												<th>수정날짜</th>
												<td>${article.getUpdateDateForPrint()}</td>
										</tr>
										<tr>
												<th>작성자</th>
												<td>${article.extra_writerName}</td>
										</tr>
										<tr>
												<th>조회수</th>
												<td>
														<span class="text-blue-700 article-detail__hit-count">${article.hitCount }</span>
												</td>
										</tr>
										<tr>
												<th>추천</th>
												<td>
														<div class="flex items-center">
																<span class="text-blue-700">${article.goodReactionPoint }</span>
																<span>&nbsp;</span>

																<c:if test="${actorCanMakeReaction }">
																		<a
																				href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}"
																				class="btn btn-xs btn-outline btn-primary"> 좋아요👍 </a>
																		<span>&nbsp;</span>
																		<a
																				href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}"
																				class="btn btn-xs btn-outline btn-secondary"> 싫어요👎 </a>
																</c:if>

																<c:if test="${actorCanCancelGoodReaction }">
																		<a
																				href="/usr/reactionPoint/doCancelGoodReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}"
																				class="btn btn-xs btn btn-primary"> 좋아요👍 </a>
																		<span>&nbsp;</span>
																		<a onclick="alert(this.title); return false;" title="먼저 좋아요를 취소해주세요." href="#"
																				class="btn btn-xs btn-outline btn-secondary"> 싫어요👎 </a>
																</c:if>

																<c:if test="${actorCanCancelBadReaction }">
																		<a
																				href="/usr/reactionPoint/doCancelBadReaction?relTypeCode=article&relId=${param.id}&replaceUri=${rq.encodedCurrentUri}"
																				class="btn btn-xs btn btn-primary"> 좋아요👍 </a>
																		<span>&nbsp;</span>
																		<a onclick="alert(this.title); return false;" title="먼저 싫어요를 취소해주세요." href="#"
																				class="btn btn-xs btn-secondary"> 싫어요👎 </a>
																</c:if>

														</div>
												</td>
										</tr>
										<tr>
												<th>제목</th>
												<td>${article.title}</td>
										</tr>
										<tr>
												<th>내용</th>
												<td>${article.body}</td>
										</tr>
								</tbody>
						</table>
				</div>
				<div class="btns">
						<button class="btn btn-link" type="button" onclick="history.back();">뒤로가기</button>

						<c:if test="${article.extra_actorCanModify }">
								<a class="btn btn-link" href="../article/modify?id=${article.id}">게시물 수정</a>
						</c:if>

						<c:if test="${article.extra_actorCanDelete }">
								<a class="btn btn-link" onclick="if( confirm('정말 삭제하시겠습니까?') == false )return false;"
										href="../article/doDelete?id=${article.id} ">게시물 삭제</a>
						</c:if>
				</div>
		</div>
</section>

<section class="mt-5">
	<div class="container mx-auto px-3">
	<h1>댓글 작성</h1>
 	<c:if test="${rq.isLogined() }">
	 	<form class="table-box-type-1" method="post" action="../reply/doWrite">
	 	  <input type="hidden" name="relTypeCode" value="article"/>
	 	  <input type="hidden" name="relId" value="${article.id }"/>
	     
	      <table>
	      <colgroup>
	        <col width="200"/>
	      </colgroup>
	        <tbody>
	     
	          <tr>
	            <th>작성자</th>
	            <td>${rq.loginedMember.nickname}</td>
	          </tr>
	          
	          <tr>
	            <th>내용</th>
	            <td>
	               <textarea required="required"  class="w-full textarea textarea-bordered" name="body" placeholder="내용" ></textarea>
	            </td>
	          </tr>
	        
	         <tr>
	            <th>댓글작성</th>
	            <td>
	               <input type="submit" class="btn btn-primary" value="댓글작성" />
	            </td>
	          </tr>
	        </tbody>
	      </table>
	    </form>
 	</c:if>

 	<c:if test="${rq.isNotLogined() }">
 		<a class="btn btn-link" href="/usr/member/login">로그인</a>후 이용해주세
 	</c:if>
    </div>
</section>


<%@ include file="../common/foot.jspf"%>
