/**
 *  매칭하기 누르면  PleaseLogin.jsp 실행하게 하기
 */
	
const button1 = document.querySelector('#match-btn1');
// const button2 = document.querySelector('#match-btn2');



button1.addEventListener("click", function() {
	window.open("matching/PleaseLogin.jsp", "팝업1", "width=450, height=550, left=0, top=0");
});

/*
button2.addEventListener("click", function() {
	window.open("matching/matching.jsp", "팝업2", "width=450, height=450, left=0, top=0");
});
*/



