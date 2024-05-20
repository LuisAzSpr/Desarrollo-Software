package org.example;

import java.util.HashMap;
import java.util.Map;

public class CineManagerSalas{
    private Map<Integer, Sala> salas;
    public CineManagerSalas() {
        salas = new HashMap<>();
    }
    public void agregarSala(Sala sala) {
        salas.put(sala.getId(), sala);
    }
    public boolean eliminarSala(int id) {
        return salas.remove(id) != null;
    }
    public int contarSalas() {
        return salas.size();
    }
}


