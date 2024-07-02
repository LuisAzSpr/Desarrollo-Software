FROM openjdk:17
WORKDIR /app
COPY . /app
RUN javac Map.java Player.java Tower.java TowerDefenseGame.java Wave.java
CMD ["java","TowerDefenseGame"]