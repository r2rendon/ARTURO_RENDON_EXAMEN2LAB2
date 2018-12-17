package ArchivosExamen;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author ArturoRendon
 */
public class Equipos {
    
    /*
    
        Formato de archivo 
        int Codigo (Es autogenerado)
        String NombreEquipo
        String Ciudad del equipo
        int Capacidad del estadio
        String EstadoDelEquipo
    
    */
    
    RandomAccessFile fe, codes;
    private static final long CODES_OFFSET = 0;
    
    public Equipos(){
        
        try{
            
            new File("Liga Nacional").mkdirs();
            fe = new RandomAccessFile("Liga Nacional/Equipos.lgn", "rw");
            codes = new RandomAccessFile("Liga Nacional/CodigosEquipos.lgn", "rw");
            initCodes();
            
        }catch(IOException e){
            
            e.getMessage();
            
        }
        
    }
    
    private void initCodes()throws IOException{
        
        if (codes.length() == 0) {
            codes.writeInt(1);
        }
        
    }
    
    private int getCode(long offset)throws IOException{
        
        codes.seek(offset);
        int Codigo = codes.readInt();
        codes.seek(offset);
        codes.writeInt(Codigo+1);
        return Codigo;
        
    }
    
    void addTeam(String NombreEquipo, String CiudadEquipo, int CapacidadEstadio)throws IOException{
        
        fe.seek(fe.length());
        
        //Codigo Autogenerado
        fe.writeInt(getCode(CODES_OFFSET));
        //Nombre del equipo
        fe.writeUTF(NombreEquipo);
        //Ciudad del equipo
        fe.writeUTF(CiudadEquipo);
        //Capacidad del estadio
        fe.writeInt(CapacidadEstadio);
        //El estado del equipo por default es true
        fe.writeBoolean(true);
        
    }
    
    boolean modCapacidadEstadio(int CodigoTeam, int NuevaCapacidad)throws IOException{
        
        fe.seek(0);
        while(fe.getFilePointer() < fe.length()){
            
            int Codigo = fe.readInt();
            if (CodigoTeam == Codigo) {
                
                fe.readUTF();
                fe.readUTF();
                fe.writeInt(NuevaCapacidad);
                fe.readBoolean();
                return true;
                
            }
            
            fe.readUTF();
            fe.readUTF();
            fe.readInt();
            fe.readBoolean();
            
        }
        
        return false;
        
    }
    
    boolean equipoExists(int Codigo)throws IOException{
        
        fe.seek(0);
        while(fe.getFilePointer() < fe.length()){
            
            int CodigoE = fe.readInt();
            
            if (CodigoE == Codigo) {
                
                return true;
                
            }
            
            fe.readUTF();
            fe.readUTF();
            fe.readInt();
            fe.readBoolean();
            
        }
        
        return false;
        
    }
    
    String getNombreEquipo(int Codigo)throws IOException{
        
        fe.seek(0);
        
        String NombreE = "";
        
        while(fe.getFilePointer() < fe.length()){
            
            int CodigoE = fe.readInt();
            if (Codigo == CodigoE) {
                
                NombreE = fe.readUTF();
                
            }
            
            fe.readUTF();
            fe.readUTF();
            fe.readInt();
            fe.readBoolean();
            
        }
        
        return NombreE;
        
    }
    
    public void ListarEquipos()throws IOException{
        Jugadores j = new Jugadores();
        fe.seek(0);
        while(fe.getFilePointer() < fe.length()){
        
            int codigo = fe.readInt();
            String nombre = fe.readUTF();
            String ciudad = fe.readUTF();
            int capacidad = fe.readInt();
            if(fe.readBoolean()){
                System.out.println("---- L I S T A D O  D E  E Q U I P O S ----");
                System.out.println("Codigo: " + codigo);
                System.out.println("Nombre: " + nombre);
                System.out.println("Ciudad: " + ciudad);
                System.out.println("Capacidad: "+ capacidad);
                int cantidad = j.cantidadJugadores(codigo);
                System.out.println("Cantidad Jugadores : "+cantidad);
            }
            
        }
       
   }
    
     public boolean Eliminar(int codigo)throws IOException{
        if(equipoExists(codigo)){
            fe.readUTF();
            fe.readUTF();
            fe.readInt();
            fe.writeBoolean(false);
            return true;
        }
        return false;
    }
     
     public boolean VerificarEliminado(int codigo)throws IOException{
        if(equipoExists(codigo)){
            fe.readUTF();
            fe.readUTF();
            fe.readInt();
            boolean estado = fe.readBoolean();
            if(estado){
            return true;
            }
        }
        return false;
    }
    
}
