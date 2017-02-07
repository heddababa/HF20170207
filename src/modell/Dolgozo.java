package modell;

import java.sql.Date;


public class Dolgozo implements Comparable<Dolgozo> {

  private int empID;
  private String nev;
  private int depId;
  private String reszlegNev;
  private String munkakor;
  private int fizetes;
  private Date felveteliDatum;

  public Dolgozo(int empID, String nev, int depId, String reszlegNev, String munkakor, int fizetes, Date felveteliDatum /*, int minFizetes, int maxFizetés*/) {
    this.empID = empID;
    this.nev = nev;
    this.depId = depId;
    this.reszlegNev = reszlegNev;
    this.munkakor = munkakor;
    this.fizetes = fizetes;
    this.felveteliDatum= felveteliDatum;
  }

  public int getEmpID() {
    return empID;
  }

  public String getNev() {
    return nev;
  }

  public int getDepId() {
    return depId;
  }

  public String getReszlegNev() {
    return reszlegNev;
  }

  public String getMunkakor() {
    return munkakor;
  }

  public int getFizetes() {
    return fizetes;
  }

//  public int getMinFizetes() {
//    return minFizetes;
//  }
//
//  public int getMaxFizetés() {
//    return maxFizetés;
//  }

  @Override
  public String toString() {
    //return "<html>"+nev+"<TAB IDENT=30> ($"+fizetes+")</html>";
    String szokoz="  ";
//    for (int i = 0; i < 30-(nev.length()+((Integer)fizetes).toString().length()); i++) {
//      szokoz+=" ";
//    }
    return nev+szokoz+"($"+fizetes+")";
  }

  @Override
  public int compareTo(Dolgozo masik) {
    return this.nev.compareTo(masik.nev);
  }

  public void setFizetes(int ujFizetes) {
    this.fizetes=ujFizetes;
  }
}
