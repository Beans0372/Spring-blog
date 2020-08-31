let index = {
	// 변수는 let 쓰고 ,함수의 이름이 없어도 실행될수밖에 없는 영역에 ()=> 씀
	init : function(){
		// 이벤트를 바인딩한다.
		// 1.리스너
		$("#btn-save").on("click",()=>{
			// 콜백 스택
			this.save();
		});

		$("#btn-delete").on("click",()=>{
			this.deleteById();
		});
	},

	// 이렇게 설계하면 장점 : 버튼 리스너를 새로 만들고 밑에서 처리하면됨
	save : function() {
		let data = {
				title:$("#title").val(),
				content:$("#content").val(),
				userId:$("#userId").val()
		};
		// fetch쓰면 jquery안써도 됨
		$.ajax({
			//
			type :"POST",
			url: "/post",
			// 자바스크립트 오브젝트를 JSON String으로 바꾼것
			data : JSON.stringify(data),
			// 스프링이 메세지 컨버터가 작동해서 requestBody를 붙여야함
			// 그러기 위해서는 스프링에게 컨텍스트 타입을 알려줘야한다. 그래야만 오브젝트로 변환해줌
			contentType : "application/json; charset=utf-8",
			// 응답 받는 타입 설정
			dataType : "json"
		}).done((resp)=>{
			alert("글쓰기 성공");
			location.href="/";
			console.log(resp);
		}).fail(function(error){
			alert("글쓰기 실패");
			console.log(error);
		})
		// 자바스크립트오브젝트 => 제이슨 스트링
		// JSON.stringify(자바스크립트오브젝트);
		// 제이슨 스트링 => 자바스크립트 오브젝트
		// JSON.parse(제이슨스트링);
	},

	deleteById : function() {
		let data = {
				id:$("#id").val()
		};
		// fetch쓰면 jquery안써도 됨
		$.ajax({
			//data를 날릴필요 없음.
			type :"DELETE",
			url: "/post/"+data.id,
			dataType : "text"
		}).done((resp)=>{
			alert("글쓰기 삭제 성공");
			location.href="/";
			console.log(resp);
		}).fail(function(error){
			alert("글쓰기 삭제 실패");
			console.log(error);
		})
	}
}

index.init();