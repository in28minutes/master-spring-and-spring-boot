# Getting Started


## HTML

```
<html>
	<head>
		<title> My first HTML Page</title>
	</head>
	<body>
		My first html page with body
	</body>
</html>
```

## JSP

/src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp

/say-hello-jsp => SayHelloController - sayHelloJsp method => sayHello

/WEB-INF/jsp/sayHello.jsp


## Login JSP

/login => com.in28minutes.springboot.myfirstwebapp.login.LoginController => login.jsp


## 
localhost:8080/login

B1: Identifies correct Controller method
/login => LoginController.gotoLoginPage

B2: Executes Controller method
=> Puts data into model
=> Returns view name => login

B3: Identifies correct View
/WEB-INF/jsp/login.jsp

B4: Executes view


## Todo

id
username
description
targetDate
done

TodoController
listTodos.jsp

## Request vs Model vs Session

- Request
- Model
- Session

## Validations
- Client Side vs Server Side
- 1: spring-boot-starter-validation
- 2: 

























