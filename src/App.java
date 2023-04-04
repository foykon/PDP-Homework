/**
*
* @author Furkan Yıldız G201210014 2.öğretim/A grubu
* @since 03.04.2023
* <p>
* verilen dosyayı list formatında linecontrol sınıfına ilettiğimiz main
* </p>
*/
import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        //dışardan alınan dosyalarını tek tek okuyup arraylist içinde biriktiriyoruz
        try {
            FileReader fileReader = new FileReader("C:\\Users\\foyko\\Desktop\\Motor.java");
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
