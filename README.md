# 개요

- 프로젝트 명 : Spring Boot 게시판 API 구현
- 개발 언어 : Java 17
- 개발 환경 : Spring Boot 3.3.0
- 데이터베이스 : H2
- 형상관리 툴 : GitHub Actions
- 메인 기능
  - 1. 게시판 - CRUD 기능, 페이징, 검색
  - 2. 댓글 - CRUD 기능
  - 3. 회원 - 회원가입, 로그인, 회원정보 수정, 유효성 및 중복 검사 
- 서브 기능
  - 

---

# 1. 데이터 베이스 설계



# 2. **API 설계 - CRUD**

게시판 기능을 제공하는 RESTful API 해당 API는 HTTP 프로토콜을 사용하며, JSON 형식의 데이터를 주고받습니다.

## Board

| 기능 | 메소드 | URL | RETURN TYPE |
| --- | --- | --- | --- |
| 단건 조회 요청 | GET | /boards/{id} | BoardResponseDtos |
| 전체 조회 요청 ( 페이징  적용 ) | GET | /boards?page={id} | BoardResponseDtos |
| 전체 조회 요청 | GET | /boards | BoardResponseDtos |
| 게시물 작성 | POST | /boards | BoardResponseDto |
| 게시물 수정 | PUT | /boards/{id} | BoardResponseDto |
| 게시물 삭제 | DELETE | /boards/{id} | “글 삭제 완료” |

## Member

| 기능 | 메소드 | URL | RETURN TYPE |
| --- | --- | --- | --- |
| 회원 등록 | POST | /member/save | MemberResponseDto |
| 회원 수정 | PATCH | /member/patch/{id} | MemberResponseDto |
| 회원 삭제  | DELETE | /members | "회원 삭제 완료" |

## Comment

| 기능 | 메소드 | URL | RETURN TYPE |
| --- | --- | --- | --- |
| 댓글 등록 | POST | /boards/{boardId}/comment | CommentResponseDto |
| 댓글 수정 | PATCH | /boards/comment/{commentId} | CommentResponseDto |
| 댓글 삭제  | DELETE | /boards/comment/{commentId} | "댓글 삭제 완료" |
| 댓글 조회 | GET | /{boardId}/comment | CommentResponseDtos |

---

# 3. Pre-setting

1. build.gradle (dependencies)

```
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
implementation 'org.springframework.boot:spring-boot-starter-web'
compileOnly 'org.projectlombok:lombok'
runtimeOnly 'com.h2database:h2'
annotationProcessor 'org.projectlombok:lombok'
testImplementation 'org.springframework.boot:spring-boot-starter-test'
implementation 'org.springframework.boot:spring-boot-starter-validation'
```

1. application.yml (H2 Database - dataSource)

```
spring:
  datasource:
    url: jdbc:h2:tcp://localhost:9092/~/testGina
    username: sa
    password: 1234
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
      default_batch_fetch_size: 1000
    properties:
      hibernate:
      show_sql: true
      format_sql: true
  data:
    web:
      pageable:
        default-page-size: 5
        max-page-size: 2000

logging.level:
  org.hibernate.SQL: debug

swagger-ui:
  path: /
  disable-swagger-default-url: true
  display-request-duration: true
  operations-sorter: alpha
  doc-expansion: none

```

---

#
