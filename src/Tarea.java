

public class Tarea {
    private int id;
    private String nombre;
    private String descripcion;
    private int estado; // Pendiente, Terminada;

    public Tarea(int id,String nombre,String descripcion,int estado){
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.estado=estado;
    }
    public int getId(){
        return id;
    }
    public String getNombre(){
        return nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public int getEstado(){
        return estado;
    }
    public void setEstado(int estado){
        this.estado=estado;
    }
    @Override
    public String toString(){
      if(estado == 0){
         return id + "] Nombre: " + nombre + " - Descripcion: " + descripcion + " - Estado: Pendiente";
      }else{
        return id + "] Nombre: " + nombre + " - Descripcion: " + descripcion + " - Estado: Completada";
      }
        
    }
}
