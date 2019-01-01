<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="df" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
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
<!-- <link rel="stylesheet" href="assets/css/bootstrap-select.less"> -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/assets/scss/style.css">
<%-- <link
	href="${pageContext.request.contextPath}/resources/assets/css/lib/vector-map/jqvmap.min.css"
	rel="stylesheet"> --%>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800'
	rel='stylesheet' type='text/css'>


<!-- <script type="text/javascript" src="https://cdn.jsdelivr.net/html5shiv/3.7.3/html5shiv.min.js"></script> -->


</head>
<script>
	$(document).ready(function() {
		
		// 수강생 삭제
		$("button.btnDeleteStudent").on("click", function() {
			if (confirm("해당 수강생을 삭제하시겠습니까?")) {
				var student_id= $(this).val();
				location.assign("${pageContext.request.contextPath}/manager/student/studentDelete?student_id="+student_id);
			}
		});
		
		
		// 과정 등록(Ajax 요청을 통해 Modal에 수강생 정보 전달 후 수강생의 수강 이력 불러옴)
		$("button.btnCourseAdd").on("click", function(){
			// Header부분에 수강생id/이름/주민번호 불러오기
			var student_id=$(this).val();
			var name_=$(this).parents("tr").children("td").eq(1).find("span").text();
			var ssn = $(this).parents("tr").children("td").eq(2).text();
			$("div#student-course-register h6.modal-title").text("수강생 "+student_id+"("+name_+"/"+ssn+")) 수강할 과정 선택");
			
			// 수강생id를 hidden에 추가
			$("div#student-course-register input[type='hidden']").val(student_id);
			
			// 수강 이력 동적 생성 테이블
			$.get("${pageContext.request.contextPath}/manager/getCourseHistory?student_id="+student_id, function(data, status){	//data - 리턴값, status - 상태메시지
				var txt="<tr><th>개설과정id</th><th>개설과정명</th><th>개설 과정 기간</th><th>강의실명</th><th>수료 여부</th><th>수료 예정<br>(중도탈락)날짜</th></tr>";
				if(data.length>0){
					for(i in data){
												
						txt+="<tr>";
						txt+="<td>"+data[i].oc_id +"</td>";
						txt+="<td>"+data[i].oc_title+"</td>";
 						txt+="<td>"+data[i].oc_start_date.substr(0,10)+"<br>~"
								+""+data[i].oc_end_date.substr(0,10)+"</td>";  
						txt+="<td>"+data[i].classroom_name+"</td>";
						txt+="<td>"+data[i].learning_status+"</td>";
						txt+="<td>"+data[i].course_end_date.substr(0,10)+"</td>";
						txt+="</tr>"; 
					}
				} else{
					txt+="<tr><td colspan=\"6\">수강 이력이 없습니다.</td></tr>";
				}
				$("div#student-course-register div#courseHistory table").html(txt);
			});			
		});
	
		// 검색 및 페이징 처리
		// 검색
		var key = "${key}";
		var value = "${value}";
        $("select#key > option[value='"+key+"']").attr("selected","selected");
        $("input#value").val(value);
        
        // 페이징 처리
		if ("${key}" != "ALL") {
		 	$("button.btnPrevious").attr("disabled", "disabled");
			$("button.btnNext").attr("disabled", "disabled")
		}
        
		if('${previous}'==0){
		 	$("button.btnPrevious").attr("disabled", "disabled");
		}

		// next 버튼 수정 해야함 ★
		if('${next}' > Math.ceil('${totalCount}'/'${limit_count}')){
		    $("button.btnNext").attr("disabled", "disabled")
		}

		$("button.btnPrevious").on("click", function() {
		 	location.assign("${pageContext.request.contextPath}/manager/student?pageNum=${previous}#bottom");
		});
		
		$("button.btnNext").on("click", function() {
		  	location.assign("${pageContext.request.contextPath}/manager/student?pageNum=${next}#bottom");
		});
		
		
		$("button.btnStudentReg").on("click", function() {
			// 수강생 과정 등록의 옵션 선택 여부를 확인, 예외처리
			if ($("select[name='oc_id'] option:selected").val() == 0) {
				$("div#add-error").append("<div class=\"alert  alert-danger alert-dismissible fade show\" role=\"alert\"><span class=\"badge badge-pill badge-danger\">Fail</span> 개설 과정이 선택되지 않았습니다.<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span></button></div>");
			} else {
				$("form#studentOcAdd").submit();
			}
		});
		
	});
</script>
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
					<li><a
						href="${pageContext.request.contextPath}/manager/main"> <i
							class="menu-icon fa fa-home"></i>Main
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
					<li><a
						href="${pageContext.request.contextPath}/manager/instructor">
							<i class="menu-icon fa fa-users"></i>강사 계정 관리
					</a></li>
					<li><a
						href="${pageContext.request.contextPath}/manager/course"> <i
							class="menu-icon fa fa-book"></i>개설 과정 및 과목 관리
					</a></li>
					<li class="active"><a
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
						<h1>수강생 검색</h1>
					</div>
				</div>
			</div>
			<div class="col-sm-8">
				<div class="page-header float-right">
					<div class="page-title">
						<ol class="breadcrumb text-right">
							<li class="active">수강생 관리</li>
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
								<strong class="card-title">수강생 목록</strong>
								<button type="button"
									class="btn btn-info btn-sm btn-margin-left" data-toggle="modal"
									data-target="#student-register">새로 등록</button>
							</div>
							<div class="card-body">

								<table id="bootstrap-data-table"
									class="table table-striped table-bordered dataTable no-footer"
									role="grid" aria-describedby="bootstrap-data-table_info">
									<thead>
										<tr>
											<th>수강생 ID</th>
											<th>수강생<br>이름
											</th>
											<th>주민번호<br>뒷자리
											</th>
											<th>전화번호</th>
											<th>최초<br>등록일
											</th>
											<th>수강<br>횟수
											</th>
											<th>수강생<br>삭제
											</th>
											<th>과정 등록</th>
											<th>중도탈락<br>처리
											</th>
											<th>수강 취소</th>
											<th>상세<br>보기
											</th>
										</tr>
									</thead>
									<tbody>
										<c:if test="${studentCount>0}">
											<c:forEach var="s" items="${studentList}">
												<tr>
													<td>${s.student_id}</td>
													<td><p class="tt student-profile">
															${s.name_}<span class="tt-text"><img
																src="${pageContext.request.contextPath}/resources/uploads/pictures/${s.profile_img}"
																alt="student-profile">${s.name_}
															</span>
														</p></td>
													<td>${s.ssn}</td>
													<td>${s.phone}</td>
													<td>
														<c:set var="initial_reg_date" value="${s.initial_reg_date}" />
														<c:if test="${initial_reg_date!=null}">
															<df:dateForm date="${initial_reg_date}" />
														</c:if>
													</td>
													<th>${s.registration_count}</th>
													<th><button type="button"
																class="btn btn-info btn-sm btnDeleteStudent"
																value="${s.student_id}" ${(s.registration_count==0)?"":"disabled"}>삭제</button></th>
													<th><button type="button"
															class="btn btn-info btn-sm btnCourseAdd"
															data-toggle="modal"
															data-target="#student-course-register"
															value="${s.student_id}">등록</button></th>
													<th><a
														href="${pageContext.request.contextPath}/manager/student/dropout?student_id=${s.student_id}"><button
																type="button" class="btn btn-info btn-sm"
																value="${s.student_id}" ${(s.dropout_status=='Y')?"":"disabled"}>중도탈락</button></a></th>
													<th><a
														href="${pageContext.request.contextPath}/manager/student/coursecancel?student_id=${s.student_id}"><button
																type="button" class="btn btn-info btn-sm"
																value="${s.student_id}" ${(s.cancel_status=='Y')?"":"disabled"}>수강취소</button></a></th>
													<th><a
														href="${pageContext.request.contextPath}/manager/student/detail?student_id=${s.student_id}"><button
																type="button" class="btn btn-info btn-sm">
																<i class="fa fa-search"></i>
															</button></a></th>
												</tr>
											</c:forEach>
										</c:if>
										<c:if test="${studentCount==0}">
											<tr>
												<td colspan="11">등록된 수강생이 없습니다</td>
											</tr>
										</c:if>
									</tbody>
								</table>

								<div class="center-parent btn-data-table">
									<div class="center-child">

										<button type="button" class="btn btn-secondary btn-sm">
											TotalCount <span class="badge badge-dark">${totalCount}</span>
										</button>
										<button type="button" class="btn btn-secondary btn-sm">
											Count <span class="badge badge-dark">${resultSize}</span>
										</button>
										<button type="button" class="btn btn-secondary btn-sm btnPrevious">Previous</button>
										<button type="button" class="btn btn-secondary btn-sm btnNext">Next</button>
										<div id="search-box">
											<form class="form-inline" method="post">
												<label> 
												<select name="key" id="key" name="bootstrap-data-table_length" aria-controls="bootstrap-data-table"
													class="form-control form-control-sm">
													<option	value="student_id">수강생ID</option>
													<option value="name_">수강생 이름</option>
													<option value="ssn">주민번호 뒷자리</option>
													<option value="phone">전화번호</option>
												</select>
												</label>
												<label> 
													<input type="text"	class="form-control form-control-sm" placeholder="Search" id="value" name="value"
														aria-controls="bootstrap-data-table">
												</label>										
												<button type="submit" class="btn btn-secondary btn-sm">
													<span class="ti-search"></span>
												</button>
											</form>
										</div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- .content -->

			<!-- Modal -->
			<div class="modal fade" id="student-register" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">새로운 수강생 등록</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<form id="studentInsert"
						action="${pageContext.request.contextPath}/manager/student/studentInsert"
						method="POST" enctype="multipart/form-data" >
						<div class="modal-body">

							<div class="form-group">
								<label for="name_" class=" form-control-label">수강생 이름</label>
								<input type="text" id="name_" name="name_"
									placeholder="수강생 이름을 입력하세요." class="form-control" required>
							</div>
							<div class="form-group">
								<label for="ssn" class=" form-control-label">주민번호
									뒷자리</label> <input type="text" id="ssn" name="ssn"
									placeholder="주민번호 뒷자리를 입력하세요." class="form-control" required>
							</div>
							<div class="form-group">
								<label for="phone" class=" form-control-label">전화번호</label> <input
									type="text" id="phone" name="phone" placeholder="전화번호를 입력하세요."
									class="form-control" required>
							</div>
							<div class="form-group">
								<label for="initial_reg_date">최초 등록일</label> <input type="date"
									class="form-control" id="initial_reg_date" name="initial_reg_date" required>
							</div>
							<div class="form-group">
								<label for="profile_img">사진 등록</label> <input type="file"
									class="form-control" id="file" name="file">
							</div>

						</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-default">확인</button>
							<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>

						</div>
						</form>
					</div>

				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="student-course-register" role="dialog">
				<div class="modal-dialog modal-lg">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<h6 class="modal-title">수강생 S000(name_/ssn)의 수강할 과정 선택</h6>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<form id="studentOcAdd" action="${pageContext.request.contextPath}/manager/student/studentOcAdd" method="POST" >
						<div class="modal-body">
							<div class="form-group">
								<select id="oc_id" name="oc_id" class="form-control">
									<option value="0">수강할 과정을 선택하세요</option>
									<c:if test="${ocAddibleListCount>0}">
									<c:forEach var="a" items="${ocAddibleList}">
										<option value="${a.oc_id}">
												${a.oc_id} / ${a.oc_title} / 
												<c:set var="oc_start_date" value="${a.oc_start_date}" />
												<c:if test="${oc_start_date!=null}">
													<df:dateForm date="${oc_start_date}" />
												</c:if>~
												<c:set var="oc_end_date" value="${a.oc_end_date}" />
												<c:if test="${oc_end_date!=null}">
													<df:dateForm date="${oc_end_date}" />
												</c:if> 
												/ ${a.classroom_name} / 개설과목 ${a.subject_offered_number} / 수강인원 ${a.registerred_number}
										</option>
									</c:forEach>
									</c:if>
									<c:if test="${ocAddibleListCount==0}">
									<option value="0">등록 가능한 과정이 없습니다.</option>
									</c:if>
								</select>
							</div>
							<div class="form-group">
								<input type="hidden" id="student_id" name="student_id" value=""/>
							</div>
							<div class="form-group" id="courseHistory">
								<table class="table table-striped table-bordered dataTable no-footer">
									
								</table>
							</div>
							<div id="add-error"></div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default btnStudentReg">확인</button>
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
		
		<script>
		// 마우스 오버시 툴팁 보이게
		$("p.tt.student-profile").on("mouseover", function() {
			$(this).find("span.tt-text").css("visibility", "visible");
		}).on("mouseout", function() {
			$(this).find("span.tt-text").css("visibility", "hidden");
		});
		
		// 파일 업로드 실패시 경고창
		var fail = "${fail}";
		if (fail == 1) {
			alert("파일 업로드에 실패했습니다.(.jpg, .jpeg, .bmp, .png, .gif 파일만 업로드 가능합니다.)");
		}
		
		</script>
	</div>
</body>
</html>
