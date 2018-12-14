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
            //Nombre equipo
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
    
    
    
}
