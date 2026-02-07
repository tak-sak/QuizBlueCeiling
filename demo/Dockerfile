# 1. ビルド用の環境（Java 17）を用意
FROM eclipse-temurin:17-jdk-jammy as build
COPY . .
# Mavenを使ってビルド（実行ファイルを作成）
RUN ./mvnw clean install -DskipTests

# 2. 実行用の軽量環境を用意
FROM eclipse-temurin:17-jre-jammy
# ビルドした実行ファイル（jar）をコピー
COPY --from=build /target/*.jar app.jar
# アプリを起動
ENTRYPOINT ["java","-jar","/app.jar"]