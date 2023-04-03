import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            FileReader fileReader = new FileReader("C:\\Users\\foyko\\Desktop\\Motor.java");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            ArrayList<String> satirlar = new ArrayList<String>();
            String satir;
            while ((satir = bufferedReader.readLine()) != null) {
                satirlar.add(satir);
            }
            // for (String satir1 : satirlar) {
            //     System.out.println(satir1);
            // }

            FonksiyonBulucu bulucu=new FonksiyonBulucu(satirlar);
            bulucu.fonksiyonlariAnalizEt();
            System.out.println("-------------------------------------");
            countComments(satirlar);

            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Dosya okunurken bir hata oluştu: " + e.getMessage());
        }
    }



    public static void countComments(ArrayList<String> lines) {
        int singleLineComments = 0;
        int multiLineComments = 0;
        int javadocComments = 0;
        boolean insideMultiLineComment = false;
        boolean insideJavadocComment = false;
        for (String line : lines) {
            line = line.trim(); // boşlukları kaldır
                
            //javadoc
            if (line.contains("/**")) { // başlangıç javadoc yorumu
                if (line.contains("*/")) { // tek satırlık bir javadoc yorumu
                    javadocComments++;
                } else {
                    insideJavadocComment = true;
                }
            } else if (line.contains("*/") && insideJavadocComment) { // javadoc yorumunun sonu
                insideJavadocComment = false;
                javadocComments++;
            } else if (line.startsWith("*") && insideJavadocComment) { // javadoc yorumunun ortası

                
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
        System.out.println("Javadoc yorum sayısı: " + javadocComments);
        System.out.println("Tek satırlı yorum sayısı: " + singleLineComments);
        System.out.println("Çoklu satırlı yorum sayısı: " + multiLineComments);
        
    }



}
