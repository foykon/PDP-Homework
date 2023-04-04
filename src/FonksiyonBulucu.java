import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FonksiyonBulucu {
    private String fonksiyonIsmi=""; 
    private int singleLineComments = 0;
    private int multiLineComments = 0;
    private int javadocComments = 0;
    private boolean insideMultiLineComment = false;
    private boolean insideJavadocComment = false;
    private boolean firstTime=true;

    

    private ArrayList<String> satirlar;
    private TxtWriter txtWriter;

    public FonksiyonBulucu(ArrayList<String> satirlar,TxtWriter txtWriter) {
        this.satirlar = satirlar;
        this.txtWriter=txtWriter;
    }

    public void fonksiyonlariAnalizEt() {

        String  fonksiyonRegex = "^\\s*(public|private|protected)?\\s+(static\\s+)?(final\\s+)?(synchronized\\s+)?(\\w+\\s+)?\\w+\\s*\\(.*\\)\\s*((throws\\s+\\w+(\\s*,\\s*\\w+)*)?\\s*)*\\{";
        Pattern pattern = Pattern.compile(fonksiyonRegex);

        for (String satir : satirlar) {
            Matcher matcher = pattern.matcher(satir);
            if (matcher.find()) {
                if(!firstTime){
                    yorumSayisiYazdir();
                }
                
                firstTime=false;
                fonksiyonIsmi = satir.substring(satir.indexOf(' ') + 1, satir.indexOf('('));
                
                fonksiyonIsimYazdir();
                
            }
            countComments(satir);
        }
        yorumSayisiYazdir();
        // System.out.println("Toplam fonksiyon sayısı: " + fonksiyonSayisi);
        // System.out.println("Fonksiyon isimleri: " + fonksiyonIsimleri);
    }

    public void countComments(String line) {
        line = line.trim(); // boşlukları kaldır
        //javadoc
        if (line.contains("/**")) { // başlangıç javadoc yorumu
            if (line.contains("*/")) { // tek satırlık bir javadoc yorumu
                txtWriter.javaDocWriter(line);
                javadocComments++;
            } else {
                txtWriter.javaDocWriter(line);
                insideJavadocComment = true;
            }
        } else if (line.contains("*/") && insideJavadocComment) { // javadoc yorumunun sonu
            txtWriter.javaDocWriter(line);
            insideJavadocComment = false;
            javadocComments++;
        } else if (line.startsWith("*") && insideJavadocComment) { // javadoc yorumunun ortası
            txtWriter.javaDocWriter(line);
            
        //çoklu satır
        } else if (line.contains("/*")) { // başlangıç çoklu satırlı yorum
            if (line.contains("*/")) { // tek satırlık bir yorum
                multiLineComments++;
            } else {
                insideMultiLineComment = true;
            }
        } else if (line.contains("*/") && insideMultiLineComment) { // çoklu satırlı yorumun sonu
            insideMultiLineComment = false;
            multiLineComments++;
        } else if (line.startsWith("*") && insideMultiLineComment) { // çoklu satırlı yorumun ortası
            
        //tekli satır
        } else if (line.contains("//")) { // tek satırlı yorum
            singleLineComments++;
        }
        
    }

    public void yorumSayisiYazdir(){
        System.out.println("Tek satırlı yorum sayısı: " + singleLineComments);
        System.out.println("Çoklu satırlı yorum sayısı: " + multiLineComments);
        System.out.println("Javadoc yorum sayısı: " + javadocComments);
        System.out.println("****************************************************************");
        sifirla();
    }

    public void fonksiyonIsimYazdir(){
        System.out.println(fonksiyonIsmi);
        System.out.println("--------------");
    }

    public void sifirla(){
        fonksiyonIsmi="";
        singleLineComments = multiLineComments = javadocComments = 0;
        
    }
    
}

