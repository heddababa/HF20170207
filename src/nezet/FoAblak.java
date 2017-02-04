package nezet;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import modell.*;


public class FoAblak extends JFrame 
  implements ActionListener, ListSelectionListener {

  private JComboBox cbReszlegLista;
  JLabel lbTalalat=new JLabel("Nincs találat");
  private JList lDolgozoLista = new JList(new DefaultListModel());
  private JTextField tfDolgozoKeres = new JTextField("Keresendő dolgozó", 12);
  private JScrollPane spDolgozoLista = new JScrollPane(lDolgozoLista);
  private AdatBazisKezeles modell;
  DefaultListModel dlm = new DefaultListModel();

  public FoAblak(AdatBazisKezeles modell) {
    this.modell=modell;
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setTitle("HR Fizetésemelés");
    setSize(700, 500);
    setLocationRelativeTo(this);
    setIconImage(Toolkit.getDefaultToolkit().getImage("image1.jpg"));
    
    cbReszlegLista = reszlegListaBetoltes();  
    
    JPanel pnReszlegek=new JPanel(new GridLayout(2, 1));
    pnReszlegek.add(new JLabel("Részlegek: "));
    pnReszlegek.add(cbReszlegLista);
    
    JPanel pnDolgozoKereses=new JPanel(new GridLayout(2, 1));
    pnDolgozoKereses.add(new JLabel("Dolgozó keresés: "));
    tfDolgozoKeres.setText("");
    tfDolgozoKeres.getDocument().addDocumentListener(new MyDocumentListener());
    tfDolgozoKeres.getDocument().putProperty("name", "Text Area");    
    pnDolgozoKereses.add(tfDolgozoKeres);    
    
    lbTalalat.setVisible(false);
    lbTalalat.setForeground(Color.blue);
    lbTalalat.setHorizontalAlignment(SwingConstants.CENTER);
    
    JPanel pn = new JPanel(new GridLayout(1, 3));
    pn.add(panelKeszit(pnReszlegek));
    pn.add(panelKeszit(pnDolgozoKereses));
    pn.add(lbTalalat);
    add(pn, BorderLayout.PAGE_START);    
    add(new JPanel(), BorderLayout.LINE_START);
    add(new JPanel(), BorderLayout.LINE_END);
    add(new JPanel(), BorderLayout.PAGE_END);
    
    add(spDolgozoLista);
    setVisible(true);
    cbReszlegLista.addActionListener(this);
    //lDolgozoLista.addListSelectionListener(this);
    
    lDolgozoLista.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {        
        if (e.getSource()==lDolgozoLista) {
          Dolgozo dolgozo = (Dolgozo) ((JList) e.getSource()).getSelectedValue();
          DefaultListModel dlm = (DefaultListModel) lDolgozoLista.getModel();
          if(dolgozo != null){
            int dolgozoIndex = dlm.indexOf(dolgozo);
            new AdatBekeres((JFrame) SwingUtilities.getRoot((Component) e.getSource()), dolgozo, modell);
            //new AdatBekeres(this, dolgozo, modell);
            dlm.setElementAt(dolgozo, dolgozoIndex);
          }
        }
      }    
    });
    
    lDolgozoLista.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        if (e.getSource()==lDolgozoLista)
          if (e.getKeyCode()==KeyEvent.VK_ENTER)  {
            Dolgozo dolgozo = (Dolgozo) ((JList) e.getSource()).getSelectedValue();
            DefaultListModel dlm = (DefaultListModel) lDolgozoLista.getModel();
            if(dolgozo != null){
              int dolgozoIndex = dlm.indexOf(dolgozo);
              //System.out.println(dolgozo.getNev() + " " + dolgozo.getMunkakor());
              new AdatBekeres((JFrame) SwingUtilities.getRoot((JList) e.getSource()), dolgozo, modell);
              dlm.setElementAt(dolgozo, dolgozoIndex);
            }
        }
      }  
    });
    reszlegListaBetoltes();
    lDolgozoLista.setModel(dolgozoListaBetoltes(-1));
    lDolgozoLista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    lDolgozoLista.setSelectedIndex(0);
    lDolgozoLista.requestFocus();
  }
  
  private JPanel panelKeszit(JPanel pnReszlegek) {
    JPanel ujPn=new JPanel();
    ujPn.add(new JPanel(), BorderLayout.PAGE_START);
    ujPn.add(new JPanel(), BorderLayout.PAGE_END);
    ujPn.add(new JPanel(), BorderLayout.LINE_START);
    ujPn.add(new JPanel(), BorderLayout.LINE_START);
    ujPn.add(pnReszlegek, BorderLayout.CENTER);
    return ujPn;
  }

  private JComboBox reszlegListaBetoltes() {
    JComboBox cbReszlegLista = new JComboBox();
    ArrayList<Reszleg> reszlegek=modell.lekerdezReszleg();
    cbReszlegLista.addItem(new Reszleg("Összes dolgozó", -1));
    for (Reszleg reszleg : reszlegek)
      cbReszlegLista.addItem(reszleg);
    return cbReszlegLista;
  }

  private DefaultListModel dolgozoListaBetoltes(int reszlegID) {
    dlm = new DefaultListModel();
    ArrayList<Dolgozo> dolgozok = modell.lekerdezDolgozokListajaAdottReszleghez(reszlegID);
    for (Dolgozo dolgozo : dolgozok)
      dlm.addElement(dolgozo);
    return dlm;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == cbReszlegLista) {
      Reszleg reszleg = (Reszleg) ((JComboBox) e.getSource()).getSelectedItem();
      lDolgozoLista.setModel(dolgozoListaBetoltes(reszleg.getReszlegId()));
      tfDolgozoKeres.setText("");
      lbTalalat.setVisible(false);
      lDolgozoLista.setSelectedIndex(0); 
      lDolgozoLista.requestFocus();
    }
  }

  public void valueChanged(ListSelectionEvent e) {
    ;
  } 

  class MyDocumentListener implements DocumentListener {
    final String newline = "\n";
    
    public void insertUpdate(DocumentEvent e) {
      updateLog(e);
    }

    public void removeUpdate(DocumentEvent e) {
      updateLog(e);
    }

    public void changedUpdate(DocumentEvent e) {
      ;
    }

    public void updateLog(DocumentEvent e) {
      if (tfDolgozoKeres.getText().length() > 0) {
        String keres = tfDolgozoKeres.getText().toLowerCase();
        DefaultListModel dlmSzukitett = new DefaultListModel();
        for (int i = 0; i < dlm.getSize(); i++) {
          if (dlm.getElementAt(i).toString().toLowerCase().contains(keres)) {
            dlmSzukitett.addElement(dlm.getElementAt(i));
          }
        }
        if (dlmSzukitett.size() == 0)
          lbTalalat.setVisible(true);
        else
          lbTalalat.setVisible(false);
        lDolgozoLista.setModel(dlmSzukitett);
      } 
      else {
        lbTalalat.setVisible(false);
        lDolgozoLista.setModel(dlm);
      }
    }
  }
  
}
