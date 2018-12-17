package ArchivosExamen;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.File;

/**
 *
 * @author ArturoRendon
 */
public class Jugadores {
    
    /*
    
        FORMATO DEL ARCHIVO
        
        Codigo del equipo al que pertenecen
        Nombre del jugador
        Dorsal
        Posicion del jugador
        Edad
        Nacionalidad
    
    */
    
    RandomAccessFile fj;
    
    Equipos e = new Equipos();
    
    public Jugadores(){
        
        try{
            
            new File("Liga Nacional/Jugadores");
            fj = new RandomAccessFile("Liga Nacional/Jugadores/Jugadores.lgn", "rw");
            
        }catch(IOException e){
            
            System.out.println(e.getMessage());
            
        }
        
    }
    
    boolean addJugador(int CodigoE, String Nombre, int Dorsal, String PosJ, int Edad, String Nacionalidad)throws IOException{
        
        fj.seek(fj.length());
        
        if (e.equipoExists(CodigoE)) {
            
            //Codigo
            fj.writeInt(CodigoE);
            //Nombre del jugador
            fj.writeUTF(Nombre);
            //Dorsal
            fj.writeInt(Dorsal);
            //String PosJ
            fj.writeUTF(PosJ);
            //Edad
            fj.writeInt(Edad);
            //Nacionalidad
            fj.writeUTF(Nacionalidad);
            
            return true;
            
        }
        
        return false;
        
    }
    
    public int cantidadJugadores(int codigo)throws IOException{
        int i =0;
        fj.seek(0);
        while(fj.getFilePointer() < fj.length()){
                int cod = fj.readInt();
                fj.readUTF();
                fj.readInt();
                fj.readUTF();
                fj.readInt();
                fj.readUTF();
                if(cod == codigo){
                i++;
            }
        } 
        return i;
    }
    
    public void iprimirJugadores(int codigo)throws IOException{
        Equipos e = new Equipos();
        
        fj.seek(0);
        int i=0;
        
        while(fj.getFilePointer() < fj.length()){
                int Codigo = fj.readInt();
                String Nombre = fj.readUTF();
                int Dorsal = fj.readInt();
                String Posicion  = fj.readUTF();
                int Edad = fj.readInt();
                String Nacionalidad = fj.readUTF();
                boolean b = e.VerificarEliminado(Codigo);
                if(b && Codigo == codigo){
                    i++;
                    System.out.println("-----------------------------");
                    System.out.println("Jugador"+i);
                    System.out.println("Codigo del Equipo: "+Codigo);
                    System.out.println("Nombre: "+Nombre);
                    System.out.println("Nacionalidad: "+Nacionalidad);
                    System.out.println("Posicion: "+Posicion);
                    System.out.println("Edad: "+Edad);
                    System.out.println("Dorsal: "+Dorsal);   
                }
        } 
    }
    
    public void posicion(String pos)throws IOException{
        Equipos e = new Equipos();
        fj.seek(0);
        while(fj.getFilePointer() < fj.length()){
            int codigo = fj.readInt();
            String Nombre = fj.readUTF();
            int dorsal = fj.readInt();
            String Pos = fj.readUTF();
            fj.readInt();
            fj.readUTF();
                
            if(e.VerificarEliminado(codigo) && pos.equals(Pos)){
                
                System.out.println("------------------");
                System.out.println("Nombre : " + Nombre);
                System.out.println("Dorsal: "+ dorsal);
                
            }
        } 
    }
    
}
