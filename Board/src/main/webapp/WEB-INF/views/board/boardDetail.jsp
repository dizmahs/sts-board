<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/commJsp.jsp"%>

<!-- BEGIN Content -->
<div class="row">
	<div class="col-lg-12">
		
			<article id="node-445302" class="node node-article left-sidebar">
				<header class="article-header">
					<!-- 기사 제목 -->
					<h1 class="page-header">
						<c:if test="${not empty article}">
							${article.title} | <small>{gategory}</small>
						</c:if>
					</h1>
	
					<!-- 작성자, 작성일, 수정일 -->
					<c:if test="${not empty article}">
						<div class="byline">
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

<!-- BEGIN Script -->
<script>
	var $searchForm = $('#searchForm');
	var $tbListTable = $('#tbListTable');

	$(document).ready(function() {
		//$tbListTable.bootstrapTable('load', randomData() );

		var options = $tbListTable.bootstrapTable('getOptions');
		options.pageNumber = 1;
		if (options.url) {
			$tbListTable.bootstrapTable('refresh');
		} else {
			var sURL = '/ajax/boards';
			console.log('request url=' + sURL);
			$tbListTable.bootstrapTable('refreshOptions', {
				//showExport: true,
				url : sURL
			});
			console.log('refreshOptions =' + sURL);
		}

	}); // init jquery

	function queryParams() {
		var params = {};

		var options = $tbListTable.bootstrapTable('getOptions');
		if ($searchForm)
			$searchForm
					.find('input[name]')
					.each(
							function() {
								var v = $(this).val();
								var name = $(this).attr('name');
								params[name] = (name.indexOf('Date') != -1) ? convertUSDateToNomalDate(v)
										: v;

								$searchForm.find('select[name]').each(
										function() {
											params[$(this).attr('name')] = $(
													this).val();
										});
							});

		var pageNo = 1;
		if (options.pageNumber != undefined) {
			pageNo = (1 + ((options.pageNumber - 1) * options.pageSize));
			console.log('Currently showing page start ' + pageNo + ' of '
					+ options.pageNumber + ' page.');
		}
		params['pageNo'] = pageNo;
		params['pageSize'] = options.pageSize;

		console.log('params->' + JSON.stringify(params));
		return params;
	}

	function responseHandler(res) {
		console.log(res);
		/* if (res.Result != '0') {
		    alert(res.Message);
		}
		return res.Data; */
		return res;
	}

	function randomData() {
		var startId = ~~(Math.random() * 100), rows = [];
		for (var i = 0; i < 10; i++) {
			rows.push({
				boardID : startId + i,
				title : 'test' + (startId + i),
				userID : 'id' + (startId + i)
			});
		}
		return rows;
	}
</script>
<!-- END Script -->







