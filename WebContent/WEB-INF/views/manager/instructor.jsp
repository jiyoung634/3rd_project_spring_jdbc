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

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<!-- 커스텀 CSS 파일 -->
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
		$("button.btnDelete").on("click", function() {
			if (confirm("해당 강사 정보를 삭제하시겠습니까?")) {
				var instructor_id = $(this).val();
				location.assign("${pageContext.request.contextPath}/manager/instructor/delete?instructor_id=" + instructor_id);
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
				<a class="navbar-brand"
					href="${pageContext.request.contextPath}/manager/main"><img
					src="${pageContext.request.contextPath}/resources/images/logo-white.png"
					alt="Logo"></a> <a class="navbar-brand hidden"
					href="${pageContext.request.contextPath}/manager/main"><img
					src="${pageContext.request.contextPath}/resources/images/logo-small.png"
					alt="Logo"></a>
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
					<li><a href="${pageContext.request.contextPath}/manager/main">
							<i class="menu-icon fa fa-home"></i>Main
					</a></li>
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-check-square-o"></i>기초 정보 관리
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa fa-table"></i><a
								href="${pageContext.request.contextPath}/manager/basicinfo/course">과정
									관리</a></li>
							<li><i class="fa fa-table"></i><a
								href="${pageContext.request.contextPath}/manager/basicinfo/subject">과목
									관리</a></li>
							<li><i class="fa fa-table"></i><a
								href="${pageContext.request.contextPath}/manager/basicinfo/class">강의실
									관리</a></li>
							<li><i class="fa fa-table"></i><a
								href="${pageContext.request.contextPath}/manager/basicinfo/book">교재
									관리</a></li>
						</ul></li>
					<li class="active"><a
						href="${pageContext.request.contextPath}/manager/instructor">
							<i class="menu-icon fa fa-users"></i>강사 계정 관리
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/manager/course"> <i
							class="menu-icon fa fa-book"></i>개설 과정 및 과목 관리
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/manager/student"> <i
							class="menu-icon fa fa-meh-o"></i>수강생 관리
					</a></li>
					<li class="menu-item-has-children dropdown"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <i
							class="menu-icon fa fa-bar-chart-o"></i>성적 조회
					</a>
						<ul class="sub-menu children dropdown-menu">
							<li><i class="fa fa-folder"></i><a
								href="${pageContext.request.contextPath}/manager/score/bycourse">과정별
									성적 조회</a></li>
							<li><i class="fa fa-folder"></i><a
								href="${pageContext.request.contextPath}/manager/score/bystudent">수강생별
									성적 조회</a></li>
						</ul></li>

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
						<h1>강사 계정 관리</h1>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="page-header float-right">
					<div class="page-title">
						<ol class="breadcrumb text-right">
							<li class="active">강사 계정 관리</li>
						</ol>
					</div>
				</div>
			</div>
		</div>

		<div class="content mt-3">
			<div class="animated fadeIn">
				<div class="row">

					<!-- ★ 본문 내용 추가 부분 ★ -->

					<div class="col-lg-12">
						<div class="card">
							<div class="card-header">
								<strong class="card-title">강사 목록</strong>
								<button type="button"
									class="btn btn-info btn-sm btn-margin-left" data-toggle="modal"
									data-target="#addInstructor">새로 등록</button>
							</div>
							<div class="card-body">

								<div class="row">
									<div class="col-sm-12">
										<table id="bootstrap-data-table"
											class="table table-striped table-bordered dataTable no-footer"
											role="grid" aria-describedby="bootstrap-data-table_info">
											<thead>
												<tr>
													<th>강사 ID</th>
													<th>강사명</th>
													<th>주민번호<br>뒷자리
													</th>
													<th>전화번호</th>
													<th>강의 가능 과목</th>
													<th>등록일</th>
													<th>강사 관리</th>
													<th>상세보기</th>
												</tr>
											</thead>
											<tbody>
												<c:set var="count" value="0" />
												<c:forEach var="i" items="${instructorList}">
													<tr>
														<td>${i.instructor_id}</td>
														<td><p class="tt instructor-profile">
															${i.name_}<span class="tt-text"><img
																src="${pageContext.request.contextPath}/resources/uploads/pictures/${i.profile_img}"
																alt="instructor-profile">${i.name_}
															</span>
															</p></td>
														<td>${i.ssn}</td>
														<td>${i.phone}</td>
														<td>${i.available_subjects}</td>
														<td><df:dateForm date="${i.instructor_reg_date}" /></td>
														<td>
															<div class="btn-group">
																<button type="button" class="btn btn-info btn-xs btnModify"
																	data-toggle="modal"
																	data-target="#modify-availabe-subject" value="${i.instructor_id}">수정</button>
																<button type="button"
																	class="btn btn-info btn-xs btnDelete" value="${i.instructor_id}" ${(i.delete_status=='Y')?"":"disabled"}>삭제</button>
															</div>
														</td>
														<td>
															<div class="btn-group">
																<a href="${pageContext.request.contextPath}/manager/instructor/courseinfo?instructor_id=${i.instructor_id}"><button class="btn btn-info btn-xs" value="${l.instructor_id}">
																		<i class="fa fa-search"></i>
																	</button></a>
															</div>
														</td>
													</tr>
													<c:set var="count" value="${count+1}" />

												</c:forEach>
												<c:if test="${count==0}">
													<tr>
														<td colspan='12'>해당하는 강사가 없습니다.</td>
													</tr>
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

			<!-- Modal -->
			<div class="modal fade" id="addInstructor" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
					<form id="instructor-insert" action="${pageContext.request.contextPath}/manager/instructor/instructorAdd" method="POST" enctype="multipart/form-data" >
						<div class="modal-header">
							<h4 class="modal-title">강사 등록</h4>
							<button type="button" class="close" data-dismiss="modal">×</button>
						</div>
						<div class="modal-body">

							<div class="form-group">
								<label for="name_" class=" form-control-label">강사명</label> <input
									type="text" id="name_" name="name_" placeholder="강사명을 입력하세요"
									class="form-control" required>
							</div>
							<div class="form-group">
								<label for="ssn" class=" form-control-label">주민번호
									뒷자리</label> <input type="text" id="ssn" name="ssn" placeholder="주민번호 뒷자리를 입력해주세요"
									class="form-control" required>
							</div>
							<div class="form-group">
								<label for="phone" class=" form-control-label">전화번호</label> <input
									type="text" id="phone" name="phone" placeholder="전화번호를 입력해주세요"
									class="form-control" required>
							</div>
							<div class="form-group" id="bs-add-checkboxes">
								<c:set var="count" value="0" />
								<c:forEach var="b" items="${bsList}">
									<input type="checkbox" name="bs_id" value="${b.bs_id}">${b.bs_name}&nbsp;
								<c:set var="count" value="${count+1}" />
								</c:forEach>
							</div>
							<div class="form-group">
								<label for="initial_reg_date">최초 등록일</label> <input type="date"
									class="form-control" id="initial_reg_date" name="initial_reg_date"  required>
							</div>
									
							<div class="form-group">
								<label for="profile_img" class=" form-control-label">강사 사진</label> <input
									type="file" class="form-control" id="file" name="file"> <span class="help-block">(png, jpg파일.
									최대 1MB)</span>
							</div>

								<div id="add-error"></div>

						</div>
						<div class="modal-footer">						
							<button type="button" class="btn btn-default btnAddInstructor">확인</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

						</div>
						</form>
					</div>

				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="modify-availabe-subject" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h6 class="modal-title subject-modify-title"></h6>
							<button type="button" class="close" data-dismiss="modal">×</button>
						</div>
						<form id="instructor-update" action="${pageContext.request.contextPath}/manager/instructor/update" method="POST" >
							<div class="modal-body">
								<div class="form-group" id="bs-list">
									<label for="available-subjects" class=" form-control-label">강의 가능
										과목</label><br>
									<input type="hidden" name="instructor_id" id="instructor_id">
									<c:set var="count" value="0" />
									<c:forEach var="b" items="${bsList}">
										<input type="checkbox" name="bs_id" value="${b.bs_id}">${b.bs_name}&nbsp;
									<c:set var="count" value="${count+1}" />
	
									</c:forEach>
									<c:if test="${count==0}">
										<p style="text-align:center;">해당하는 과목이 없습니다.</p>
									</c:if>
								</div>
	
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-default btnChangeCourse">확인</button>
								<button type="button" class="btn btn-default btnCloseModal" data-dismiss="modal">취소</button>
							</div>
						</form>
					</div>

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
	<script>
	$(document).ready(function(){
		
		$("button.btnModify").on("click", function() {
		
			var instructor_id = $(this).val();
			var name_ = $(this).parent().parent().siblings().eq(1).text();
			var ssn = $(this).parent().parent().siblings().eq(2).text();
			
			// 강의가능과목 모달창 타이틀
			$("h6.subject-modify-title").text("강사 "+instructor_id+ "(" + name_ + "/" + ssn +")의 강의 가능 과목 수정");

			// 이전에 등록되어있던 강의 가능 과목에 체크
			var bsurl = "${pageContext.request.contextPath}/manager/instructor/bslist?instructor_id=" + instructor_id;
				
	        $.ajax({
	        	url: bsurl,
	        	async : false,
	        	success:function(responseData){

	        		var selectBsIds = $("div#bs-list").find("input[type='checkbox']");
	        		for (var b=0; b<responseData.length; b++){		
	        			for (var a=0; a<selectBsIds.length; a++) {
	        				if (responseData[b] == selectBsIds.eq(a).val()) {
	        					selectBsIds.eq(a).prop("checked", true);
	        				}
	        			}
	        		}
	        	}	
	        }); 
	        
	        // 업데이트용 instructor_id 전달
	        //<input type="hidden" name="instructor_id" id="instructor_id">        
	        var instructor_id_update = $(this).val();
	        $("input[type='hidden']#instructor_id").val(instructor_id_update);
	       
		});
		
		$("button.btnCloseModal").on("click", function() {
    		var refreshBsIds = $("div#bs-list").children("input[type='checkbox']").prop("checked", false);
        });
		
		// 마우스 오버시 툴팁 보이게
		$("p.tt.instructor-profile").on("mouseover", function() {
			$(this).find("span.tt-text").css("visibility", "visible");
		}).on("mouseout", function() {
			$(this).find("span.tt-text").css("visibility", "hidden");
		});
		
		$("button.btnAddInstructor").on("click", function(){
			
			// 강사 등록 시, 강의가능과목 체크박스 갯수가 1개 이상이어야 함
			var checkedbs_id = $("div#bs-add-checkboxes input[type='checkbox']:checked").length;
			if ($("input#name_").val() == "") {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 강사명을 입력해 주세요. <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else if ($("input#ssn").val() == "") {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 주민번호를 입력해 주세요. <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else if ($("input#phone").val() == "") {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 전화번호를 입력해 주세요. <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else if (checkedbs_id == 0) {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 강의가능과목은 하나 이상 선택되어야 합니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else if ($("input#initial_reg_date").val() == "") {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 최초 등록일을 입력해 주세요. <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else {
				$("form#instructor-insert").submit();
			}
			
		});	
		
	});

	</script>
</body>
</html>
