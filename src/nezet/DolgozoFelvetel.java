package nezet;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modell.AdatBazisKezeles;
import modell.Munkakor;
import modell.Reszleg;

class DolgozoFelvetel extends JDialog
    implements ActionListener /*ListSelectionListener*/ {
    
    JLabel lbVezeteknev = new JLabel("* Vezetéknév:    ", SwingConstants.RIGHT);
    JLabel lbKeresztnev = new JLabel("* Keresztnév:    ", SwingConstants.RIGHT);
    JLabel lbEmail = new JLabel("E-mail cím:    ", SwingConstants.RIGHT);
    JLabel lbTelefonszam=new JLabel("Telefonszám:    ", SwingConstants.RIGHT);
    JLabel lbReszlegnev = new JLabel("* Részleg kiválasztása:    ", SwingConstants.RIGHT);
    JLabel lbMunkakor = new JLabel("* Munkakör kiválasztása:    ", SwingConstants.RIGHT);
    JLabel lbFizetes=new JLabel("* Fizetés:    ", SwingConstants.RIGHT);
    private JTextField tfVezeteknev = new JTextField("", 25);
    private JTextField tfKeresztnev = new JTextField("", 20);
    private JTextField tfEmail = new JTextField("", 25);
    private JTextField tfTelefonszam = new JTextField("", 20);
    private JTextField tfFizetes = new JTextField("");
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
//    tfVezeteknev.getDocument().addDocumentListener((DocumentListener)
//        new DolgozoFelvetel.MyDocumentListener());   

    JPanel pnKeresztnev=new JPanel(new GridLayout(1, 2));
    pnKeresztnev.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnKeresztnev.add(lbKeresztnev);
    pnKeresztnev.add(tfKeresztnev);
    tfKeresztnev.setText("");
//    tfKeresztnev.getDocument().addDocumentListener((DocumentListener)
//        new DolgozoFelvetel.MyDocumentListener());  
    pnKeresztnev.add(tfKeresztnev);
    
    JPanel pnEmail=new JPanel(new GridLayout(1, 2));
    pnEmail.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnEmail.add(lbEmail);
    pnEmail.add(tfEmail);
    tfEmail.setText("");
//    tfEmail.getDocument().addDocumentListener((DocumentListener)
//        new DolgozoFelvetel.MyDocumentListener());    
    pnEmail.add(tfEmail);
    
    JPanel pnTelefonszam=new JPanel(new GridLayout(1, 2));
    pnTelefonszam.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnTelefonszam.add(lbTelefonszam);
    pnTelefonszam.add(tfTelefonszam);
    tfTelefonszam.setText("");
//    tfTelefonszam.getDocument().addDocumentListener((DocumentListener)
//        new DolgozoFelvetel.MyDocumentListener());   
    pnTelefonszam.add(tfTelefonszam);
    
    JPanel pnReszleg=new JPanel (new GridLayout(1, 2));
    pnReszleg.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnReszleg.add(lbReszlegnev);
    pnReszleg.add(cbReszlegLista);
//    cbReszlegLista.addActionListener(this);
    
    JPanel pnMunkakor=new JPanel (new GridLayout(1, 2));
    pnMunkakor.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnMunkakor.add(lbMunkakor);
    pnMunkakor.add(cbMunkakorLista);
//    cbMunkakorLista.addActionListener(this);
    
    JPanel pnFizetes=new JPanel(new GridLayout(1, 2));
    pnFizetes.setBorder(new EmptyBorder(10, 10, 10, 10));
    pnFizetes.add(lbFizetes);
    pnFizetes.add(tfFizetes);
    tfFizetes.setText("");
//    tfFizetes.getDocument().addDocumentListener((DocumentListener)
//        new DolgozoFelvetel.MyDocumentListener());   
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
    setVisible(true);
    btAdd.addActionListener(this);
  }
    
    
    private JComboBox reszlegListaBetoltes() {
        JComboBox cbReszlegLista = new JComboBox();
        ArrayList<Reszleg> reszlegek=modell.lekerdezReszleg();
        cbReszlegLista.addItem(new Reszleg(" -- Összes részleg", -1));
        for (Reszleg reszleg : reszlegek)
            cbReszlegLista.addItem(reszleg);
        return cbReszlegLista;
    }

    private JComboBox munkakorListaBetoltes() {
        JComboBox cbMunkakorLista = new JComboBox();
        ArrayList<String> munkakorok=modell.lekerdezMunkakorok();
        cbMunkakorLista.addItem(new Munkakor(" -- Összes munkakör", -1));
        for (String munkakor : munkakorok)
            cbMunkakorLista.addItem(munkakor);
        return cbMunkakorLista;
    }

//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == cbReszlegLista) {
//        }
//    }
//
//    public void valueChanged(ListSelectionEvent e) {
//        ;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAdd) {
            System.out.println("Nem történt hozzáadás.");
            dispose();
        }
        else if (e.getSource() != btAdd) {
            JOptionPane.showMessageDialog((Component) e.getSource(), 
                    "Nem sikerült a módosítás!", 
                    "Hibaüzenet", 
                    JOptionPane.ERROR_MESSAGE);
        }
       // dispose();
      }

//    private static class MyDocumentListener {
//        public MyDocumentListener() {
//        }
//    }
}