package permanencia;

import java.io.*;
import java.util.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

public class JsonManager {
    
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public static <T> void guardarLista(String nombreArchivo, List<T> lista) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> cargarLista(String nombreArchivo, Class<T> clase) {
        try (FileReader reader = new FileReader(nombreArchivo)) {
            return gson.fromJson(reader, TypeToken.getParameterized(List.class, clase).getType());
        } catch (IOException e) {
            return new ArrayList<>(); // Si no existe, devuelve lista vacía
        }
    }
}
