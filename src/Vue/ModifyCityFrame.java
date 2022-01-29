package Vue;

import METIER.ServiceVille;
import MODEL.Ville;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class ModifyCityFrame extends JPanel {
    Ville ville = new Ville();
    String newline ;

    JPanel northPanel,centerPanel,southPanel;
    JLabel lbl_title, lbl_id, lbl_nom, lbl_Population, lbl_Region;
    JButton btn_confirm, btn_cancel;

    JTextField txt_id, txt_nom, txt_population, txt_region;

    void initTextFields(){
        txt_id=new JTextField("auto increment");
        txt_id.setHorizontalAlignment(JTextField.CENTER);
        txt_id.setFont(new Font("Optima",Font.BOLD |Font.PLAIN,18));
        txt_id.setForeground(Color.DARK_GRAY);
        txt_id.setBounds(150,10,200,40);
        txt_id.setEnabled(false);

        txt_nom=new JTextField();
        txt_nom.setHorizontalAlignment(JTextField.CENTER);
        txt_nom.setFont(new Font("Optima",Font.ITALIC |Font.PLAIN,18));
        txt_nom.setForeground(Color.BLUE);
        txt_nom.setBounds(150,60,200,40);

        txt_population=new JTextField();
        txt_population.setHorizontalAlignment(JTextField.CENTER);
        txt_population.setFont(new Font("Optima",Font.ITALIC |Font.PLAIN,18));
        txt_population.setForeground(Color.BLUE);
        txt_population.setBounds(150,110,200,40);

        txt_region=new JTextField();
        txt_region.setHorizontalAlignment(JTextField.CENTER);
        txt_region.setFont(new Font("Optima",Font.ITALIC |Font.PLAIN,18));
        txt_region.setForeground(Color.BLUE);
        txt_region.setBounds(150,160,200,40);


    }

    void initButtons(){
        btn_confirm=new JButton("Confirm");
        btn_confirm.setHorizontalAlignment(JButton.CENTER);
        btn_confirm.setFont(new Font("Optima",Font.BOLD | Font.ITALIC |Font.PLAIN,18));
        btn_confirm.setPreferredSize(new Dimension(100,40));
        btn_confirm.setForeground(Color.BLUE);

        btn_cancel=new JButton("Cancel");
        btn_cancel.setHorizontalAlignment(JButton.CENTER);
        btn_cancel.setFont(new Font("Optima",Font.BOLD | Font.ITALIC |Font.PLAIN,18));
        btn_cancel.setForeground(Color.BLUE);
    }

    void initLabels(){
        lbl_title=new JLabel("Modify City Form");
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setFont(new Font("Optima",Font.BOLD | Font.ITALIC |Font.PLAIN,25));
        lbl_title.setForeground(Color.BLUE);


        lbl_id=new JLabel("Id");
        lbl_id.setHorizontalAlignment(JLabel.CENTER);
        lbl_id.setFont(new Font("Optima",Font.PLAIN,18));
        lbl_id.setForeground(Color.DARK_GRAY);
        lbl_id.setBounds(10,20,100,20);

        lbl_nom=new JLabel("Nom");
        lbl_nom.setHorizontalAlignment(JLabel.CENTER);
        lbl_nom.setFont(new Font("Optima",Font.PLAIN,18));
        lbl_nom.setForeground(Color.DARK_GRAY);
        lbl_nom.setBounds(10,70,100,20);

        lbl_Population=new JLabel("Population");
        lbl_Population.setHorizontalAlignment(JLabel.CENTER);
        lbl_Population.setFont(new Font("Optima",Font.PLAIN,18));
        lbl_Population.setForeground(Color.DARK_GRAY);
        lbl_Population.setBounds(10,120,100,20);

        lbl_Region=new JLabel("Région");
        lbl_Region.setHorizontalAlignment(JLabel.CENTER);
        lbl_Region.setFont(new Font("Optima",Font.PLAIN,18));
        lbl_Region.setForeground(Color.DARK_GRAY);
        lbl_Region.setBounds(10,170,100,20);
    }

    void initPanels(){
        initLabels();
        initButtons();
        initTextFields();


        northPanel=new JPanel();
        northPanel.setBackground(new Color(180,150,250));
        northPanel.setBorder(new EmptyBorder(10,50,10,50));
        northPanel.setLayout(new FlowLayout());
        northPanel.setLayout(new GridLayout(1,1));
        northPanel.add(lbl_title);

        centerPanel=new JPanel();
        centerPanel.setBackground(new Color(180,190,200));
        centerPanel.setLayout(null);
        centerPanel.add(lbl_id);
        centerPanel.add(lbl_nom);
        centerPanel.add(lbl_Population);
        centerPanel.add(lbl_Region);

        centerPanel.add(txt_id);
        centerPanel.add(txt_nom);
        centerPanel.add(txt_population);
        centerPanel.add(txt_region);


        southPanel=new JPanel();
        southPanel.setBackground(new Color(180,150,250));
        southPanel.setBorder(new EmptyBorder(10,50,10,50));
        southPanel.setLayout(new GridLayout(1,2,10,50));
        southPanel.add(btn_confirm);
        southPanel.add(btn_cancel);
    }

    public ModifyCityFrame(){
        initPanels();
        this.setBackground(new Color(100,50,200));
        this.setLayout(new BorderLayout());
        this.add(northPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(southPanel,BorderLayout.SOUTH);

    }
}