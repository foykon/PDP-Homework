import java.io.*;

public class App {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\foyko\\Desktop\\Motor.java");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String satir;
            while ((satir = bufferedReader.readLine()) != null) {
                System.out.println(satir);
            }

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        }
    }
}
