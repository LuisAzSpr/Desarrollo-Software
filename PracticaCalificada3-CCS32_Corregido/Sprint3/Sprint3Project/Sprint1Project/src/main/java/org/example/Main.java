package org.example;



import java.io.FileNotFoundException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Juego juego = new Juego(10);
    private static Graficas graficas;

    public static void main(String[] args) throws FileNotFoundException {
        graficas = new Salidas(juego);
        start();
    }
    private static void start(){
        int estado = 0;
        Scanner scanner = new Scanner(System.in);
        while(estado==0) {
            estado = juego.verificarEstado();
            graficas.mostrarLaberinto();
            System.out.println("DIRECCION : ");
            String movimiento = scanner.nextLine();
            juego.actualizarEstado(movimiento);
        }
        graficas.mostrarFinDelJuego();
    }
}

/*

String path = "Sprint1Project/src/main/java/org/example/";
Report report;
Map<String,List<String>> methodsClass = new HashMap<>();

// agregamos los metodos de Juego que implementan la logica
List<String> methodsJuego = new ArrayList<>();
methodsJuego.add("procesarComandos");
methodsJuego.add("actualizarEstado");
methodsJuego.add("verificarEstado");

// agregamos los metodos de Jugador que implementan la logica
List<String> methodsJugador = new ArrayList<>();
methodsJugador.add("moverJugador");
methodsJugador.add("caerEnTrampa");
methodsJugador.add("recogerTesoro");

// agregamos los metodos de Laberinto que implementan la logica
List<String> methodsLaberinto = new ArrayList<>();
methodsLaberinto.add("inicializarLaberinto");
methodsLaberinto.add("colcarTesorosYTrampas");
methodsLaberinto.add("actualizarCelda");

methodsClass.put("Juego",methodsJuego);
methodsClass.put("Jugador",methodsJugador);
methodsClass.put("Laberinto",methodsLaberinto);


for(String class_:methodsClass.keySet()){
    System.out.println("============ Class "+class_+" ============= ");
    List<String> methods = methodsClass.get(class_);
    report = new Report(path,class_);
    report.doReport(methods);
    report.showReport();
    System.out.println("\n");
}

 */

/*
int estado = 0;
Juego juego = new Juego(10);
Salidas salidas = new Salidas(juego);
Scanner scanner = new Scanner(System.in);
while(estado==0) {
estado = juego.verificarEstado();
salidas.mostrarLaberinto();
System.out.println("DIRECCION : ");
String movimiento = scanner.nextLine();
juego.actualizarEstado(movimiento);
}

salidas.mostrarFinDelJuego();

 */