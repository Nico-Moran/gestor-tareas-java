/*
Contexto:
Imagina que trabajas en una pequeña startup y te han pedido desarrollar un sistema simple en Java para gestionar tareas personales.
El sistema debe permitir:
--Agregar tareas con una descripción y un estado (“pendiente” o “completada”).
--Listar todas las tareas.
--Marcar una tarea como completada.
--Eliminar tareas completadas.
 */
/*
Requisitos Técnicos:
--Debe usarse una clase Tarea con atributos descripcion y estado.
--Una clase GestorTareas que almacene las tareas en un ArrayList<Tarea> y tenga métodos para cada funcionalidad.
--Un main que muestre un menú en consola para que el usuario pueda elegir qué hacer.
--Usar bucles (while, for) y condicionales (if, switch) para controlar el flujo.
--Validar que no se ingresen descripciones vacías.
 */



import java.util.ArrayList;
import java.util.Scanner;

public class SistemaTareas {

    private static Scanner scanner = new Scanner(System.in);
    private static GestorTarea gestorTarea = new GestorTarea();
    
    public static void main(String[] args) {                            //FUNCION PRINCIPAL

    mostrarMenu();    
    }

    private static void mostrarMenu(){                                  //FUNCION MENU
    int opcion = 0;
    while (opcion!=5) {
    System.out.println("[--------------------------------------------------------]");
    System.out.println("Bienvenido al Sistema de Gestion de Tareas Personales.");
    System.out.println("1] Ingresar una nueva tarea junto a su descripcion y estado.");
    System.out.println("2] Marcar una tarea completada.");
    System.out.println("3] Eliminar las tareas completadas.");
    System.out.println("4] Mostrar todas las tareas.");
    System.out.println("5] Salir.");
    System.out.println("Ingresar una de las opciones: ");
    opcion = scanner.nextInt();
    scanner.nextLine();

    switch (opcion) {
        case 1: 
            cargarTarea();
            break;
        case 2:
            marcarTareaIncompleta();
            break;
        case 3:
            eliminarTareaCompleta();
            break;
        case 4:
            mostrarTareas();
            break;
        case 5:
            System.out.println("Hasta luego!");
        default:
            break;
        }   
     }
    }

    private static void cargarTarea(){                              //FUNCION CARGAR TAREA

        System.out.println("Ingresar el Nombre de la tarea: ");
        String nombre = scanner.nextLine();

        String descripcion ="";
        while (descripcion.trim().isEmpty()) {
        System.out.println("Ingresar la descripcion de la tarea: ");
        descripcion = scanner.nextLine();
        if(descripcion.trim().isEmpty()){
            System.out.println("La descripcion no puede estar vacia.");
            }
        }
        
        int estado = 3;
        while (estado !=0 && estado != 1) {
            System.out.println("Ingresar el estado de la tarea: (Pendiente=0/Completada=1): ");
            estado =scanner.nextInt();
            scanner.nextLine();
            if (estado !=0 && estado !=1) {
                System.out.println("El ingreso debe ser = Pendiente=0 o Completada=1.");
            }
        }
         
        
        Tarea nuevaTarea = new Tarea(gestorTarea.obtenerTareas().size()+1,nombre,descripcion,estado);
        gestorTarea.agregarTarea(nuevaTarea);
        System.out.println("Se agrego una nueva tarea.");

        System.out.println("Tarea agregada con el ID: "+ nuevaTarea.getId() );
    }
    private static void marcarTareaIncompleta(){                    //FUNCION MARCARR TAREA INCOMPLETA
        if(gestorTarea.obtenerTareas().isEmpty()){
            System.out.println("No hay ninguna tarea registrada.");
            return;
        }
        System.out.println("Las tareas incompletas son: ");
        for (Tarea tarea : gestorTarea.obtenerTareasIncompletas()) {
        System.out.println(tarea);
    }

        System.out.println("Ingresar el id de la tarea a modificar: ");
        int modificar = scanner.nextInt();
        scanner.nextLine();
        boolean tareaEncontrada = gestorTarea.marcarTareaIncompleta(modificar);
        if (tareaEncontrada) {
            System.out.println("Se modifico la tarea como completada.");
        }else{
            System.out.println("No se encontro la tarea.");
        }
    }
    private static void eliminarTareaCompleta(){                    // FUNCION ELIMINAR TODAS LAS TAREAS COMPLETAS
        if(gestorTarea.obtenerTareas().isEmpty()){
            System.out.println("No hay tareas ingresadas.");
            return;   
        }
        boolean tareaCompleta = false;
        for(Tarea tarea: gestorTarea.obtenerTareas()){
            if(tarea.getEstado()==1){
                tareaCompleta=true;
                break;
            }
        }
        if (!tareaCompleta) {
            System.out.println("No se encuentra ninguna tarea completa.");
        }else{
            gestorTarea.eliminarTareasCompletadas();
            System.out.println("Se elimino las tareas completadas.");
        }
    }
    private static void mostrarTareas(){                            //FUNCION MOSTRAR TODAS LAS TAREAS
        if(gestorTarea.obtenerTareas().isEmpty()){
            System.out.println("No hay tareas ingresadas.");
            return;
        }
        ArrayList<Tarea> tareas = gestorTarea.obtenerTareas();
        for (Tarea tarea : tareas) {
            System.out.println(tarea);
        }
    }
}

