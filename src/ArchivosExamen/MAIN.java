package ArchivosExamen;

import java.util.Scanner;
import java.io.IOException;

/**
 *
 * @author ArturoRendon
 */
public class MAIN {
    
    public static void main(String[] args){
        
        int Opcion = 0;
        
        Equipos e = new Equipos();
        Jugadores j = new Jugadores();
        
        Scanner leer = new Scanner(System.in);
        
        try{
            
            do{
                
                System.out.println("--- L I G A  N A C I O N A L ---");
                System.out.println("1. Agregar equipo");
                System.out.println("2. Modificar capacidad del estadio");
                System.out.println("3. Modificar nombre del equipo");
                System.out.println("4. Modificar ciudad del equipo");
                System.out.println("5. Eliminar equipo");
                System.out.println("6. Agregar jugador a un equipo");
                System.out.println("7. Reportes");
                System.out.println("8. Salir");
                System.out.print("Opcion deseada: ");
                Opcion = leer.nextInt();
                
                switch(Opcion){
                    
                    case 1:
                        
                        System.out.println("-AGREGAR EQUIPO-");
                        System.out.print("Nombre equipo: ");
                        String NombreEquipo = leer.next();
                        System.out.print("Ciudad donde esta el equipo: ");
                        String Ciudad = leer.next();
                        System.out.print("Capacidad del estadio: ");
                        int Capacidad = leer.nextInt();
                        
                        e.addTeam(NombreEquipo, Ciudad, Capacidad);
                        
                        break;
                        
                    case 2:
                        
                        System.out.println("-MODIFICAR CAPACIDAD DEL EQUIPO-");
                        System.out.print("Codigo del equipo: ");
                        int Codigo = leer.nextInt();
                        System.out.print("Capacidad nueva del equipo: ");
                        int CapacidadNueva = leer.nextInt();
                        
                        if (e.modCapacidadEstadio(Codigo, CapacidadNueva)) {
                            
                            System.out.println("Se modifico la capacidad del equipo . . .");
                            
                        }
                        
                        break;
                        
                    case 3:
                        break;
                        
                    case 4:
                        break;
                        
                    case 5:
                        break;
                        
                    case 6:
                        
                        System.out.println("-AGREGAR JUGADOR A UN EQUIPO-");
                        System.out.print("Codigo del equipo: ");
                        int CodigoEquipo = leer.nextInt();
                        System.out.print("Nombre del jugador: ");
                        String NombreJugador = leer.next();
                        System.out.print("Dorsal: ");
                        int Dorsal = leer.nextInt();
                        System.out.print("Posicion: ");
                        String Pos = leer.next();
                        System.out.print("Edad: ");
                        int Edad = leer.nextInt();
                        System.out.print("Nacionalidad: ");
                        String Nacionalidad = leer.next();
                        
                        if (j.addJugador(CodigoEquipo, NombreJugador, Dorsal, Pos, Edad, Nacionalidad)) {
                            
                            System.out.println("Se agrego el jugador "+NombreJugador+" al equipo "+e.getNombreEquipo(CodigoEquipo));
                            
                        }
                        
                        break;
                        
                    case 7:
                        break;
                    
                }
                
            }while(Opcion != 8);
            
        }catch(IOException ex){
            
            System.out.println(ex.getMessage());
        }
        
    }
    
}
