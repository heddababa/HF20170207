
package modell;

import java.util.ArrayList;

public class Ellenorzesek {
/*  static final int MINKARAKTERKUKACELOTT=2;
  static final String[] SZOLGALTATO={"freemail", "gmail", "hotmail", "mail"};
  static final String[] ORSZAGJELOLO={"com", "hu", "edu", "net"};
*/
  
  public static boolean hosszEllenorzes(String hibauzenet, String szoveg, int hossz, boolean kotelezo)
    throws IllegalArgumentException {
    if (szoveg.length()>hossz)
      throw new IllegalArgumentException(hibauzenet);    //Ez a kivetel miert nem gordul tovabb a hivo metodushoz? 
    return ( kotelezo? szoveg.length()>0 : true );
  }
  
//  public static boolean fizetesEllenorzesUjDolgozonal(int reszlegId, int ujFizetes) {
//    int[] osszFizetesosszLetszam=AdatBazisKezeles.lekerdezesOsszFizLetszReszlegenBelul(reszlegId);
//    int osszFiz=osszFizetesosszLetszam[0];
//    int osszLetszam=osszFizetesosszLetszam[1];
//    long adhatoMinFizetes=Math.round( osszFiz*(-0.05) + (osszFiz*0.95/osszLetszam));
//    long adhatoMaxFizetes=Math.round( osszFiz*0.05 + (osszFiz*1.95/osszLetszam));
//    return (ujFizetes<=adhatoMaxFizetes && ujFizetes>=adhatoMinFizetes);           
//  } 
  
/*  
  private static boolean van(String emailszakasz, String[] tomb) {
    int i=0;
    while(i<tomb.length && !(tomb[i].equals(emailszakasz)))
      i++;
    return (i<tomb.length);
  }
*/  
  public static boolean emailEllenorzes(String email) throws IllegalArgumentException {
    boolean megfeleloEmail=false;
    ArrayList<String> emailLista=AdatBazisKezeles.lekerdezEmail();
    return !emailLista.contains(email);
//    int kukacHely=email.indexOf("@");
//    int pontHely=email.indexOf(".");
//
//    if (kukacHely==-1 || pontHely==-1)
//      throw new IllegalArgumentException("Hiányzik a @ és/vagy a . a megadott email címből!");
//    if (kukacHely>pontHely)
//      throw new IllegalArgumentException("Nem megfelelő a @ és a . sorrendje!");
//    if ((email.substring(0, kukacHely)).length()<2)
//      throw new IllegalArgumentException("Legalább két karakter hosszúságú kell legyen a @ előtti rész!");
//    if (email.charAt(0)>='0' && email.charAt(0)<='9')
//      throw new IllegalArgumentException("Nem kezdődhet számjeggyel!");
//    String emailKozepe=email.substring(kukacHely+1, pontHely);
//    String emailVege=email.substring(pontHely+1);
//    if (!van(emailKozepe, SZOLGALTATO) || !van(emailVege, ORSZAGJELOLO))
//      throw new IllegalArgumentException("@ utáni rész nem megfelelő, nem létezik ilyen!");      
//    megfeleloEmail=true;
//    return megfeleloEmail;
  }
}
