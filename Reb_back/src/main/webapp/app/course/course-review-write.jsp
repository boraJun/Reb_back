<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/course/course-review-write.css">

  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/footer.css">

  <script defer src="${pageContext.request.contextPath}/assets/js/course/course-review-write.js"></script>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&family=Noto+Sans+KR&display=swap"
    rel="stylesheet">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link
    href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+KR&family=Noto+Sans+KR&family=Yeon+Sung&display=swap"
    rel="stylesheet">
  <title>틈새빛</title>
</head>

<body>
<jsp:include page="/header.jsp" />
  <main>
    <div id="main-container">
      <p class="pagetitle">수업 후기 작성</p>
      <form action="${pageContext.request.contextPath}/course/courseReviewWriteOk.co" method="post" enctype="multipart/form-data">
        <div class="div-create-course">
          <input type="text" class="input-title input-bundle" name="courseReviewTitle" placeholder="제목을 입력해주세요" required>
        </div>
        <div><span class="span-course">수강한 수업</span>
        	<select class="course" name="courseNumber" required>
        		<c:forEach var="courselist" items="${courseMemberApplicant}">
        			<option value="${courselist.getCourseNumber()}" ><c:out value="${courselist.getCourseTitle()}" /></option>
        		</c:forEach>
        	</select>
        </div>
        <div class="div-create-course">
          <span class="font-main little-title">대표 이미지 : </span>
          <input type="file" name="courseFile" class="input-bundle" required>
        </div>
        <div class="div-create-course">
          <p class="font-main little-title">내용</p>
          <textarea class="textarea-text input-bundle" name="courseReviewContent" rows="20" cols="100" required></textarea>
        </div>

        <div class="div-create-course">
          <div class="div-button-line">
            <button class="button-ok" type="submit">확인</button>
            <a href="${pageContext.request.contextPath}/app/course/course-review-list.jsp"><button class="button-cancel" type="button">취소</button></a>
          </div>
        </div>
      </form>
    </div>
  </main>
	<jsp:include page="/footer.jsp" />
	<script src="${pageContext.request.contextPath}/assets/js/footer.js"></script>
</body>
</html>