<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/commJsp.jsp"%>

<!-- BEGIN Content -->
<div class="row">
	<div class="col-lg-12">
		
			<article id="node-445302" class="node node-article left-sidebar">
				<header class="article-header" id="divHeader">
					<!-- 기사 제목 -->
					<h1 class="page-header">
						<c:if test="${not empty article}">
							${article.title} | <small>{gategory}</small>
						</c:if>
					</h1>
	
					<!-- 작성자, 작성일, 수정일 -->
					<c:if test="${not empty article}">
						<div class="byline" id="divByLine">
						By 
						<span class="author">
							<a href="#" class="author-name">${article.userID}</a>
						</span>
						<time itemprop="datePublished"
							datetime="${article.createdOn}"> ${article.createdOn}</time>
						<time itemprop="dateModified" datetime="${article.updatedOn}"></time>
					</div>
					</c:if>
					
				</header>

				<div class="article-content">
					<!-- 이미지 -->
					<figure itemscope="" itemtype="http://schema.org/ImageObject"
						itemprop="image"
						itemid="http://www.kakaocorp.com/images/operating/temp_career_151001.png">
						<picture itemprop="contentUrl" class="mapping-medium"> 
							<!--[if IE 9]><video style="display: none;"><![endif]-->
							<source
								srcset="http://www.kakaocorp.com/images/operating/temp_career_151001.png 1x"
								media="(min-width: 1200px)">
								<!-- (min-width: 992px), (min-width: 768px), (min-width: 481px), (min-width: 0px) -->
							<!--[if IE 9]></video><![endif]--> 
							
							<img itemprop="contentUrl"
								class="mapping-medium"
								src="http://www.kakaocorp.com/images/operating/temp_career_151001.png"
								title=""> 
						</picture>
						
						<figcaption>
							<span class="caption" itemprop="caption">{image caption}</span> <span
								class="credit" itemprop="copyrightHolder">{image copyrightHolder}</span>
						</figcaption>
					</figure>
					<br>
					
					<!-- 키워드 -->
					<div class="filed-under clearfix">
						<ol class="breadcrumb">
						  <li class="active">{keyword1}</li>
						  <li><a href="#">{keyword2}</a></li>
						  <li><a href="#">{keyword2}</a></li>
						</ol>
					</div>

				</div>
				
				<!-- 본문시작  -->
				<div class="article-body" itemprop="articleBody">
					<c:if test="${not empty article}">
						${article.content}
					</c:if>
				</div>
				<br>

				<div class="content-correction">
					<div class="content-correction-links">
						<a class="btn-reprint" href="http://ibtreprints.com/Newsweek/"
							target="_blank">Request Reprint</a> or <span
							class="user-btn btn-correct"><a class="btn-correction"
							href="#">Submit Correction</a></span>
					</div>
					<div class="content-correction-holder"></div>
				</div>
			</article>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- END Content -->


<c:if test="${not empty article}">
	<c:set var="articleId" value="${article.boardID}"/>
</c:if>

<!-- BEGIN Script -->
<script>
	var $articleId = '${articleId}';
	
	var $divHeader = $( '#divHeader' );
	
	$(document).ready(function() {
		
		var url = '/ajax/boards/' + $articleId
		jQuery.get( url, function( data ){			
			console.log( data );
			if( data.result == '1' ){
				alert( data.message );
				return;
			}
			
			binddingViewData( data.data );
			
			
			
			
			//$divHeader
			
			
		} );

	}); // init jquery
	
	function binddingViewData( content ){
		
		// title
		var titleArea = $('<h1/>', {
			class : 'page-header',
			text: content.title + ' | '
		} );
		titleArea.append( $( '<small/>', { text : 'test' } ) );
		
		// byline
		var divByLine = $( '<div/>', { //class: byline, 
			text: 'By ' } );
		var spanAuthor = $( '<span/>', { //class : author 
			} );
		spanAuthor.append( $( '<a/>', {
			//class : 'author-name',
			href : '#',
			text : content.userID + ' '
		} ) );
		
		// by line
		divByLine.append( spanAuthor );
		var createdOn = '';
		if( content.createdOn ){
			var date = new Date(content.createdOn);
			createdOn = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
		} 
		
		divByLine.append( $('<time/>', { 
			itemprop: 'datePublished',
			datetime:  createdOn,
			text : createdOn
			} ) );
		
		divByLine.append( $('<time/>', { 
			itemprop: 'dateModified',
			datetime: content.updatedOn,
			text : content.updatedOn
			} ) );
		
		$divHeader.empty();
		$divHeader.append( titleArea );
		$divHeader.append( divByLine );
	}

</script>
<!-- END Script -->







