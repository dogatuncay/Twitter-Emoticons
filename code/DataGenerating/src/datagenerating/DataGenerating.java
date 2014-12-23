/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datagenerating;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author coli
 */
public class DataGenerating {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // TODO code application logic here
        PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
        for(int i = 0; i < 347; i ++){
            writer.println("@attribute " + i + "{0, 1}" );
        }
        writer.close();
        
    }
    
}
