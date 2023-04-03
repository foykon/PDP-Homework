import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\foyko\\Desktop\\Motor.java");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<String> satirlar = new ArrayList<String>();
            String satir;
            while ((satir = bufferedReader.readLine()) != null) {
                satirlar.add(satir);
            }
            for (String satir1 : satirlar) {
                System.out.println(satir1);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        }
    }
}
