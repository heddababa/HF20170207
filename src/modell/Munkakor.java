package modell;

public class Munkakor implements Comparable<Munkakor> {

  private String munkakorNev;
  private int munkakorId;
  private int minFizetes;
  private int maxFizetes;

  public Munkakor(String munkakorNev, int munkakorId) {
    this.munkakorNev = munkakorNev;
    this.munkakorId = munkakorId;
    this.minFizetes = minFizetes;
    this.maxFizetes = maxFizetes;
  }

  public String getMunkakorNev() {
    return munkakorNev;
  }

  public int getMunkakorId() {
    return munkakorId;
  }
  
  public int getMinFizetes() {
    return minFizetes;
  }

  public int getMaxFizetes() {
    return maxFizetes;
  }

  @Override
  public String toString() {
    return munkakorNev;
  }

  @Override
  public int compareTo(Munkakor masik) {
    return this.munkakorNev.compareTo(masik.getMunkakorNev());
  } 
}