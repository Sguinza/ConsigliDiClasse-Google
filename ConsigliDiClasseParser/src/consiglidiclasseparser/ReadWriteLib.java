/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package consiglidiclasseparser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maspes_marco
 */
public class ReadWriteLib {
    String percorsoFileLettura;
    String percorsoFileScrittura;
    
    /**
     *
     * @author maspes_marco
     * @brief il metodo inizializza la classe
     */
    public ReadWriteLib()
    {}
    
    /**
     *
     * @author maspes_marco
     * @brief il metodo permettere di settare il percosro da cui leggere il file
     */
    public ReadWriteLib(String percorsoFileLettura, String percorsoFileScrittura)
    {
        this.percorsoFileLettura = percorsoFileLettura;
        this.percorsoFileScrittura = percorsoFileScrittura;
    }
    
    /**
     *
     * @author maspes_marco
     * @brief il metodo permettere di settare il percorso da cui leggere il file
     */
    public void setPLettura(String percorsoFileLettura)
    {
        this.percorsoFileLettura = percorsoFileLettura;
    }
    
    /**
     *
     * @author maspes_marco
     * @brief il metodo permettere di settare il percorso in cui scrivere il file
     */
    public void setPScrittura(String percorsoFileScrittura)
    {
        this.percorsoFileScrittura = percorsoFileScrittura;
    }
    
    /**
     *
     * @author maspes_marco
     * @brief il metodo legge un file dagli archivi e permette di scriverlo in un pacchetto da inviare
     */
    public String readFile()
    {
        FileReader fr;
        BufferedReader br;
        String testo = "";
        int contatore = 0;
        
        try
        {
            fr = new FileReader(percorsoFileLettura);
            br = new BufferedReader(fr);
            
            String lineaLetta = "";
            
            while(true) {
                lineaLetta = br.readLine();
                if(lineaLetta==null)
                    break;
                else
                    contatore ++;
                
                testo += "&" + lineaLetta;
            }
            
            br.close();
        }catch(Exception ex)
        {}
        
        String restituzione = String.valueOf(contatore);
        restituzione += testo;
        
        return restituzione;
    }
    
    /**
     *
     * @author maspes_marco
     * @brief il metodo permette di scrivere un file che è stato ricevuto
     */
    public void writeFile(String ricevuto)
    {
        FileWriter fw;
        BufferedWriter bw;
        
        try {
            fw = new FileWriter(percorsoFileScrittura);
            bw = new BufferedWriter(fw);
            
            String [] vettore = ricevuto.split("&");
            
            for(int i=0; i< Integer.parseInt(vettore[0]); i++)
                bw.write(vettore[i+1] + "\r\n");
            
            System.out.println("FILE RICEVUTO");
            
            bw.close();
        } catch (IOException ex) { }
    }
    
    /**
     * @author maspes_marco
     * @brief metodo che permette di ottenere tutti i file e cartelle presenti all'interno
     * di una directory
     * @param pathName parametro che indica la directory entro cui cercare i file
     * @return restituisce una stringa contenente tutti i file/cartelle presenti
     */
    public String getAll(String pathName)
    {
        String str = "";
        File directory = new File(pathName);
        File[] fList = directory.listFiles();
        
        if(fList.length == 0)
            str += "0";
        else
        {
            str += fList.length;
            for (File file : fList)
            {
                if(file.isFile())
                {
                    percorsoFileLettura = file.getPath();
                    str += "!" + file.getName() + "@" + "file" + "!";
                    str += readFile();
                }
                else if(file.isDirectory())
                {
                    str += "!" + file.getName() + "@" + "directory";
                }
            }
        }
        
        return str;
    }
    
    /**
     * @author maspes_marco
     * @brief il metodo permette riscirvere tutti i file/directories ottenuti dal server
     */
    public void getRiscrittura(String ricevuto)
    {
        String[] split = ricevuto.split("!");
        
        if(split.length == 1)
            System.out.println("Non sono presenti file o cartelle");
        else
        {
            for(int i=1; i< Integer.parseInt(split[0])*2; i++)
            {
                String [] nomeTipo = split[i].split("@");
                
                if(nomeTipo[1].equals("file"))
                {
                    percorsoFileScrittura = "Archivi\\" + nomeTipo[0];
                    writeFile(split[i+1]);
                    i++;
                }
                else if(nomeTipo[1].equals("directory"))
                {
                    new File("Archivi\\" + nomeTipo[0]).mkdir();
                }
            }
        }
    }
    
    /**
     * @author maspes_marco
     * @brief il metodo permette ottenere tutti i file/directories presenti in una cartella scelta dall'utente
     */
    public String dirAll(String pathName)
    {
        String str = "";
        File directory = new File(pathName);
        File[] fList = directory.listFiles();
        
        if(fList.length == 0)
            str += "0";
        else
        {
            str += fList.length;
            for (File file : fList)
            {
                if(file.isFile())
                {
                    str += "&" + file.getName() + "@" + "file";
                }
                else if(file.isDirectory())
                {
                    str += "&" + file.getName() + "@" + "directory";
                }
            }
        }
        
        return str;
    }
    
    public String dirRiscrittura(String ricevuto)
    {
        String str = "";
        String[] split = ricevuto.split("&");
        
        if(split.length == 1)
            System.out.println("Non sono presenti file o cartelle");
        else
        {
            for(int i=1; i<= Integer.parseInt(split[0]); i++)
            {
                String [] nomeTipo = split[i].split("@");
                
                str += nomeTipo[0] + "," + nomeTipo[1] + "\r\n";
            }
        }
        
        return str;
    }
}
