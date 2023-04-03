import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FonksiyonBulucu {
    private ArrayList<String> satirlar;

    public FonksiyonBulucu(ArrayList<String> satirlar) {
        this.satirlar = satirlar;
    }

    public void fonksiyonlariAnalizEt() {
        int fonksiyonSayisi = 0;
        ArrayList<String> fonksiyonIsimleri = new ArrayList<>();

        String  fonksiyonRegex = "^\\s*(public|private|protected)?\\s+(static\\s+)?(final\\s+)?(synchronized\\s+)?(\\w+\\s+)?\\w+\\s*\\(.*\\)\\s*((throws\\s+\\w+(\\s*,\\s*\\w+)*)?\\s*)*\\{";
        Pattern pattern = Pattern.compile(fonksiyonRegex);

        for (String satir : satirlar) {
            Matcher matcher = pattern.matcher(satir);
            if (matcher.find()) {
                fonksiyonSayisi++;
                String fonksiyonIsmi = satir.substring(satir.indexOf(' ') + 1, satir.indexOf('('));
                fonksiyonIsimleri.add(fonksiyonIsmi);
            }
        }

        System.out.println("Toplam fonksiyon sayısı: " + fonksiyonSayisi);
        System.out.println("Fonksiyon isimleri: " + fonksiyonIsimleri);
    }
    
}

