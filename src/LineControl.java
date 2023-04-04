/**
*
* @author Furkan Yıldız G201210014 2.öğretim/A grubu
* @since 03.04.2023
* <p>
* mainden aldığı satırların içinde yorum varsa yorumarı çeşitlerine ayıran ve fonksiyonun ismini bulan class. zaten gerekli tüm bilgiler fonksiyonların javadoclarında bulunmakta
* </p>
*/
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineControl {
    private String  regexOfFunc = "^\\s*(public|private|protected)?\\s+(static\\s+)?(final\\s+)?(synchronized\\s+)?(\\w+\\s+)?\\w+\\s*\\(.*\\)\\s*((throws\\s+\\w+(\\s*,\\s*\\w+)*)?\\s*)*\\{";
    private String functionName=""; 
    private int singleLineComments = 0;
    private int multiLineComments = 0;
    private int javadocComments = 0;
    private boolean insideMultiLineComment = false;
    private boolean insideJavadocComment = false;
    private boolean firstTime=true;

    private boolean insideFunction = true;
    private int numberOfBrace = 0;

    private ArrayList<String> lines;
    private TxtWriter txtWriter;

    public LineControl(ArrayList<String> lines,TxtWriter txtWriter) {
        this.lines = lines;
        this.txtWriter=txtWriter;
    }

    /**
     * Önce regex ile fonksiyon olup olmadığını kontrol ediyor.
     * Bulduktan sonra "splitFunctionName" fonksiyonunu çağırarak fonksiyonun ismini değişkene atar.
     * Her satır için "insideFunctionController" fonksiyonunu çağırarak fonksiyonun içinde olup olmadığını kontrol eder çünkü output bu fonksiyona göre verilmektedir.
     * Gene her satır için "findComments" fonkisyonu çağrılır ve her satır için yorumlar bulunarak çeşitlerine ayrılır.
     */
    public void findFunctionsAndSpiltTheirComments() {
        Pattern pattern = Pattern.compile(regexOfFunc);
        for (String line : lines) {
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                insideFunction = true;
                firstTime=false;
                splitFunctionName(line);
            
            }
            insideFunctionController(line);
            findComments(line);
        
        }
    
    }

    /**
     * fonksiyon ismini ayırmaya yarar.
     * @param line
     */
    public void splitFunctionName(String line){
        functionName = line.substring(line.indexOf(' ') + 1, line.indexOf('('));
    
    }

    /**
     * yorum satırı olup olmadığını kontrol eder ve çeşitlerine ayırır.
     * @param line
     */
    public void findComments(String line) {
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
                txtWriter.cokSatirWriter(line);
                multiLineComments++;
            
            } else {
                txtWriter.cokSatirWriter(line);
                insideMultiLineComment = true;
            
            }
        
        } else if (line.contains("*/") && insideMultiLineComment) { // çoklu satırlı yorumun sonu
            txtWriter.cokSatirWriter(line);
            insideMultiLineComment = false;
            multiLineComments++;
        
        } else if (line.startsWith("*") && insideMultiLineComment) { // çoklu satırlı yorumun ortası
            txtWriter.cokSatirWriter(line);
        
        
            //tekli satır
        } else if (line.contains("//")) { // tek satırlı yorum
            singleLineComments++;
            txtWriter.tekSatirWriter(line);
        
        }
        
    }

    /**
     * Süslü parantez sayısını arttırıp azaltarak fonksiyonun içinde olup olmadığımızı kontrol eder.
     * Son if içinde "==1" yapılmasının sebebi class başındaki açılış parantezinden kaçmaktır.
     * Fonksiyonun bittiğini fark edildiğinde "getOutPut" çağırılır ve ekrana çıktı verilir .
     * @param line
     */
    public void insideFunctionController(String line){
        if(insideFunction == true){
            if(line.contains("}"))                  numberOfBrace--;
            if(line.contains("{"))                  numberOfBrace++;
            if(numberOfBrace == 1 && !firstTime)    { insideFunction=false; getOutPut(); }        
        
        }
    
    }

    /**
     * istenen outputu ekrana yazdırır.
     * yazdırdıktan sonra "resetVariables" fonksiyonunu çağırır.
     */
    public void getOutPut(){
        System.out.println(functionName);
        System.out.println("--------------");
        System.out.println("Tek satırlı yorum sayısı: " + singleLineComments);
        System.out.println("Çoklu satırlı yorum sayısı: " + multiLineComments);
        System.out.println("Javadoc yorum sayısı: " + javadocComments);
        System.out.println("****************************************************************");
        resetVariables();
    
    }

    /**
     * değişken değerlerini sıfırlıyor bu bize her fonksiyon için baştan değer tutabilme şansı veriyor.
     */
    public void resetVariables(){
        functionName="";
        singleLineComments = multiLineComments = javadocComments = 0;
        
    }
    
}

