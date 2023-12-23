## Spring Boot 3.2.x Changes


## To parse JSP in the **_11-web-application_** project, kindly implement the following modifications.

## Step1: Replace the following dependency
Use below dependency for **_Spring Boot <= 3.0.x_**
```xml
<dependency>
     <groupId>org.eclipse.jetty</groupId>
     <artifactId>glassfish-jstl</artifactId>
</dependency>
```
WITH

Use below dependency for **_Spring Boot >= 3.2.x_**
```xml
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
</dependency>
```
## Step2: Replace the following taglib in `header.jspf`
> No change for **_Spring Boot <= 3.0.x_**
### AS-IS 
```jsp 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

### TO-BE (For Spring Boot 3.2.x)
```jsp
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
```

And then execute the `mvn clean install` command

<img src="https://media2.giphy.com/media/rdEE8wlaB5ngr5o2rZ/200w.webp?cid=ecf05e47dmj9sizqw0oa4dqq4z1wu97c3nr4cttjl0r69mny&ep=v1_gifs_search&rid=200w.webp&ct=g" width="100">

in28minutes team