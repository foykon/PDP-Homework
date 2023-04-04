import java.io.*;

public class TxtWriter {
    
  public void javaDocWriter(String line){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("javadoc.txt",true));
            writer.write(line);
            writer.newLine();
            writer.close();
            System.out.println("Dosya yazıldı."+line);
          } catch (IOException e) {
            System.out.println("Bir hata oluştu.");
            e.printStackTrace();
          }
    }
    public void cokSatirWriter(String line){
      try {
          BufferedWriter writer = new BufferedWriter(new FileWriter("coksatir.txt",true));
          writer.write(line);
          writer.newLine();
          writer.close();
          System.out.println("Dosya yazıldı."+line);
        } catch (IOException e) {
          System.out.println("Bir hata oluştu.");
          e.printStackTrace();
        }
  }
  public void tekSatirWriter(String line){
    try {
        BufferedWriter writer = new BufferedWriter(new FileWriter("teksatir.txt",true));
        writer.write(line);
        writer.newLine();
        writer.close();
        System.out.println("Dosya yazıldı."+line);
      } catch (IOException e) {
        System.out.println("Bir hata oluştu.");
        e.printStackTrace();
      }
}
    
}
