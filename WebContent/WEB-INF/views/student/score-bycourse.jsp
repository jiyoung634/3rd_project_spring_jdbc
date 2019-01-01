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
				<a class="navbar-brand" href="${pageContext.request.contextPath}/student/main"><img
					src="${pageContext.request.contextPath}/resources/images/logo-white.png" alt="Logo"></a> <a
					class="navbar-brand hidden" href="${pageContext.request.contextPath}/student/main"><img
					src="${pageContext.request.contextPath}/resources/images/logo-small.png" alt="Logo"></a>
			</div>

			<div id="main-menu" class="main-menu collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="user-profile"><img src="${pageContext.request.contextPath}/resources/uploads/pictures/${sessionScope.studentLoginInfo.profile_img}"
						alt="student.png">
						<h5 class="text-sm-center mt-2 mb-1 profile-font">수강생</h5>
						
						<p class="text-sm-center mb-1 profile-font2">${sessionScope.studentLoginInfo.name_}</p>
				
						<a href="${pageContext.request.contextPath}/logout">
						<button type="button" class="btn btn-success btn-sm btn-logout">Logout</button></a>
					</li>
					<li><p class="menu-title">수강생 메뉴</p></li>
					<!-- /.menu-title -->
					<li><a href="${pageContext.request.contextPath}/student/main"> <i
							class="menu-icon fa fa-home"></i>Main
					</a></li>
					<li class="active"><a href="${pageContext.request.contextPath}/student/score"> <i
							class="menu-icon fa fa-bar-chart-o"></i>성적 조회
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
							class="user-avatar rounded-circle" src="${pageContext.request.contextPath}/resources/uploads/pictures/${sessionScope.studentLoginInfo.profile_img}"
							alt="User Avatar">
						</a>

						<div class="user-menu dropdown-menu">
							<a class="nav-link" href="${pageContext.request.contextPath}/student/main"><i class="fa fa- user"></i>Main</a>
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
						<h1>성적 조회</h1>
					</div>
				</div>
			</div>

			<div class="col-sm-8">
				<div class="page-header float-right">
					<div class="page-title">
						<ol class="breadcrumb text-right">
							<li><a href="${pageContext.request.contextPath}/student/score">성적 조회</a></li>
							<li class="active">과정별 성적 조회</li>
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
								<strong class="card-title">${ocInfo.oc_title}(<df:dateForm date="${ocInfo.oc_start_date}"/>~<df:dateForm date="${ocInfo.oc_end_date}"/>) 성적 조회</strong>
							</div>
							<div class="card-body">
								<table id="" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>개설 과목명</th>
											<th>개설 과목 기간</th>
											<th>교재명</th>
											<th>강사명</th>
											<th>출결점수</th>
											<th>필기점수</th>
											<th>실기점수</th>
											<th>총점</th>
											<th>시험날짜</th>
											<th>시험문제</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach var="s" items="${scoreList}">
										<tr>
											<td>${s.os_name}</td>
											<td><df:dateForm date="${s.os_start_date}"/>~<br><df:dateForm date="${s.os_end_date}"/></td>
											<td><p class="tt interpark-book"> ${s.textbook_title}
													<span class="tt-text"> 
													<img alt="${s.isbn}">${s.textbook_title}<br>${s.publisher}<br>ISBN: ${s.isbn}
													</span>
												</p>
											</td>
											<td><p class="tt instructor-profile">
													${s.name_}<span class="tt-text"> <img
														src="${pageContext.request.contextPath}/resources/uploads/pictures/${s.profile_img}" alt="instructor">${s.name_}강의가능과목<br>${s.available_subjects}
													</span>
												</p>
											</td>
											<td>${s.attendance_score} / <b>${s.attendance_percentage}</b></td>
											<td>${s.writing_score} / <b>${s.writing_percentage}</b></td>
											<td>${s.practice_score} / <b>${s.practice_percentage}</b></td>
											<td>${s.total_score}</td>
											<td><df:dateForm date="${s.exam_date}"/></td>
											<td><a href="${pageContext.request.contextPath}/resources/uploads/files/${s.exam_file}">${s.exam_file}</a></td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
	
					<!-- Chart -->
					<div class="col-md-12">
						<div class="card">
							<div class="card-header">
								<strong class="card-title">성적</strong>
							</div>
							<div class="card-body">
								<div class="student-score-chart-box">
								<canvas id="student-score-chart"></canvas>
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
	<!-- /#right-panel -->

	<!-- Right Panel -->

	<script src="${pageContext.request.contextPath}/resources/assets/js/vendor/jquery-2.1.4.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/plugins.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/main.js"></script>

	<!-- 
	<script src="${pageContext.request.contextPath}/resources/assets/js/custom.js"></script>
	-->

    <script src="${pageContext.request.contextPath}/resources/assets/js/lib/chart-js/Chart.bundle.js"></script>
	
	<!-- 
	<script src="${pageContext.request.contextPath}/resources/assets/js/dashboard.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/widgets.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/jquery.vmap.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/jquery.vmap.min.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/jquery.vmap.sampledata.js"></script>
	<script src="${pageContext.request.contextPath}/resources/assets/js/lib/vector-map/country/jquery.vmap.world.js"></script>
	-->

	<!-- Global site tag (gtag.js) - Google Analytics -->
	<!-- 
	<script async="" src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'UA-23581568-13');
	</script>
	 -->
	
	
	<!-- Chart -->
	<script>
	//bar chart
    var ctx = document.getElementById( "student-score-chart" );
    var chartData = "${chartData}";

    var jobj =  JSON.parse(chartData);

    var labelData = [];
    var attendanceData = [];
    var writingData = [];
    var practiceData = [];
    var totalData = [];
    
    for(s in jobj.Student){		
    	labelData[s]=jobj.Student[s].os_name;
    	attendanceData[s] = jobj.Student[s].attendance_score;
    	writingData[s] = jobj.Student[s].writing_score;
    	practiceData[s] = jobj.Student[s].practice_score;
    	totalData[s] = jobj.Student[s].total_score;
	   }
   
    // ctx.height = 200;
	    var myChart = new Chart( ctx, {
	        type: 'bar',
	        data: {
	            labels: labelData,
	            datasets: [
	                {
	                    label: "출결 점수",
	                    data: attendanceData,
	                    borderColor: "rgba(165, 232, 243, 0.7)",
	                    borderWidth: "0",
	                    backgroundColor: "rgba(165, 232, 243, 0.7)"
	                            },
	                {
	                    label: "필기 점수",
	                    data: writingData,
	                    borderColor: "rgba(120, 221, 237, 0.7)",
	                    borderWidth: "0",
	                    backgroundColor: "rgba(120, 221, 237, 0.7)"
	                            },            
	                {
	                    label: "실기 점수",
	                    data: practiceData,
	                    borderColor: "rgba(53, 204, 227, 0.7)",
	                    borderWidth: "0",
	                    backgroundColor: "rgba(53, 204, 227, 0.7)"
	                            },
	                {
	        	        label: "총점",
	        	        data: totalData,
	        	        borderColor: "rgba(24, 159, 180, 0.7)",
	        	        borderWidth: "0",
	        	        backgroundColor: "rgba(24, 159, 180, 0.7)"
	             			    }
	                    ]
	        },
	        options: {
	            scales: {
	                yAxes: [ {
	                    ticks: {
	                        beginAtZero: true
	                    }
	                                } ]
	            }
	        } 
	    });
    
	</script>
	
	<script>
	$( document ).ready(function() {
		
		var coverurl;
		var totalResults;
		
		$("p.tt.interpark-book").on("mouseover", function(){
			
			// alt에 걸린 ISBN 찾기
			var alt = $(this).find("img").attr("alt");
			
			// interparkAPI를 AJAX로 호출		
			var url_ = "${pageContext.request.contextPath}/student/interparkapi";
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
