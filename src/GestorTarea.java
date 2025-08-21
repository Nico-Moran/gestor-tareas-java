

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorTarea {
   
    private ArrayList<Tarea> tareas;

   
    public GestorTarea() {
        this.tareas = new ArrayList<>();
        cargarTareasDelArchivo();
    }

    public void agregarTarea(Tarea tarea) {
        tareas.add(tarea);
        guardarTareasEnArchivo();
    }

   
    public boolean eliminarTareaPorId(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id) {
                tareas.remove(tarea);
                guardarTareasEnArchivo();
                return true;  
            }
        }
        return false;  
    }

    
    public boolean marcarTareaIncompleta(int id) {
        for (Tarea tarea : tareas) {
            if (tarea.getId() == id && tarea.getEstado() == 0) {
                tarea.setEstado(1);  
                guardarTareasEnArchivo();
                return true;
            }
        }
        return false;  
    }

    
    public void eliminarTareasCompletadas() {
        tareas.removeIf(tarea -> tarea.getEstado() == 1);
        guardarTareasEnArchivo();
    }

    
    public ArrayList<Tarea> obtenerTareas() {
        return tareas;
    }

    
    public ArrayList<Tarea> obtenerTareasIncompletas() {
        ArrayList<Tarea> incompletas = new ArrayList<>();
        for (Tarea tarea : tareas) {
            if (tarea.getEstado() == 0) {
                incompletas.add(tarea);
            }
        }
        return incompletas;
    }

    // FUNCIONES DE ARCHIVO //

    private void guardarTareasEnArchivo() { //GUARDAR
        try (BufferedWriter cargar = new BufferedWriter(new FileWriter("tareas.txt"))) {
            for (Tarea tarea : tareas) {
                cargar.write(tarea.getId() + "," + tarea.getNombre() + "," + tarea.getDescripcion() + "," + tarea.getEstado());
                cargar.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las tareas en el archivo: " + e.getMessage());
        }
    }
    private void cargarTareasDelArchivo(){
        try(BufferedReader leer = new BufferedReader(new FileReader("tareas.txt"))){
            String line;
            while((line = leer.readLine()) != null){
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String nombre = parts[1];
                String descripcion = parts[2];
                int estado = Integer.parseInt(parts[3]);

                Tarea tarea = new Tarea(id,nombre,descripcion,estado);
                tareas.add(tarea);
            }
        }
        catch(IOException e){
            System.out.println("Error al cargar el archivo de texto." + e.getMessage());
        }  
    }
}

