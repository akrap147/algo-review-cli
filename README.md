# algo-review-cli (algo)

백준 문제를 풀고, 망각 곡선(1-3-7-14-30일) 기반으로 자동 복습 일정을 잡아 주는 Java CLI입니다. 신규 문제 풀기/리뷰 관리/오늘의 할 일 추천을 한 번에 처리합니다.

## 주요 기능
- Pool 관리: 새로 풀 문제 후보를 저장/조회/삭제 (`pool add|list|remove`)
- Today Todo: 오늘 할 문제 추천 (신규 1 + 리뷰 N) 및 브라우저 열기 (`todo`)
- 완료 처리: 문제를 풀었다고 표시하고 다음 복습 날짜 자동 갱신 (`done <문제번호>`)
- 리뷰 목록: 오늘 복습 대상 문제 확인 (`review list`)

## 요구 사항
- Java 17+
- Gradle(래퍼 포함) 사용

## 빌드
```bash
# Fat JAR 생성 (shadowJar)
./gradlew :app:shadowJar

# 산출물
ls app/build/libs/app.jar
```

## 실행
```bash
# 도움말
java -jar app/build/libs/app.jar --help

# 예시
java -jar app/build/libs/app.jar pool list
java -jar app/build/libs/app.jar pool add 1000
java -jar app/build/libs/app.jar pool remove 1000
java -jar app/build/libs/app.jar todo
java -jar app/build/libs/app.jar done 1000
java -jar app/build/libs/app.jar review list
```

개발 중 빠른 실행은 Gradle run을 사용할 수 있습니다.
```bash
./gradlew :app:run --args="todo"
./gradlew :app:run --args="pool add 1000"
```

 

## 설치 (Homebrew)
Homebrew로 손쉽게 설치/업데이트할 수 있습니다. `openjdk@17`는 자동으로 함께 설치됩니다.

빠른 설치(권장):
```bash
brew tap akrap147/akrap
brew install algo
```

검증:
```bash
algo --help
```

업그레이드/삭제:
```bash
brew upgrade algo
brew uninstall algo
```

설치 구조:
- 실행 스크립트: `$(brew --prefix)/bin/algo`
- 애플리케이션 JAR: `$(brew --prefix)/Cellar/algo/<version>/libexec/app.jar`

## 사용 방법 요약
- `pool add <문제번호>`: 새 문제를 후보 Pool에 추가합니다.
- `todo`: 오늘 할 문제 목록을 생성/표시합니다. 목록에서 번호를 입력하면 해당 백준 문제 페이지가 브라우저로 열립니다.
- `done <문제번호>`: 문제를 완료 처리합니다.
  - Pool에서 제거
  - 리뷰 데이터에 반영(다음 복습 날짜 갱신: 1→3→7→14→30일 간격)
  - 오늘의 Todo에 완료 표시 저장
- `review list`: 오늘 복습해야 하는 문제 목록을 표시합니다.

## 자동 버전 관리 & 배포 (GitHub Actions)
메인 브랜치에 푸시되면 워크플로우가 자동 실행됩니다(`.github/workflows/main.yml`).
- `version.txt`의 패치 버전을 +1 하고 커밋/태그 생성
- Java 17로 빌드하여 JAR 산출(artifact 업로드)
- GitHub Release 생성 및 `app.jar` 업로드
- 릴리스 `app.jar`로 SHA256 계산
- Homebrew Tap 저장소(`akrap147/homebrew-akrap`)의 Formula(`algo.rb`)의 `url`/`sha256` 자동 갱신 후 푸시

## 데이터 저장소(.algo-data)
모든 데이터는 실행 디렉터리 기준의 `.algo-data/` 폴더에 JSON으로 저장됩니다. 최초 실행 시 자동 생성됩니다.

- `.algo-data/pool.json`: 풀 문제 후보 목록
- `.algo-data/review.json`: 복습 스케줄 데이터(문제번호, 단계, 다음 복습일 등)
- `.algo-data/daily-todo.json`: 날짜별 오늘 할 일 목록

주의: JAR을 다른 경로에서 실행하면 해당 경로에 새로운 `.algo-data/`가 생길 수 있습니다. 프로젝트 루트에서 실행하는 것을 권장합니다.

## 내부 구성
- CLI 프레임워크: [Picocli]
- 직렬화: Jackson(ObjectMapper, JavaTimeModule)
- 브라우저 열기: macOS `open`, Windows `rundll32`, Linux `xdg-open` 사용

패키지 개요
- `dev.akrap.algobot.App`: 엔트리 포인트(`algo` 루트 커맨드)
- `comands.pool.*`: Pool 관리 서브커맨드(add/list/remove)
- `comands.todo.*`: 오늘의 Todo 출력 및 선택/열기 처리
- `comands.done.*`: 완료 처리 로직
- `comands.review.*`: 리뷰 목록 및 스케줄 관련
- `util.*`: 파일 저장/브라우저 열기/상호작용 유틸리티
- `model.*`: Pool/Review/DailyTodo 데이터 모델

## 개발 팁
- Java 17 Toolchain을 Gradle로 지정합니다(`app/build.gradle`).
- 산출물은 shadowJar로 `app/build/libs/app.jar`에 생성됩니다.
- 시작 스크립트 생성은 비활성화되어 있으므로 JAR 실행을 사용하세요.

## 예시 출력
오늘의 Todo 예시:
```
==== TODAY TODO ====
날짜: 2025-01-01

1) 문제 1000 [미완료]
2) 문제 1010 [미완료]
3) 문제 1026 [미완료]
```

리뷰 목록 예시:
```
==== REVIEW 목록 ====
- 문제 1000 / stage=2 / next=2025-01-02  [오늘 복습]
- 문제 1010 / stage=1 / next=2025-01-03  [2025-01-03]
```

 
