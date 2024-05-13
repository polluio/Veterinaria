package Veterinaria;

import java.io.*;

/*autor: Carlos Vozmediano*/

public class MiObjectInputStream extends ObjectInputStream {
   public MiObjectInputStream(InputStream in) throws IOException{
       super(in);
   }
   protected void readStreamHeader() throws IOException{

   }
}