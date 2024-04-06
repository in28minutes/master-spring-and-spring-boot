## Course Update: Spring Boot 3.2.x JSTL Change

3 Important Changes
- Change 01: Use `jakarta.servlet.jsp.jstl` instead of `glassfish-jstl`
- Change 02: Use `jakarta.tags.core` instead of `http://java.sun.com/jsp/jstl/core` as taglib
- Change 03: Run `mvn clean install` to update libraries

### Change 01: Use `jakarta.servlet.jsp.jstl` instead of `glassfish-jstl`

**_Spring Boot 3.2.x and greater_**

```xml
<dependency>
    <groupId>org.glassfish.web</groupId>
    <artifactId>jakarta.servlet.jsp.jstl</artifactId>
</dependency>
```

`jakarta.servlet.jsp.jstl` replaces `glassfish-jstl` (recommended for **_Spring Boot <= 3.1.x_**)

```xml
<dependency>
     <groupId>org.eclipse.jetty</groupId>
     <artifactId>glassfish-jstl</artifactId>
</dependency>
```

## Change 02: Use `jakarta.tags.core` instead of `http://java.sun.com/jsp/jstl/core` as taglib

### Spring Boot 3.2.X and greater

Use
```jsp
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
```

### Spring Boot 3.1.X and lower
Use
```jsp 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```

### Change 03: Run `mvn clean install` to update libraries

And then execute the `mvn clean install` command

<img src="https://media2.giphy.com/media/rdEE8wlaB5ngr5o2rZ/200w.webp?cid=ecf05e47dmj9sizqw0oa4dqq4z1wu97c3nr4cttjl0r69mny&ep=v1_gifs_search&rid=200w.webp&ct=g" width="100">


Thank you!

in28minutes team (Maahi and Ranga)
