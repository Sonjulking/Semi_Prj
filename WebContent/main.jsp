<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="loginCheck" value="0" />
<c:if test="${!empty sessionScope.LoginCheck}">
  <c:set var="loginCheck" value="${sessionScope.LoginCheck}" />
</c:if>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>겜만추</title>
    <script
      type="text/javascript"
      src="https://code.jquery.com/jquery-3.6.1.js"
    ></script>
    <!-- fontawesome cdn -->
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
    />
    <link
      href="https://unpkg.com/nes.css@latest/css/nes.min.css"
      rel="stylesheet"
    />

    <link rel="stylesheet" href="css/main.css" />
    <link rel="stylesheet" href="css/nav.css " />
  </head>

  <body>
    <%@ include file="include/header.jsp"%>

    <div class="main_wrap">
      <!-- <img class="ruru" src="img/rurupixel.png" alt="" title="귀여운 루루">

		<img class="nar" src="img/nar.png" alt="" title="포악한 나르"> <img
			class="timo" src="img/timo.png" alt="" title="혐모"> <img
			class="youme" src="img/youme.png" alt="" title="버스충 유미"> -->

      <div
        class="pop_post_wrap main_box1 nes-container is-rounded is-dark with-title is-centered"
      >
        
        
        <p class="box_text title">실시간 인기글</p>
        <a href="<%=request.getContextPath() %>/hot_free1.do">
          <div class="item nes-container is-rounded is-dark">
            <div class="ranking">1</div>
            <img src="" class="sumimg" />
            <div class="title_con">
              <div class="item-title">야스오 강의</div>
              <div class="date_writer_con">
                <div class="date">2023-09-16</div>
                <div class="writer">실버 판테온</div>
              </div>
            </div>
            <div class="comment">[65]</div>
          </div>
        </a>
        <a href="<%=request.getContextPath() %>/hot_free2.do">
          <div class="item nes-container is-rounded is-dark">
            <div class="ranking">2</div>
            <img src="" class="sumimg" />
            <div class="title_con">
              <div class="item-title">야스오 강의</div>
              <div class="date_writer_con">
                <div class="date">2023-09-16</div>
                <div class="writer">실버 판테온</div>
              </div>
            </div>
            <div class="comment">[65]</div>
          </div>
        </a>
        <a href="<%=request.getContextPath() %>/hot_legend1.do">
          <div class="item nes-container is-rounded is-dark">
            <div class="ranking">3</div>
            <img src="" class="sumimg" />
            <div class="title_con">
              <div class="item-title">야스오 강의</div>
               <div class="date_writer_con">
                <div class="date">2023-09-16</div>
                <div class="writer">실버 판테온</div>
              </div>
            </div>
            <div class="comment">[65]</div>
          </div>
        </a>
        <a href="<%=request.getContextPath() %>/hot_legend2.do">
          <div class="item nes-container is-rounded is-dark">
            <div class="ranking">4</div>
            <img src="" class="sumimg" />
            <div class="title_con">
              <div class="item-title">야스오 강의</div>
              <div class="date_writer_con">
                <div class="date">2023-09-16</div>
                <div class="writer">실버 판테온</div>
              </div>
            </div>
            <div class="comment">[65]</div>
          </div>
        </a>
       
      </div>
      <div
        class="pop_post_wrap main_box1 nes-container is-rounded is-dark with-title is-centered"
      >
       
        <p class="box_text title">실시간 매칭</p>
        <div>
          <!-- <img class="matching-img" src="../WebContent/img/1.png" /> -->
        </div>
        <c:if test="${loginCheck == 0 }">
          <button
            type="button"
            id="match-btn1"
            class="PleaseLogin nes-container is-rounded is-dark"
          >
            매칭하기
          </button>
        </c:if>

        <c:if test="${loginCheck > 0 }">
          <button
            type="button"
            id="match-btn2"
            class="matching-btn nes-btn is-primary"
          >
            매칭하기
          </button>
        </c:if>
      </div>
    </div>

    <!-- 매칭 창 팝업 js -->
    <script type="text/javascript" src="matching/pop.js"></script>
    <script type="text/javascript" src="matching/pop2.js"></script>

    <%@ include file="include/footer.jsp"%>
  </body>
</html>
