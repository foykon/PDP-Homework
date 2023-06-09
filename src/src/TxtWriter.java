package src;
/**
*
* @author Furkan Yıldız G201210014 2.öğretim/A grubu
* @since 04.04.2023
* <p>
* txtye yazmak için kullandığımmız sınıf javadoclarda ayrıntı mevcut.
* </p>
*/
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TxtWriter {
  private String javadocPath = "javadoc.txt";
  private String coksatirPath = "coksatir.txt";
  private String teksatirPath = "teksatir.txt"; 
  
  /**
   * tüm txt dosya yazma işlemlerinin aynı olan kısımlarını tek fonksiyonda topladığımız bir txt dosyasına yazma fonksiyonu.
   * kod tekrarından kaçmamızı sağlıyor.
   * @param line
   * @param path
   */
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
  
  /**
   * istedğimiz dosyaya yazdırmak için ana writer fonksiyonuna kendi dosyamızı parametre olrak gönderip javadoc için yazma işlemi yaptırıyoruz.
   * @param line
   */
  public void javaDocWriter(String line){
    writer(line,javadocPath);
  
  }
  
  /**
   * istedğimiz dosyaya yazdırmak için ana writer fonksiyonuna kendi dosyamızı parametre olrak gönderip coksatir için yazma işlemi yaptırıyoruz.
   * @param line
   */
  public void cokSatirWriter(String line){
    writer(line,coksatirPath);
  
  }
  
  /**
   * istedğimiz dosyaya yazdırmak için ana writer fonksiyonuna kendi dosyamızı parametre olrak gönderip teksatir için yazma işlemi yaptırıyoruz.
   * @param line
   */
  public void tekSatirWriter(String line){
    writer(line, teksatirPath);
    
  }
    
}
