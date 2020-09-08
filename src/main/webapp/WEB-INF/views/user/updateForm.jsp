<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %> 

<div class="container">
   <form class="was-validated" >

     <c:set var="principal" value="${sessionScope.principal}" />
     <div class="form-group">
       <label for="username">Username:</label>
       <input type="text" class="form-control" id="username" value="${principal.username}" readOnly>
     </div>

     <div class="form-group">
       <label for="pwd">Password:</label>
       <input type="password" class="form-control" id="password" required>
     </div>

     <div class="form-group">
       <label for="email">Email:</label>
       <input type="email" class="form-control" id="email" value="${principal.email}" required>
     </div>
		<%-- type을 button으로 함으로써 클릭시 submit 방지 --%>
     <button id="btn-update" type="button" class="btn btn-primary">수정완료</button>
   </form>
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp" %>