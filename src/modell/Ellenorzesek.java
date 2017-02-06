
package modell;

public class Ellenorzesek {
  static final int MINKARAKTERKUKACELOTT=2;
  static final String[] SZOLGALTATO={"freemail", "gmail", "hotmail", "mail"};
  static final String[] ORSZAGJELOLO={"com", "hu", "edu", "net"};
  
  private static boolean van(String emailszakasz, String[] tomb) {
    int i=0;
    while(i<tomb.length && !(tomb[i].equals(emailszakasz)))
      i++;
    return (i<tomb.length);
  }
  
  public static boolean emailEllenorzes(String email) throws IllegalArgumentException {
    boolean megfeleloEmail=false;
//    try {
      int kukacHely=email.indexOf("@");
      int pontHely=email.indexOf(".");
      
      if (kukacHely==-1 || pontHely==-1)
        throw new IllegalArgumentException("Hiányzik a @ és/vagy a . a megadott email címből!");
      if (kukacHely>pontHely)
        throw new IllegalArgumentException("Nem megfelelő a @ és a . sorrendje!");
      if ((email.substring(0, kukacHely)).length()<2)
        throw new IllegalArgumentException("Legalább két karakter hosszúságú kell legyen a @ előtti rész!");
      if (email.charAt(0)>='0' && email.charAt(0)<='9')
        throw new IllegalArgumentException("Nem kezdődhet számjeggyel!");
      String emailKozepe=email.substring(kukacHely+1, pontHely);
      String emailVege=email.substring(pontHely+1);
      if (!van(emailKozepe, SZOLGALTATO) || !van(emailVege, ORSZAGJELOLO))
        throw new IllegalArgumentException("@ utáni rész nem megfelelő, nem létezik ilyen!");      
      megfeleloEmail=true;
//    }          
//    catch (Exception e) {
//      System.out.println(e.getMessage());
//    }
    return megfeleloEmail;
  } 
  
}
