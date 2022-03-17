# [JavaScript] 이벤트

태그: 내용정리

# 이벤트 처리

### 1. 인라인 방식

```html
<head>
	<script>        
		function btn_click() {            
			console.log("btn_clicl");        
		}        

		// 파라미터 사용    => 개수 미정시 ...rest 사용        
		function ok_click(n, n2 = 999, ...rest) {            
			console.log("ok", n, n2, rest);        
		}        

		// 이벤트 객체 사용        
		function btn_click() {            
			console.log(event); // 이벤트 객체            

			// DOM 접근            
			console.log(event.target);            
			console.log(""+event.target.innerText);            
			console.log(""+event.target.innerHTML);            
			event.target.disabled=true;            
			event.target.innerText="Cancel";            
			
			// 키보드 입력시 유용한 DOM 접근            
			console.log(event.key); // 현재 입력한 키 값            
			console.log(event.target.value);    // 현재 입력한 키 값        
		}    
	</script>
</head>
<body>    
	<button onclick="btn_click()">버튼</button>    
	
	<!-- 함수와 동일하게 파라미터 값을 줄 수 있음 -->    
	<button onclick="ok_click(100)">ok클릭1</button><br>    
	<button onclick="ok_click('a',200)">ok클릭2</button><br>    
	<button onclick="ok_click('a',200,4,4,5,6)">ok클릭3</button><br>
</body>
```

### 2. DOMLevel0 고전 이벤트 방식

window.onload=function() {  };

<body onload="함수"> 역할: body를 먼저 실행 후 script를 실행함

⇒ 즉, 모든 html의 태그(DOM)가 DOM트리로 만들어졌을 때 실행됨

```html
<head>    
	<script type="text/javascript">     
		window.onload=function(){
			document.querySelector("#ok").onclick=function() {                
				console.log("okee2");            
			};        
		};    
	</script>
</head>
<body>    
	<button id="ok">ok클릭</button>
</body>
```

### 3. DomLevel2 방식

```html
<head>    
	<script type="text/javascript">        
		window.onload=function(){            
			document.querySelector("#ok")                    
							.addEventListener("click", function(){                        
								console.log("ok");                    
							}, false);        
		};    
	</script>
</head>
<body>    
<button id="ok">ok클릭</button><br>
</body>
```