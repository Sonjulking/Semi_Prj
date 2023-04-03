/**
 *  매칭 팝업창 js  매칭하게 누르면 matching.jsp 실행하게 하기
 */

const button1 = document.querySelector('.matching-btn');

button1.addEventListener("click", function() {
	window.open("matching/matching.jsp", "팝업1", "width=400, height=450, left=0, top=0");
});

const closeWindow = document.querySelector(".close");

closeWindow.addEventListener('click', function() {
	window.close();
});