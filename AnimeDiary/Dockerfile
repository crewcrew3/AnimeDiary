# Используем базовый образ с Java (JDK 17, можно сменить)
FROM eclipse-temurin:21-jdk-jammy

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем собранный jar-файл в контейнер
COPY build/libs/AnimeDiary-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Указываем команду для запуска приложения
ENTRYPOINT ["java", "-jar", "app.jar"]
