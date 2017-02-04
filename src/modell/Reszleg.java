package modell;

public class Reszleg implements Comparable<Reszleg> {

  private String reszlegNev;
  private int reszlegId;

  public Reszleg(String reszlegNev, int reszlegId) {
    this.reszlegNev = reszlegNev;
    this.reszlegId = reszlegId;
  }

  public String getReszlegNev() {
    return reszlegNev;
  }

  public int getReszlegId() {
    return reszlegId;
  }

  @Override
  public String toString() {
    return reszlegNev;
  }

  @Override
  public int compareTo(Reszleg masik) {
    return this.reszlegNev.compareTo(masik.getReszlegNev());
  }
}
