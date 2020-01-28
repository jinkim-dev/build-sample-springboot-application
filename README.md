# build-sample-springboot-application
It is an application that helps you to build easily.

## Description
어플리케이션 빌드 정보를 관리하여 빌드 자동화 샘플 어플리케이션이다.

## Weekly Plan
1. 기본 구성
  * UI 구성도 작성
  * 프로젝트 설계 및 기본 구성
2. 화면 제작
  * vue.js 나 react.js를 활용한 화면 완성
  * hibernate를 활용한 mysql 연동
3. 기능 구현
  * 빌드 정도 등록
  * jenkins를 활용하여 CI 구현
* 3주안에 접하지 못한 새로운 기술들이 사용되어 jenkins 연동은 추후에 진행 될 수 있다.*

## 활용 기술
|Dependencies|Compatibility|
|-------------|-------------|
|IDE|intellij|
|Gradle|5.2.1|
|Spring Boot|2.1.4.RELEASE|
|Java|1.11+|
|Mysql|8.0+|
|vue.js or react.js||

# 1차 시나리오
1. CI할 application build 정보를 입력한다.
2. 입력한 application을 build한다.
3. build한 application을 패키징하여 war파일을 배포한다.
4. application의 배포 결과를 확인한다.
