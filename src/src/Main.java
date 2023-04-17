package src;
/**
*
* @author Furkan Yıldız G201210014 2.öğretim/A grubu
* @since 03.04.2023
* <p>
* verilen dosyayı list formatında linecontrol sınıfına ilettiğimiz main
* </p>
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        //dışardan alınan dosyalarını tek tek okuyup arraylist içinde biriktiriyoruz
        //java -jar dist\Program.jar Motor.java
        // if (args.length == 0) {
        //     System.out.println("Bir dosya yolu girmediniz.");
        //     System.exit(0);
        // }
        // 
        // System.out.println("dosyanın adı: " + filePath);
        String filePath = "Deneme.java";
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            ArrayList<String> lines = new ArrayList<String>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            
            }
            LineControl lineController = new LineControl(lines,new TxtWriter());
            lineController.findFunctionsAndSpiltTheirComments();
            bufferedReader.close();
        
        } catch (IOException e) {
            System.out.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        
        }
    
    }

}
