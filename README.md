# spring_workspace
스프링 공부해서 취업하기   
## http method
Get - 조회 (select )
post - 생성(insert)
patch - 수정(update)
delete - 삭제(delete)
## 스프링 gradle 생성
<a>https://start.spring.io/</a>
## HTTP 오류
404: 페이지를 찾을 수 없음
500: 개발자 실수
401: 권한 없음
200: 성공
----------------
## db연동
Spring 으로 데이터베이스 연동하는 방법
1. gradle에 데이터베이스 라이브러리 추가
//데이터베이스 라이브러리 ->  jdbc.jar
1-1 구글에 maven mysql 검색
1-2(https://mvnrepository.com)접속
1-3 mysql gradle용 복사
1-4 build.gradle에 붙여넣기
1-5 mvnrepository에서 mybatis검색
1-6 MyBatis Spring Boot Starter
1-7 MyBatis gradle복사
1-8 build gradle에 붙여넣기
1-9 build gradle 새로고침
//
2. 속성파일에 db아이디, 비밀번호 작성
	2-1. properties 확장명을 yaml(야물)으로 수정
	2-2, DB 접속 정보 입력
3. Spring과 Mybatis연결
	3-1. resources 경로에 sqlmap폴더 생성 (new-> package)
	3-2. sqlmap에 xml파일 생성(이름은 sqlmapper_(하고싶은이름).xml)
	3-3. 속성파일(yaml)에 sqlmapper_*.xml 경로 작성
	
4. 결과 확인하기



src/main/java: java소스(여기서 코딩)
src/main/resources:
	데이터베이스 연결 정보 (id, password)
	sql, css, images,javascript
Footer
