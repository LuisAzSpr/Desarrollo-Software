FROM openjdk:17

# Crear y cambiar al directorio /app
WORKDIR /app

# Copiar los archivos del proyecto al contenedor
COPY ../TowerService /app

# Compilar el proyecto
RUN chmod +x ./gradlew build

# Establecer el comando predeterminado para ejecutar la aplicación
CMD ["java", "-cp", "build/classes/java/TowerService", "org.example.TowerService"]