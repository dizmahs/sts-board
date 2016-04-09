<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/jsp/commJsp.jsp"%>
	
<!-- BEGIN Title -->	
<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">게시판</h1>
    </div>
    <!-- /.col-lg-12 -->
</div> 
<!-- END Title -->

<!-- BEGIN Content -->
<div class="row">
	<div class="col-lg-12">
		<div class="dataTable_wrapper table-responsive">
			<table id="tbListTable"
				data-classes="table table-no-bordered" 
				data-toggle="table"
                data-side-pagination="server"
                data-pagination="true"
                data-page-size="5"
                data-page-list="[10,25,50,100,200,500]"
                data-query-params="queryParams"
                data-response-handler="responseHandler" >
				<thead>
					<tr>
						<th data-field="boardID">&nbsp;&nbsp;</th>
						<th data-field="title" data-align="center" 
							data-formatter="articleLinkFormatter" >제 목</th>
						<th data-field="userID" data-align="center" >작성자</th>
						<th data-field="createdOn" data-align="center" >작성일</th>
						<th data-field="hitCount" data-align="center" >조회수</th>
					</tr>
				</thead>
			</table>
		</div>
	</div> 
	<!-- /.col-lg-12 -->	
</div>
<!-- END Content -->

<!-- BEGIN Script -->
<script>
	var $searchForm = $('#searchForm');
	var $tbListTable = $('#tbListTable');

	$(document).ready(function () {
		//$tbListTable.bootstrapTable('load', randomData() );
		
        var options = $tbListTable.bootstrapTable('getOptions');
        options.pageNumber = 1;
        if (options.url) {
            $tbListTable.bootstrapTable('refresh');
        }
        else {
            var sURL = '/ajax/boards';
            console.log('request url=' + sURL);
            $tbListTable.bootstrapTable('refreshOptions', {
                //showExport: true,
                url: sURL
            });
            console.log('refreshOptions =' + sURL);
        }
		
	}); // init jquery
	
	function queryParams() {
        var params = {};
        
        var options = $tbListTable.bootstrapTable('getOptions');
        if( $searchForm )
        	$searchForm .find('input[name]').each(function () {
            var v = $(this).val();
            var name = $(this).attr('name');
            params[name] = (name.indexOf('Date') != -1) ? convertUSDateToNomalDate(v) : v;
            
            $searchForm .find('select[name]').each(function () {
                params[$(this).attr('name')] = $(this).val();
            });
        });
        
        var pageNo = 1;
        if (options.pageNumber != undefined) {
            pageNo = (1 + ((options.pageNumber - 1) * options.pageSize));
            console.log('Currently showing page start ' + pageNo + ' of ' + options.pageNumber + ' page.');
        }
        params['pageNo'] = pageNo;
        params['pageSize'] = options.pageSize;
        
        console.log('params->' + JSON.stringify(params));
        return params;
    }

    function responseHandler(res) {
    	console.log( res );
        if (res.result != '0') {
            alert(res.Message);
        }
        return res.data;
    }
    
    /*
    * value: the field value. 
    * row: the row record data.
    * index: the row index.
    */
    function articleLinkFormatter( value, row, index ){
    	return '<a href="/bbs/board/'+row.boardID+'.html">' + value + '</a>';
    }
	

</script>
<!-- END Script -->







