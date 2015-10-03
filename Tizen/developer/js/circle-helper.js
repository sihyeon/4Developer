/*global tau */
/*jslint unparam: true */
(function(tau) {
	var page,
		elScroller,
		list,
		headerHelper,
		listHelper = [],
		snapList = [],
		headerExpandHandler = [],
		headerCollapseHandler = [],
		i, len;

(function() {
		//화면 꺼짐 방지
		tizen.power.request("SCREEN", "SCREEN_DIM");
		
		var page = document.getElementById( "main" );;
		var changer = document.getElementById( "tabsectionchangerPage" );
		var s_pointX, s_pointY, e_pointX, e_pointY, lengthX, lengthY;
		var idx=1;
		console.log("javascript loaded");

		page.addEventListener( "touchstart", function(e){
			s_pointX = e.touches[0].clientX;
			s_pointY = e.touches[0].clientY;
		});

		page.addEventListener( "touchmove", function(e){
			e_pointX = e.touches[0].clientX;
			e_pointY = e.touches[0].clientY;
		});

		page.addEventListener( "touchend", function(){
			lengthX = (s_pointX > e_pointX)?s_pointX-e_pointX:e_pointX-s_pointX;
			lengthY = (s_pointY > e_pointY)?s_pointY-e_pointY:e_pointY-s_pointY;

			// SAP 연결 확인
			if(!SASocket){
				//SAP 연결 안되어있으면 연결
				connect();
				console.log("reconnect");
				dispPopup("핸드폰과 연결되지 않았습니다."+'</br>'+"연결을 진행합니다.");
			}
			
			//터치 분석
			else if(lengthX + lengthY <= 60){
				//touch
				console.log("touched");
				controlPage("touch");
		    }
		});
}
