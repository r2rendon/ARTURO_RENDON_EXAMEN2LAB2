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
                
                System.out.println("\n\n--- L I G A  N A C I O N A L ---");
                System.out.println("1. Agregar equipo");
                System.out.println("2. Modificar equipo");
                System.out.println("3. Listar equipos");
                System.out.println("4. Listar Jugadores");
                System.out.println("5. Eliminar equipo");
                System.out.println("6. Agregar jugador a un equipo");
                System.out.println("7. Jugador por posicion");
                System.out.println("8. Eliminar equipo");
                System.out.println(". Salir");
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
                        
                        System.out.println("-MODIFICAR CAPACIDAD DEL ESTADIO DEL EQUIPO-");
                        System.out.print("Codigo del equipo: ");
                        int Codigo = leer.nextInt();
                        System.out.print("Capacidad nueva del estadio: ");
                        int CapacidadNueva = leer.nextInt();
                        
                        if (e.modCapacidadEstadio(Codigo, CapacidadNueva)) {
                            
                            System.out.println("Se modifico la capacidad del estadio del equipo . . .");
                            
                        }
                        
                        break;
                        
                    case 3:
                        
                        e.ListarEquipos();
                        
                        break;
                        
                    case 4:
                        
                        System.out.println("---- L I S T A D O  D E  J U G A D O R E S ----");
                        System.out.print("Ingrese el codigo del equipo: ");
                        int CodigoE = leer.nextInt();
                        j.iprimirJugadores(CodigoE);
                        
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
                        
                        System.out.println("---- J U G A D O R  P O R  P O S I C I O N ----");
                        
                        System.out.print("Ingrese la posición de los jugadores a mostrar: ");
                        Pos = leer.next();
                        
                        j.posicion(Pos);
                        
                        break;
                        
                    case 8:
                        
                        System.out.println("---- E L I M I N A R  E Q U I P O ---");
                        
                        System.out.print("Ingrese el codigo del equipo a eliminar: ");
                        CodigoE = leer.nextInt();
                        
                        e.Eliminar(CodigoE);
                        
                        break;
                    
                }
                
            }while(Opcion != 9);
            
        }catch(IOException ex){
            
            System.out.println(ex.getMessage());
        }
        
    }
    
}
