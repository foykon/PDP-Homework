import java.io.*;

public class TxtWriter {
  private String javadocPath = "javadoc.txt";
  private String coksatirPath = "coksatir.txt";
  private String teksatirPath = "teksatir.txt"; 
  
  public void writer(String line,String path){
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(path,true));
      writer.write(line);
      writer.newLine();
      writer.close();
      //System.out.println("Dosya yazıldı."+line);
    } catch (IOException e) {
      System.out.println("Bir hata oluştu.");
      e.printStackTrace();
    }
  
  }
  
  public void javaDocWriter(String line){
    writer(line,javadocPath);
  
  }
  
  public void cokSatirWriter(String line){
    writer(line,coksatirPath);
  
  }
  
  public void tekSatirWriter(String line){
  
    writer(line, teksatirPath);
  }
    
}
