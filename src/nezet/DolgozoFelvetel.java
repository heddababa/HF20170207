package nezet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modell.AdatBazisKezeles;
import modell.Ellenorzesek;
import modell.Munkakor;
import modell.Reszleg;

class DolgozoFelvetel extends JDialog {    
  JLabel lbVezeteknev = new JLabel("* Vezetéknév:    ", SwingConstants.RIGHT);
  JLabel lbKeresztnev = new JLabel("Keresztnév:    ", SwingConstants.RIGHT);
  JLabel lbEmail = new JLabel("* E-mail cím:    ", SwingConstants.RIGHT);
  JLabel lbTelefonszam=new JLabel("Telefonszám:    ", SwingConstants.RIGHT);
  JLabel lbReszlegnev = new JLabel("* Részleg kiválasztása:    ", SwingConstants.RIGHT);
  JLabel lbMunkakor = new JLabel("* Munkakör kiválasztása:    ", SwingConstants.RIGHT);
  JLabel lbFizetes=new JLabel("* Fizetés:    ", SwingConstants.RIGHT);
  private JTextField tfVezeteknev = new JTextField("", 25);
  private JTextField tfKeresztnev = new JTextField("", 20);
  private JTextField tfEmail = new JTextField("", 25);
  private JTextField tfTelefonszam = new JTextField("", 20);
  private JTextField tfFizetes = new JTextField("");
  //private JSpinner spFizetes=new JSpinner;//(new SpinnerNumberModel(aktFizetes, adhatoMin, adhatoMax, 50));
  private JComboBox cbReszlegLista;
  private JComboBox cbMunkakorLista;
  private AdatBazisKezeles modell;
  JButton btAdd = new JButton("Hozzáadás");    
    
  public DolgozoFelvetel(JFrame parent, AdatBazisKezeles modell) {
    super(parent, "Új dolgozó hozzáadása", true);
    this.modell=modell;
    setSize(350, 400);
    setLocationRelativeTo(this);
    
    cbReszlegLista = reszlegListaBetoltes();
    cbMunkakorLista = munkakorListaBetoltes();
    
    JPanel pnVezeteknev=new JPanel(new GridLayout(1, 2));
    pnVezeteknev.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnVezeteknev.add(lbVezeteknev);
    pnVezeteknev.add(tfVezeteknev);
    tfVezeteknev.setText(""); 

    JPanel pnKeresztnev=new JPanel(new GridLayout(1, 2));
    pnKeresztnev.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnKeresztnev.add(lbKeresztnev);
    pnKeresztnev.add(tfKeresztnev);
    tfKeresztnev.setText("");
    pnKeresztnev.add(tfKeresztnev);
    
    JPanel pnEmail=new JPanel(new GridLayout(1, 2));
    pnEmail.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnEmail.add(lbEmail);
    pnEmail.add(tfEmail);
    tfEmail.setText(""); 
    pnEmail.add(tfEmail);
    
    JPanel pnTelefonszam=new JPanel(new GridLayout(1, 2));
    pnTelefonszam.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnTelefonszam.add(lbTelefonszam);
    pnTelefonszam.add(tfTelefonszam);
    tfTelefonszam.setText("");
    pnTelefonszam.add(tfTelefonszam);
    
    JPanel pnReszleg=new JPanel (new GridLayout(1, 2));
    pnReszleg.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnReszleg.add(lbReszlegnev);
    pnReszleg.add(cbReszlegLista);
    
    JPanel pnMunkakor=new JPanel (new GridLayout(1, 2));
    pnMunkakor.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnMunkakor.add(lbMunkakor);
    pnMunkakor.add(cbMunkakorLista);
    
    JPanel pnFizetes=new JPanel(new GridLayout(1, 2));
    pnFizetes.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnFizetes.add(lbFizetes);
    pnFizetes.add(tfFizetes);
    tfFizetes.setText("");  
    pnFizetes.add(tfFizetes);
    
    JPanel pnAdatbevitel=new JPanel(new GridLayout(7,2));
    pnAdatbevitel.add(pnVezeteknev);
    pnAdatbevitel.add(pnKeresztnev);
    pnAdatbevitel.add(pnEmail);
    pnAdatbevitel.add(pnTelefonszam);
    pnAdatbevitel.add(pnReszleg);
    pnAdatbevitel.add(pnMunkakor);
    pnAdatbevitel.add(pnFizetes);
    
    JPanel pnGomb=new JPanel(new GridLayout(1,1));
    pnGomb.setBorder(new EmptyBorder(10, 90, 10, 90));
    pnGomb.add(btAdd, BorderLayout.CENTER);
    
    add(pnAdatbevitel, BorderLayout.CENTER);
    add(pnGomb, BorderLayout.SOUTH);
    
    setResizable(false);    
    btAdd.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          boolean kotelezoAdatokMegadva=false;
          Ellenorzesek.hosszEllenorzes("A keresztnév túl hosszú", tfKeresztnev.getText(), 20, false);
          kotelezoAdatokMegadva=Ellenorzesek.hosszEllenorzes("A vezetéknév túl hosszú", tfVezeteknev.getText(), 25, true);
          kotelezoAdatokMegadva=kotelezoAdatokMegadva && Ellenorzesek.hosszEllenorzes("Az email túl hosszú", tfEmail.getText(), 25, true);
          Ellenorzesek.hosszEllenorzes("A telefonszám túl hosszú", tfTelefonszam.getText(), 20, false);
          kotelezoAdatokMegadva=Ellenorzesek.hosszEllenorzes("Fizetés megadása kötelező", tfFizetes.getText(), 8, true);
          //TODO a fizetes mezobe csak szamokat lehet gepelni!!!!
          if (kotelezoAdatokMegadva) {
            Reszleg reszleg = (Reszleg) cbReszlegLista.getSelectedItem();
            int[] osszFizetesosszLetszam=AdatBazisKezeles.lekerdezesOsszFizLetszReszlegenBelul(reszleg.getReszlegId());
            int osszFiz=osszFizetesosszLetszam[0];
            int osszLetszam=osszFizetesosszLetszam[1];
            long adhatoMinFizetes=Math.round( osszFiz*(-0.05) + (osszFiz*0.95/osszLetszam));
            long adhatoMaxFizetes=Math.round( osszFiz*0.05 + (osszFiz*1.95/osszLetszam));
            int ujFizetes=Integer.parseInt(tfFizetes.getText());
            if ( ujFizetes>adhatoMaxFizetes && ujFizetes < adhatoMinFizetes)
              throw new IllegalArgumentException("A fizetés "+adhatoMinFizetes+" és "+adhatoMaxFizetes+" között lehet!");
            System.out.println("Ide johet a mentes ha valoban minden adat klappol fentebb.");
          }
        }
        catch (IllegalArgumentException hiba) {
          JOptionPane.showMessageDialog((Component) e.getSource(), 
                  hiba.getMessage(), 
                  "Hibaüzenet", 
                  JOptionPane.ERROR_MESSAGE);
        }
      }  
    });
    setVisible(true);
  }    
    
  private JComboBox reszlegListaBetoltes() {
    JComboBox cbReszlegLista = new JComboBox();
    ArrayList<Reszleg> reszlegek=modell.lekerdezReszleg();
  //  cbReszlegLista.addItem(new Reszleg("", -1));
    for (Reszleg reszleg : reszlegek)
      cbReszlegLista.addItem(reszleg);
    return cbReszlegLista;
  }

  private JComboBox munkakorListaBetoltes() {
    JComboBox cbMunkakorLista = new JComboBox();
    ArrayList<Munkakor> munkakorok=modell.lekerdezMunkakorok();
  //  cbMunkakorLista.addItem(new Munkakor("", "", -1, -1));
    for (Munkakor munkakor : munkakorok)
      cbMunkakorLista.addItem(munkakor);
    return cbMunkakorLista;
  }
}