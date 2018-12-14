package ArchivosExamen;

import java.io.RandomAccessFile;
import java.io.IOException;
import java.io.File;

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
        
    }
    
    boolean modCapacidadEstadio(int CodigoTeam, int NuevaCapacidad)throws IOException{
        
        fe.seek(0);
        while(fe.getFilePointer() < fe.length()){
            
            int Codigo = fe.readInt();
            if (CodigoTeam == Codigo) {
                
                fe.readUTF();
                fe.readUTF();
                fe.writeInt(NuevaCapacidad);
                return true;
                
            }
            
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
            
        }
        
        return NombreE;
        
    }
    
}
