package Vue;

import METIER.ServiceVille;
import MODEL.Ville;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.nio.file.Paths;
import java.util.List;

public class DeleteCityFrame extends JPanel {
    private static JPanel northPanel, southPanel , centerPanel ,southPanel2 , centerPanel2;
    private static JButton btn_delete, btn_reset;
    private static JLabel formTitle , label;
    private static JTextField nomVilleASupprimer ;

    void initButtons() {
        btn_reset = new JButton("Reset");
        btn_reset.setFont(new Font("Optima", Font.BOLD | Font.ITALIC | Font.PLAIN, 15));

        btn_delete = new JButton("Delete");
        btn_delete.setHorizontalAlignment(JButton.CENTER);
        btn_delete.setFont(new Font("Optima", Font.BOLD | Font.ITALIC | Font.PLAIN, 15));
    }

    void initTextField(){
        nomVilleASupprimer = new JTextField();
        nomVilleASupprimer.setHorizontalAlignment(JTextField.CENTER);
        nomVilleASupprimer.setFont(new Font("Optima",Font.ITALIC |Font.PLAIN,20));
    }

    void initLabels(){
        formTitle = new JLabel("City Manager");
        formTitle.setFont(new Font("Times New Roman", Font.BOLD , 25));

        label = new JLabel("Entrez le nom de la ville");
        label.setFont(new Font("Times New Roman", Font.BOLD , 22));
        label.setBorder(new EmptyBorder(5,5,5,5));

    }

    void initPanels() {

        centerPanel2 = new JPanel();
        centerPanel2.setBackground(Color.lightGray);
        centerPanel2.setPreferredSize(new Dimension(100,100));
        centerPanel2.setLayout(new GridLayout(1,1));
        centerPanel2.setBorder(new EmptyBorder(2,2,5,2));

        southPanel2 = new JPanel();
        southPanel2.setBackground(Color.decode("#3CE7E7"));
        southPanel2.setPreferredSize(new Dimension(100,50));
        southPanel2.setLayout(new GridLayout(1,2));
        southPanel2.setBorder(new EmptyBorder(5,5,5,5));
        southPanel2.add(label);
        southPanel2.add(nomVilleASupprimer);

        northPanel = new JPanel();
        northPanel.setBackground(Color.decode("#36B2B2"));
        northPanel.setPreferredSize(new Dimension(100,50));
        northPanel.setBorder(new EmptyBorder(5,5,5,5));


        northPanel.add(formTitle);

        centerPanel = new JPanel();
        centerPanel.setBackground(Color.lightGray);
        centerPanel.setPreferredSize(new Dimension(100,100));
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(5,5,5,5));
        centerPanel.add(centerPanel2, BorderLayout.CENTER);
        centerPanel.add(southPanel2, BorderLayout.SOUTH);

        southPanel = new JPanel();
        southPanel.setBackground(Color.decode("#91A7A7"));
        southPanel.setPreferredSize(new Dimension(100,40));
        southPanel.setBorder(new EmptyBorder(0, 50,5,15));
        southPanel.add(btn_delete);
        southPanel.add(btn_reset);
    }

    void initActionListener(){

        ServiceVille serviceVille = new ServiceVille(Paths.get("villes.txt"));
        btn_delete.addActionListener(e -> {
            serviceVille.supprimerVille(nomVilleASupprimer.getText());
            nomVilleASupprimer.setText("");
        });
         btn_reset.addActionListener(e -> nomVilleASupprimer.setText(""));
    }

    public DeleteCityFrame() {

        initButtons();
        initLabels();
        initTextField();
        initPanels();
        initActionListener();
        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);
    }
}