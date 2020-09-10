# Springboot introduction

인프런 인강들으면서 스프링부트 입문하기

## Spring project 생성

[spring project 생성](start.spring.io)

> Project

+ Maven
+ Gradle

필요한 library를 가져오고 bulid의 lifecycle까지 관리해주는 tool

요즘에는 Gradle을 많이 사용한다.

> Spring boot version

snapshot: 만들고 있음
m*: 정식 릴리즈가 안된 버전

> Project Metadata

+ group: 기업명
+ artifact: 빌드되어 나온 결과물(프로젝트 명)

> Dependencies

+ 어떤 라이브러리를 땡겨서 쓸 것인가?

탬플릿 엔진: html을 만들어 주는 역할

## 파일 구조

```
`-- hello-spring
    |-- idea - intellj 설정 파일
    |-- gradle - gradle 관련 폴더
    |   `-- wrapper
    `-- src
        |-- main
        |   |-- java
        |   |   `-- HELLO
        |   |       `-- hellospring
        |   `-- resources - 자바이외의 파일들이 여기 들어간다
        |       |-- static
        |       `-- templates
        `-- test - 테스트코드들이 들어가 있음
            `-- java
                `-- HELLO
                    `-- hellospring
```

+ bulid.gradle

ruby의 gemfile과 같은 역할임

```
plugins {
	id 'org.springframework.boot' version '2.3.3.RELEASE' // springboot version
	id 'io.spring.dependency-management' version '1.0.10.RELEASE'
	id 'java'
}

group = 'HELLO'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '11' // java 11 version

repositories {
	mavenCentral() // 라이브러리를 다운로드 받는 사이트를 지정, 특정 사이트 url넣을 수 있음
}

dependencies { // start.spring.io에서 선택한 것들이 여기에 들어감
	implementation 'org.springframework.boot:spring-boot-starter-thymeleaf' // templete engine
	implementation 'org.springframework.boot:spring-boot-starter-web' // web project
	testImplementation('org.springframework.boot:spring-boot-starter-test') { // junit - test를 위한 라이브러리
		exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
	}
}

test {
	useJUnitPlatform()
}
```

## 실행

```
Tomcat started on port(s): 8080 (http) with context path ''
```

`localhost:8080`

## 라이브러리 살펴보기

`라이브러리가 의존한다.`

> 실행하기위해 필요한 라이브러리를 땡겨온다.

ex) spring-boot-web을 우리가 선택하고 시작했는데 이 spring-boot-web라이브러리는 spring-start-tomcat, spring-start-core등의 라이브러리가 필요하다.   
    즉, spring-boot-web은 spring-boot-tomcat에 의존한다고 말할 수 있다.

external libraries에서 확인할 수 있다.

<div align ="center">

![](../img/dependencies.PNG)

</div>

자세한 내용은 pdf를 참고하자

## view 환경설정

1. welcome page(index page)

+ html파일 위치
    - resources/static

2. 스프링 부트가 제공하는 welcome page 기능
   + `static/index.html`을 올려두면 자동적으로 기능제공
   + 없는 경우, index templete을 찾는다.

3. 링크

[spring docs](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-bootfeatures.html#boot-features-spring-mvc-welcome-page)

+ thymeleaf 템플릿 엔진

[thymeleaf 공식 사이트](https://www.thymeleaf.org/)
[스프링 공식 튜토리얼](https://spring.io/guides/gs/serving-web-content/)
[스프링부트 메뉴얼](https://docs.spring.io/spring-boot/docs/2.3.1.RELEASE/reference/html/spring-boot-features.html#boot-features-spring-mvc-template-engines)

## controller

1. controller만들기
java/HELLO.hellospring위치에서,   
controller package생성후 HelloController클래스 생성   

```java
package HELLO.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    // http의 get method이다. 우리가 url을 치고 던져주면 그것은 get방식이다.
    @GetMapping("hello") // localhost:8080/hello와 매핑하게 만들어주는 annotation
    public String hello(Model model) { // MVC모델에서의 Model
        model.addAttribute("data", "hello!!");// 인자로 받은 model에 data라는 key에 | hello!!라는 값을 넣는다. 
        return "hello"; // resources/templetes의 hello.html로 넘어가라
    }
}
```

![구조](../img/architecture.PNG)

+ 컨트롤러에서 리턴 값으로 문자를 반환하면 뷰 리졸버( viewResolver )가 화면을 찾아서 처리한다.
    + 스프링 부트 템플릿엔진 기본 viewName 매핑
    + resources:templates/ +{ViewName}+ .html

## terminal로 server 실행하기

bulid와 실행

```bash

$ ./gradlew bulid

$ cd build/libs

$ java -jar hello-spring-0.0.1-SNAPSHOT.jar

```

bulid 삭제 후 재생성

```bash
$ ./gradlew clean bulid

$ cd build/libs

$ java -jar hello-spring-0.0.1-SNAPSHOT.jar
```