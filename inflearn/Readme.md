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