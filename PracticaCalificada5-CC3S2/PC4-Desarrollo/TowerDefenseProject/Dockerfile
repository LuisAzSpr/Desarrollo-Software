FROM openjdk:17

WORKDIR /app

COPY . /app

RUN chmod +x ./gradlew build

CMD ["java", "-cp","build/classes/java/main", "towerdefense.TowerDefenseGame"]


