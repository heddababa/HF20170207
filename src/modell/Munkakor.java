package modell;

public class Munkakor implements Comparable<Munkakor> {

  private String munkakorNev;
  private String munkakorId;
  private int minFizetes;
  private int maxFizetes;

  public Munkakor(String munkakorNev, String munkakorId, int minFizetes, int maxFizetes) {
    this.munkakorNev = munkakorNev;
    this.munkakorId = munkakorId;
    this.minFizetes = minFizetes;
    this.maxFizetes = maxFizetes;
  }

  public String getMunkakorNev() {
    return munkakorNev;
  }

  public String getMunkakorId() {
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