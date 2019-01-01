<%@ tag body-content="empty" pageEncoding="utf-8"%>
<%@ tag trimDirectiveWhitespaces="true"%>
<%@ tag import="java.time.LocalDate, java.time.format.DateTimeFormatter"%>

<%@ attribute name="date" type="java.lang.String" required="true"%>
<%
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.S");
	LocalDate parsedDate = LocalDate.parse(date, formatter);
	String dateResult = parsedDate.toString();
%>
<%=dateResult%>