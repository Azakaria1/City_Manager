package Vue;

import METIER.ServiceVille;
import MODEL.Ville;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.List;

public class SearchCityFrame extends  JPanel implements ActionListener {

    private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private static JPanel centerPanel2, northPanel2, northPanel, centerPanel;
    JButton btn_search;
    JLabel formTitle;
    JTextField txt_search;
    JTextArea textArea;


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    void initButtons() {
        btn_search = new JButton("Search");
        btn_search.setHorizontalAlignment(JButton.RIGHT);
        btn_search.setPreferredSize(new Dimension(0, 40));
        btn_search.setFont(new Font("Optima", Font.BOLD | Font.ITALIC | Font.PLAIN, 15));
    }

    void initTextArea() {
        textArea = new JTextArea();
        textArea.setFont(new Font("Times New Roman", Font.BOLD, 30));
    }

    void initLabels() {
        formTitle = new JLabel("City Manager");
        formTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));

        txt_search = new JTextField();
        txt_search.setFont(new Font("Times New Roman", Font.BOLD, 25));
    }

    void initPanels() {

        centerPanel2 = new JPanel();
        centerPanel2.setBackground(Color.lightGray);
        centerPanel2.setPreferredSize(new Dimension(100, 100));
        centerPanel2.setLayout(new GridLayout(1, 1));
        centerPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
        centerPanel2.add(textArea);

        northPanel2 = new JPanel();
        northPanel2.setBackground(Color.decode("#3CE7E7"));
        northPanel2.setLayout(new GridLayout(1, 2));
        northPanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
        northPanel2.setBounds(0, 0, dimension.width - 400, 0);
        northPanel2.add(txt_search);
        northPanel2.add(btn_search);

        northPanel = new JPanel();
        northPanel.setBackground(new Color(255, 0, 0));
        northPanel.setPreferredSize(new Dimension(100, 60));
        northPanel.add(formTitle, BorderLayout.CENTER);

        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(161, 154, 154));
        centerPanel.setPreferredSize(new Dimension(100, 100));
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        centerPanel.add(centerPanel2, BorderLayout.CENTER);
        centerPanel.add(northPanel2, BorderLayout.NORTH);
    }

    public SearchCityFrame() {
        initLabels();
        initTextArea();
        initButtons();
        initPanels();
        setSize(dimension.width - 450, dimension.height - 200);
        System.out.println("Screen Width: " + dimension.width);
        System.out.println("Screen Height : " + dimension.height);
        this.setLayout(new BorderLayout());
        this.add(northPanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);
    }
}