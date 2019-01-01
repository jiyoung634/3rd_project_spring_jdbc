<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="df" tagdir="/WEB-INF/tags" %>
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

<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/images/favicon.ico">

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<!-- 커스텀 CSS 파일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/custom-style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/normalize.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/themify-icons.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/flag-icon.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/cs-skin-elastic.css">
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/css/lib/datatable/dataTables.bootstrap.min.css"> --%>
<!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/bootstrap-select.less"> -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/scss/style.css">
<%-- <link href="${pageContext.request.contextPath}/resources/assets/css/lib/vector-map/jqvmap.min.css" rel="stylesheet"> --%>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>


<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->
<script>
	$(document).ready(function() {

		$("button.btnDelete").on("click", function(){
			if(confirm("해당 개설 과목을 삭제하시겠습니까?")){
				/* var cj = JSON.parse($(this).val());
				var os_id = cj.os_id;
				var oc_id = cj.oc_id; */
				var os_id = $(this).val();
				var oc_id = "${oc_id}";
				location.assign("${pageContext.request.contextPath}/manager/course/subject/subjectDelete?os_id=" + os_id +"&oc_id="+oc_id);
			}
		});

		$("select#bs_id").on("change",function(){

			var bs_id = $(this).find("option:selected").val();
			var insurl = "${pageContext.request.contextPath}/manager/getinstructor?bs_id=" + bs_id;

			$.ajax({
				url : insurl,
				success : function(data) {
					var txt="<option value='0'>강사를 선택하세요</option>";
					// console.log(data);
					// var instructors = JSON.parse(data);
					for (var a=0; a<data.length; a++){
						// console.log(data[a].instructor_id);
						txt += "<option value='"+ data[a].instructor_id +"'>"+ data[a].name_ +"</option>";
					}
					$("select#instructor_id").html(txt);
				}
			});

		});

		$("button.btnOsAdd").on("click", function() {
			// 개설 과목 입력의 옵션 선택 여부를 확인, 예외처리
			if ($("select[name='bs_id'] option:selected").val() == 0) {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 개설할 과목이 선택되지 않았습니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else if ($("input#os_start_date").val() == "") {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 개설 과목 시작일이 입력되지 않았습니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else if ($("input#os_end_date").val() == "") {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 개설 과목 종료일이 입력되지 않았습니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else if ($("select[name='textbook_id'] option:selected").val() == 0) {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 교재가 선택되지 않았습니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else if ($("select[name='instructor_id'] option:selected").val() == 0) {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 강사가 선택되지 않았습니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else {
				$("form#os-insert").submit();
			}
		});

	});
</script>
</head>

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
				<a class="navbar-brand" href="${pageContext.request.contextPath}/manager/main"><img
					src="${pageContext.request.contextPath}/resources/images/logo-white.png" alt="Logo"></a> <a
					class="navbar-brand hidden" href="${pageContext.request.contextPath}/manager/main"><img
					src="${pageContext.request.contextPath}/resources/images/logo-small.png" alt="Logo"></a>
			</div>

			<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="user-profile"><img src="${pageContext.request.contextPath}/resources/uploads/pictures/${sessionScope.managerLoginInfo.profile_img}"
						alt="manager.jpg">
						<h5 class="text-sm-center mt-2 mb-1 profile-font">관리자</h5>
						<p class="text-sm-center mb-1 profile-font2">${sessionScope.managerLoginInfo.name_}</p>
						<button type="button" class="btn btn-success btn-sm btn-logout" onclick="location.href='${pageContext.request.contextPath}/logout'">Logout</button>
					</li>
					<li><p class="menu-title">관리자메뉴</p></li>
					<!-- /.menu-title -->
					<li><a href="${pageContext.request.contextPath}/manager/main"> <i class="menu-icon fa fa-home"></i>Main
					</a></li>
					<li class="menu-item-has-children dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-check-square-o"></i>기초 정보 관리</a>
                        <ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-table"></i><a href="${pageContext.request.contextPath}/manager/basicinfo/course">과정 관리</a></li>
                            <li><i class="fa fa-table"></i><a href="${pageContext.request.contextPath}/manager/basicinfo/subject">과목 관리</a></li>
                            <li><i class="fa fa-table"></i><a href="${pageContext.request.contextPath}/manager/basicinfo/class">강의실 관리</a></li>
                            <li><i class="fa fa-table"></i><a href="${pageContext.request.contextPath}/manager/basicinfo/book">교재 관리</a></li>
                        </ul>
                    </li>
					<li><a href="${pageContext.request.contextPath}/manager/instructor"> <i
							class="menu-icon fa fa-users"></i>강사 계정 관리
					</a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/manager/course"> <i
							class="menu-icon fa fa-book"></i>개설 과정 및 과목 관리
					</a></li>
					<li><a href="${pageContext.request.contextPath}/manager/student"> <i
							class="menu-icon fa fa-meh-o"></i>수강생 관리
					</a></li>
					<li class="menu-item-has-children dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<i class="menu-icon fa fa-bar-chart-o"></i>성적 조회
					</a>
						<ul class="sub-menu children dropdown-menu">
                            <li><i class="fa fa-folder"></i><a href="${pageContext.request.contextPath}/manager/score/bycourse">과정별 성적 조회</a></li>
                            <li><i class="fa fa-folder"></i><a href="${pageContext.request.contextPath}/manager/score/bystudent">수강생별 성적 조회</a></li>
                        </ul>
					</li>

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
							class="user-avatar rounded-circle" src="${pageContext.request.contextPath}/resources/uploads/pictures/${sessionScope.managerLoginInfo.profile_img}"
							alt="User Avatar">
						</a>

						<div class="user-menu dropdown-menu">
							<a class="nav-link" href="${pageContext.request.contextPath}/manager/main"><i class="fa fa- user"></i>Main</a>

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
						<h1>개설 과정 및 과목 관리</h1>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="page-header float-right">
					<div class="page-title">
						<ol class="breadcrumb text-right">
							<li><a href="${pageContext.request.contextPath}/manager/course">개설 과정 및 과목 관리</a></li>
							<li><a href="${pageContext.request.contextPath}/manager/course">개설 과정 목록</a></li>
							<li class="active">개설 과목 목록</li>
						</ol>
					</div>
				</div>
			</div>
		</div>

		<div class="content mt-3">
			<div class="animated fadeIn">
				<div class="row">

					<div class="col-lg-12">
						<div class="card">
							<div class="card-header">
								<strong class="card-title">${ocInfo.oc_title}(<c:set var="oc_start_date" value="${ocInfo.oc_start_date}"/><c:if test="${oc_start_date!=null}"><df:dateForm date="${ocInfo.oc_start_date}"/></c:if> ~ <c:set var="oc_end_date" value="${ocInfo.oc_end_date}"/><c:if test="${oc_end_date!=null}"><df:dateForm date="${ocInfo.oc_end_date}"/></c:if>)의 개설 과목 목록</strong>
											<button type="button" class="btn btn-info btn-sm btn-margin-left"
												data-toggle="modal" data-target="#osAddModal" ${(ocInfo.delete_status=='Y')?"":"disabled"}>새로 등록</button>
							</div>
							<div class="card-body">

									<div class="row">
										<div class="col-sm-12">
											<table id="bootstrap-data-table"
												class="table table-striped table-bordered dataTable no-footer"
												role="grid" aria-describedby="bootstrap-data-table_info">
												<thead>
													<tr>
														<th>개설 과목 ID</th>
														<th>개설 과목명</th>
														<th>개설 과목 기간</th>
														<th>교재명</th>
														<th>강사명</th>
														<th>개설 과목 관리</th>
													</tr>
												</thead>
												<tbody>
												<c:set var="count" value="0" />
													<c:forEach var="l" items="${oc_osSearch}">
													<tr>
														<td>${l.os_id}</td>
														<td>${l.os_name}</td>
														<td><df:dateForm date="${l.os_start_date}"/>~<br><df:dateForm date="${l.os_end_date}"/></td>
														<td><p class="tt interpark-book"> ${l.textbook_title}
																<span class="tt-text">
																	<img alt="${l.isbn}">${l.textbook_title}<br>${l.publisher}<br>ISBN: ${l.isbn}
																</span>
															</p>
														</td>
														<td><p class="tt instructor-profile">
															${l.name_}<span class="tt-text"> <img
																src="${pageContext.request.contextPath}/resources/uploads/pictures/${l.profile_img}" alt="instructor" >강의가능과목<br>${l.available_subjects}
															</span></p>
														</td>
														<td>
															<button type="button"
																class="btn btn-info btn-xs btnDelete" value="${l.os_id}" ${(l.delete_status=='Y')?"":"disabled"}>삭제</button> <%-- '{"os_id":"${l.os_id}", "oc_id":"${oc_id}"}' --%>

														</td>
													</tr>
													<c:set var="count" value="${count+1}" />
													</c:forEach>
													<c:if test="${count==0}">
														<tr><td colspan='9'>해당하는 과목이 없습니다</td></tr>
													</c:if>
												</tbody>
											</table>

									</div>
								</div>


							</div>
						</div>
					</div>
				</div>
				<!-- .animated -->
			</div>
			<!-- .content -->

		</div>
	</div>

			<!-- Modal -->
			<div class="modal fade" id="osAddModal" role="dialog">
				<div class="modal-dialog">
					<!-- Modal content-->
					<div class="modal-content">
					<form id="os-insert" action="${pageContext.request.contextPath}/manager/course/subject/subjectInsert" method="POST">
						<div class="modal-header">
							<h4 class="modal-title">개설 과목 등록</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="os_name">개설할 과목</label>
								<select name="bs_id"
									id="bs_id" class="form-control">
									<option value="0">과목을 선택하세요</option>
									<c:forEach var="s" items="${bsList}">
										<option value="${s.bs_id}">${s.bs_name}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="os_start_date">개설 과목 시작일</label> <input type="date"
									class="form-control" id="os_start_date" name="os_start_date" required>
							</div>
							<div class="form-group">
								<label for="os_end_date">개설 과목 종료일</label> <input type="date"
									class="form-control" id="os_end_date" name="os_end_date" required>
							</div>
							<div class="form-group">
									<label
									for="textbook_title">교재</label>
								<select name="textbook_id" id="textbook_id"
									class="form-control">
									<option value="0">교재를 선택하세요</option>
									<c:forEach var="t" items="${textbookList}">
										<option value="${t.textbook_id}">${t.textbook_title}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="instructor_id">강사</label> <select name="instructor_id"
									id="instructor_id" class="form-control">
									<option value='0'>강사를 선택하세요</option>
									<%-- <c:forEach var="i" items="${instructorList}">
										<option value="${i.instructor_id}">${i.name_}</option>
									</c:forEach> --%>
								</select>
							</div>
								<input type="hidden" class="course-subject" name="oc_id" id="oc_id" value="${oc_id}"/>

							<div id="add-error"></div>
							</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default btnOsAdd">확인</button>
							<button type="button" class="btn btn-default"
								data-dismiss="modal">취소</button>

						</div>
						</form>
					</div>
				</div>
			</div>

	<!-- /#right-panel -->

	<!-- Right Panel -->

	<script src="${pageContext.request.contextPath}/resources/assets/js/vendor/jquery-2.1.4.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/plugins.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>
	<!--
	<script src="${pageContext.request.contextPath}/resources/assets/js/custom.js"></script>

	<script src="${pageContext.request.contextPath}/resources/assets/js/lib/chart-js/Chart.bundle.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/dashboard.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/widgets.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/jquery.vmap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/jquery.vmap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/jquery.vmap.sampledata.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/country/jquery.vmap.world.js"></script>
	-->

	<script>
		$( document ).ready(function() {

			var coverurl;
			var totalResults;

			$("p.tt.interpark-book").on("mouseover", function(){

				// alt에 걸린 ISBN 찾기
				var alt = $(this).find("img").attr("alt");

				// interparkAPI를 AJAX로 호출
				var url_ = "${pageContext.request.contextPath}/manager/interparkapi";
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
