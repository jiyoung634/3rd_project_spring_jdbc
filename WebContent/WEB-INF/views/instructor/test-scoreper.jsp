<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="df" tagdir="/WEB-INF/tags"%>
<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="">
<!--<![endif]-->
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>SIST 교육센터 - 성적관리시스템</title>
<meta name="description" content="Sufee Admin - HTML5 Admin Template">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="apple-touch-icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.ico">
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/resources/images/favicon.ico">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/custom-style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/normalize.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/flag-icon.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/cs-skin-elastic.css">
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/lib/datatable/dataTables.bootstrap.min.css"> --%>
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-select.less"> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/scss/style.css">
<%-- <link
	href="${pageContext.request.contextPath}/resources/assets/css/lib/vector-map/jqvmap.min.css"
	rel="stylesheet"> --%>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>


<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
<script>
	$(document).ready(function() {

		//삭제
		$("button.btnDeleteScoreper").on("click", function() {
			if (confirm("해당 배점 정보를 삭제하시겠습니까?")) {
				var os_id = $(this).val();
				location.assign("${pageContext.request.contextPath}/instructor/percentageDelete?os_id=" + os_id);
			}
		});

		// 배점 입력
		$("button.btn-percentageAdd").click(function() {
		 	var os_id = $(this).val();
		 	$("form#percentage-insert-form > div.modal-body > input[type='hidden']").val(os_id);

		 	//모달창 서브 제목
		 	var oc_title = $(this).parents("tr").children().eq(2).text();
		 	var oc_date = $(this).parents("tr").children().eq(3).text();
			var os_name = $(this).parents("tr").children().eq(0).text();
		 	$("h6#score-percentage-setting-title").html(oc_title+"("+oc_date+")의 "+ "<br>" + os_name + " 배점 입력");

		});

	});
</script>
</head>
<style>
</style>
<body>


	<!-- Left Panel -->

	<aside id="left-panel" class="left-panel">
		<nav class="navbar navbar-expand-sm navbar-default">

			<div class="navbar-header">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#main-menu" aria-controls="main-menu"
					aria-expanded="false" aria-label="Toggle navigation">
					<i class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/instructor/main"><img
					src="${pageContext.request.contextPath}/resources/images/logo-white.png"
					alt="Logo"></a> <a class="navbar-brand hidden"
					href="${pageContext.request.contextPath}/instructor/main"><img
					src="${pageContext.request.contextPath}/resources/images/logo-small.png"
					alt="Logo"></a>
			</div>

			<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="user-profile"><img src="${pageContext.request.contextPath}/resources/uploads/pictures/${sessionScope.instructorLoginInfo.profile_img}"
						alt="instructor.png">
						<h5 class="text-sm-center mt-2 mb-1 profile-font">강사</h5>
						<p class="text-sm-center mb-1 profile-font2">${sessionScope.instructorLoginInfo.name_}</p>
						<a href="${pageContext.request.contextPath}/logout">
						<button type="button" class="btn btn-success btn-sm btn-logout">Logout</button></a>
					</li>
					<li><p class="menu-title">강사메뉴</p></li>
					<!-- /.menu-title -->
					<li><a
						href="${pageContext.request.contextPath}/instructor/main"> <i
							class="menu-icon fa fa-home"></i>Main
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/instructor/schedule">
							<i class="menu-icon fa fa-table"></i>강의스케쥴 조회
					</a></li>


					<li class="menu-item-has-children dropdown show active"><a
						href="${pageContext.request.contextPath}/instructor/test/scoreper"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true"> <i
							class="menu-icon fa fa-laptop"></i>배점 및 시험 관리
					</a>
						<ul class="sub-menu children dropdown-menu show">
							<li class="active"><i class="fa fa-file"></i><a
								href="${pageContext.request.contextPath}/instructor/test/scoreper">배점
									관리</a></li>
							<li><i class="fa fa-file"></i><a
								href="${pageContext.request.contextPath}/instructor/test/info">시험
									날짜 및 파일 관리</a></li>
						</ul></li>
					<li><a
						href="${pageContext.request.contextPath}/instructor/score"> <i
							class="menu-icon fa fa-bar-chart-o"></i>성적 관리
					</a></li>

				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</nav>
	</aside>
	<!-- /#left-panel -->

	<!-- Left Panel -->

	<!-- Right Panel -->

	<div id="right-panel" class="right-panel">

		<!-- Header-->
		<header id="header" class="header">

			<div class="header-menu">

				<div class="col-sm-7">
					<a id="menuToggle" class="menutoggle pull-left"><i
						class="fa fa fa-tasks"></i></a>
					<div class="header-left"></div>
				</div>


				<!-- Body-->
				<div class="col-sm-5">
					<div class="user-area dropdown float-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false"> <img
							class="user-avatar rounded-circle" src="${pageContext.request.contextPath}/resources/uploads/pictures/${sessionScope.instructorLoginInfo.profile_img}"
							alt="User Avatar">
						</a>

						<div class="user-menu dropdown-menu">
							<a class="nav-link" href="${pageContext.request.contextPath}/instructor/main"><i class="fa fa- user"></i>Main</a>

							<a class="nav-link" href="${pageContext.request.contextPath}/logout"><i class="fa fa-power -off"></i>Logout</a>
						</div>
					</div>

					<div class="language-select dropdown" id="language-select">
						<a class="dropdown-toggle" href="#" data-toggle="dropdown"
							id="language" aria-haspopup="true" aria-expanded="true"> <i
							class="flag-icon flag-icon-kr"></i>
						</a>
						<div class="dropdown-menu" aria-labelledby="language">
							<div class="dropdown-item">
								<span class="flag-icon flag-icon-us"></span>
							</div>
						</div>
					</div>

				</div>
			</div>

		</header>
		<!-- /header -->
		<!-- Header-->

		<div class="breadcrumbs">
			<div class="col-sm-4">
				<div class="page-header float-left">
					<div class="page-title">
						<h1>배점 관리</h1>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="page-header float-right">
					<div class="page-title">
						<ol class="breadcrumb text-right">
							<li><a href="#">배점 및 시험 관리</a></li>
							<li class="active">배점 관리</li>
						</ol>
					</div>
				</div>
			</div>
		</div>

		<div class="content mt-3">
			<div class="animated fadeIn">
				<div class="row">

					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<strong class="card-title">개설된 과목 목록</strong>
							</div>
							<div class="card-body">
								<table id="" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>개설<br>과목명
											</th>
											<th>개설과목<br>기간
											</th>
											<th>개설<br>과정명
											</th>
											<th>개설과정<br>기간
											</th>
											<th>강의실</th>
											<th>교재명</th>
											<th>출결 배점</th>
											<th>필기 배점</th>
											<th>실기 배점</th>
											<th>수강생<br>등록인원
											</th>
											<th>배점 관리</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${percentage_addible_os_count>0}">
										<c:forEach var="l" items="${percentage_addible_osList}">
											<tr>
												<td>${l.os_name}</td>
												<td><df:dateForm date="${l.os_start_date}" />~<br>
												<df:dateForm date="${l.os_end_date}" /></td>
												<td>${l.oc_title}</td>
												<td><df:dateForm date="${l.oc_start_date}" />~<br>
												<df:dateForm date="${l.oc_end_date}" /></td>
												<td>${l.classroom_name}</td>
												<td><p class="tt interpark-book"> ${l.textbook_title}
														<span class="tt-text">
															<img alt="${l.isbn}">${l.textbook_title}<br>${l.publisher}<br>ISBN: ${l.isbn}
														</span>
													</p>
												</td>
												<td>${l.attendance_percentage}</td>
												<td>${l.writing_percentage}</td>
												<td>${l.practice_percentage}</td>
												<td>${l.registerred_number}</td>
												<td><a href="#"><button type="button" class="btn btn-info btn-sm btn-percentageAdd" data-toggle="modal" data-target="#score-percentage-setting" value="${l.os_id}" ${(l.attendance_percentage==0)?"":"disabled"}>입력</button></a>
													<a href="#"><button type="button" class="btn btn-info btn-sm btnDeleteScoreper" value="${l.os_id}" ${(l.delete_status=='Y'&&l.attendance_percentage>=20)?"":"disabled"}>삭제</button></a></td>
											</tr>
										</c:forEach>
										</c:if>
										<c:if test="${percentage_addible_os_count==0}">
											<tr><td colspan="11">배점 관리 가능한 과목이 없습니다</td></tr>
										</c:if>
									</tbody>
								</table>
							</div>
						</div>
					</div>



				</div>
			</div>
			<!-- .animated -->
		</div>
		<!-- .content -->

		<!-- Modal -->
		<div class="modal fade" id="score-percentage-setting" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<h6 class="modal-title" id="score-percentage-setting-title">

						</h6>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<form id="percentage-insert-form" >
						<div class="modal-body">
							<input type="hidden" class="test-scoreper" name="os_id" id="os_id" value=""/>
							<div class="form-group">
								<label for="attendance_percentage" class=" form-control-label">출결
									배점</label> <input type="number" id="attendance_percentage"
									name="attendance_percentage" placeholder="배점을 입력하세요(min 20)"
									class="form-control" required>
							</div>
							<div class="form-group">
								<label for="writing_percentage" class=" form-control-label">필기
									배점</label> <input type="number" id="writing_percentage"
									name="writing_percentage" placeholder="배점을 입력하세요"
									class="form-control" required>
							</div>
							<div class="form-group">
								<label for="practice_percentage" class=" form-control-label">실기
									배점</label> <input type="number" id="practice_percentage"
									name="practice_percentage" placeholder="배점을 입력하세요"
									class="form-control" required>
							</div>

							<div id="not-100percent"></div>

						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default test-scoreper btn-total-percentage" form="percentage-insert-form">확인</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

						</div>
					</form>
				</div>

			</div>
		</div>

	</div>
	<!-- /#right-panel -->

	<!-- Right Panel -->

	<script
		src="${pageContext.request.contextPath}/resources/assets/js/vendor/jquery-2.1.4.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/plugins.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>
	<!--
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/custom.js"></script>

	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/chart-js/Chart.bundle.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/dashboard.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/widgets.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/jquery.vmap.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/jquery.vmap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/jquery.vmap.sampledata.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/country/jquery.vmap.world.js"></script>
	-->

	<script type="text/javascript">
		$(document).ready(function() {

			$("button.btn-total-percentage").click(function() {

				if (($("input#attendance_percentage").val() == "") || ($("input#writing_percentage").val() == "") || ($("input#practice_percentage").val() == "")) {

        	  		$("div#not-100percent").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 모든 배점은 입력되어야 합니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");

        	  	} else {

				var ap = parseInt($("input#attendance_percentage").val(), 10);
				var wp = parseInt($("input#writing_percentage").val(), 10);
				var pp = parseInt($("input#practice_percentage").val(), 10);

				var tot = parseInt(ap) + parseInt(wp) + parseInt(pp);

				if (ap < 20) {
						// 출석 점수 20점 이하일 경우
						$("div#not-100percent").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 출석 배점은 20점 이상이어야 합니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
				} else if (tot != 100) {
						// 배점 총 합 100이 아닐 경우
						$("div#not-100percent").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 배점의 총합은 100점이어야 합니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
					} else {

						$("form#percentage-insert-form").attr("action", "${pageContext.request.contextPath}/instructor/percentageInsert");
						$("form#percentage-insert-form").attr("method", "POST");
						$("form#percentage-insert-form").submit();

					}
        	  	}

			});


			// 인터파크 api 툴팁
			var coverurl;
			var totalResults;

			$("p.tt.interpark-book").on("mouseover", function(){

				// alt에 걸린 ISBN 찾기
				var alt = $(this).find("img").attr("alt");

				// interparkAPI를 AJAX로 호출
				var url_ = "${pageContext.request.contextPath}/instructor/interparkapi";
		        $.ajax({
		        	url: url_,
		        	data: { isbn : alt },
		        	async : false,
		        	success:function(responseData){
		        		coverurl = $(responseData).find("coverSmallUrl").text();
		        		totalResults = $(responseData).find("totalResults").text();
		        		console.log(coverurl);
		        	}
		        });

		        if (totalResults == 1) {
		        	$(this).find("img").attr("src", coverurl);
		        } else {
		        	$(this).find("img").attr("src", "${pageContext.request.contextPath}/resources/images/no_result.png");
		        }
		        $(this).find("span.tt-text").css("visibility", "visible");

			}).on("mouseout", function(){
				$(this).find("span.tt-text").css("visibility", "hidden");
			});

			// 마우스 오버시 툴팁 보이게
			$("p.tt.instructor-profile").on("mouseover", function(){
				$(this).find("span.tt-text").css("visibility", "visible");
			}).on("mouseout", function(){
				$(this).find("span.tt-text").css("visibility", "hidden");
			});

		});
	</script>

</body>
</html>
