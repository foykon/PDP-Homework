import java.io.*;

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
