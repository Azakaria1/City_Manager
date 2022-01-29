package Vue;

import METIER.ServiceVille;
import MODEL.Ville;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class CityManager extends JFrame implements ActionListener {
    private static Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private Container container = getContentPane();
    private static JPanel buttonPanel, menuPanel ,titlePanel, northPanel, southPanel , centerPanel, eastPanel , westPanel ;
    private static JButton btn_import,btn_save, btn_menu, btn_loadData , btn_reset , btn_add, btn_modify, btn_delete, btn_search;
    private static AddCityFram addCityFrame ;
    private static ModifyCityFrame modifyCityFrame;
    private static DeleteCityFrame deleteCityFrame;
    private static SearchCityFrame searchCityFrame;
    private static JLabel lbl_title ;
    private static Image newImageload ,newImagereset,newImageimport, newImagesave ;
    private static  ImageIcon loadIcon,importIcon,resetIcon,saveIcon ;
    private static  JMenuBar menuBar ;
    private static JMenu tool , file;
    public static JTable table;
    private static JMenuItem loadMenu , exitMenu , addMenu, modifyMenu, deleteMenu, searchMenu ;
    boolean b_menu= false, b_add = false  ,  b_modify = false , b_delete = false, b_search = false;
    private static JSeparator verticalSeparator , verticalSeparator2;
    private static DefaultTableModel tableModel;
    private static JScrollPane sp;
    private List<String[]> values = new ArrayList<>();
    private static List<Ville> villes = new ArrayList<>();
    private static JFileChooser fichier ;
    private static File choosedFile ;
    private static ServiceVille serviceVille ;


    void initFileChooser(){

        fichier = new JFileChooser(".");
        int rep = fichier.showOpenDialog(getParent());
        if (rep == JFileChooser.APPROVE_OPTION) {
            choosedFile = fichier.getSelectedFile();
             serviceVille = new ServiceVille(Path.of(choosedFile.getName()));
            System.out.println(choosedFile.getName());
            if(choosedFile.getName().equals("villes.txt"))
            {
                btn_loadData.setEnabled(true);
                btn_save.setEnabled(true);
                btn_search.setEnabled(true);
                btn_modify.setEnabled(true);
                btn_delete.setEnabled(true);
                btn_add.setEnabled(true);
                btn_reset.setEnabled(true);
                villes = serviceVille.lire_bd_villes_NIO();

                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);

                tableModel = (DefaultTableModel) table.getModel();

                for (Ville v:villes) {
                    tableModel.addRow(new String[] {String.valueOf(v.getId()),v.getNom(), String.valueOf(v.getPopulation()),v.getRegion()});
                }
                for (int i = 0; i < tableModel.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            }
        }
        else {
            System.out.println("cancel");
        }
        fichier.setMultiSelectionEnabled(false);
        fichier.setFileSelectionMode (JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter(" Text files", "txt");
        fichier.setFileFilter(filter);
    }

     void saveFile(List<Ville> villes,Path path){
        try {
            for (Ville v : villes)
            {
                String ligne = v.getId() + ":" + v.getNom() + ":" + v.getPopulation() + ":" + v.getRegion()+ "\n";
                Files.write(path, ligne.getBytes(),
                        StandardOpenOption.CREATE_NEW,
                        StandardOpenOption.WRITE,
                        StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    void initFileSave(){
    int rep = fichier.showSaveDialog(this);
        if (rep == JFileChooser.APPROVE_OPTION) {
            villes = serviceVille.lire_bd_villes_NIO();
            choosedFile = fichier.getSelectedFile();
            serviceVille = new ServiceVille(Path.of(choosedFile.getName()));
            villes.forEach(System.out::println);
            saveFile(villes, Path.of(choosedFile.getName()));
        }
        else {
            System.out.println("cancel");
        }
    }

    void initImage()
    {
        loadIcon =  new ImageIcon("icons/load.png");
         importIcon =  new ImageIcon("icons/import.png");
         resetIcon =  new ImageIcon("icons/reset.png");
        saveIcon =  new ImageIcon("icons/save.png");

        Image imageload = loadIcon.getImage();
        newImageload = imageload.getScaledInstance( 40,40,Image.SCALE_SMOOTH );

        Image imagesave = saveIcon.getImage();
        newImagesave = imagesave.getScaledInstance( 40,40,Image.SCALE_SMOOTH );

        Image imagereset = resetIcon.getImage();
         newImagereset = imagereset.getScaledInstance( 40,40,Image.SCALE_SMOOTH );

        Image imageimport = importIcon.getImage();
        newImageimport = imageimport.getScaledInstance( 40,40,Image.SCALE_SMOOTH );
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    void initTable(){


        table = new JTable(tableModel);
        table.setEnabled(false);
        table.setCellSelectionEnabled(true);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tableModel = (DefaultTableModel)table.getModel();
        tableModel.addColumn("#");
        tableModel.addColumn("NOM");
        tableModel.addColumn("POPULATION");
        tableModel.addColumn("REGION");
        sp=new JScrollPane(table);

    }

    void initButtons(){
        initImage();
        btn_menu = new JButton();
        btn_menu.setBackground(Color.white);
        btn_menu.setIcon(new ImageIcon("icons/menuBlue.png"));
        btn_menu.setBorderPainted(false);
        btn_menu.setHorizontalAlignment(JButton.CENTER);
        btn_menu.setHorizontalTextPosition(JButton.CENTER);
        btn_menu.setVerticalTextPosition(JButton.CENTER);


        btn_reset = new JButton();
        btn_reset.setBorderPainted(false);
        btn_reset.setBackground(new Color(187, 187, 189));
        btn_reset.setIcon(new ImageIcon(newImagereset));
        btn_reset.setBorderPainted(false);

        container.add(btn_reset);

        btn_loadData = new JButton();
        btn_loadData.setBackground(new Color(187, 187, 189));
        btn_loadData.setIcon(new ImageIcon(newImageload));
        btn_loadData.setBorderPainted(false);

        container.add(btn_loadData);

        btn_save = new JButton();
        btn_save.setBackground(new Color(187, 187, 189));
        btn_save.setIcon(new ImageIcon(newImagesave));
        btn_save.setBorderPainted(false);

        container.add(btn_save);

        btn_import = new JButton();
        btn_import.setBackground(new Color(187, 187, 189));
        btn_import.setIcon(new ImageIcon(newImageimport));
        btn_import.setBorderPainted(false);

        container.add(btn_import);

        btn_add = new JButton();
        btn_add.setHorizontalAlignment(JButton.CENTER);
        btn_add.setBackground(Color.white);
        btn_add.setIcon(new ImageIcon("icons/add.png"));
        btn_add.setBorderPainted(false);

        container.add(btn_add);

        btn_modify = new JButton();
        btn_modify.setHorizontalAlignment(JButton.CENTER);
        btn_modify.setBackground(Color.white);
        btn_modify.setIcon(new ImageIcon("icons/edit.png"));
        btn_modify.setBorderPainted(false);

        container.add(btn_modify);

        btn_delete = new JButton();
        btn_delete.setHorizontalAlignment(JButton.CENTER);
        btn_delete.setBackground(Color.white);
        btn_delete.setIcon(new ImageIcon("icons/delete.png"));
        btn_delete.setBorderPainted(false);

        container.add(btn_delete);

        btn_search = new JButton();
        btn_search.setHorizontalAlignment(JButton.CENTER);
        btn_search.setBackground(Color.white);
        btn_search.setIcon(new ImageIcon("icons/search.png"));
        btn_search.setBorderPainted(false);

        container.add(btn_search);

        btn_loadData.setEnabled(false);
        btn_save.setEnabled(false);
        btn_search.setEnabled(false);
        btn_modify.setEnabled(false);
        btn_delete.setEnabled(false);
        btn_add.setEnabled(false);
        btn_reset.setEnabled(false);
    }

    void initSeparator(){
        verticalSeparator = new JSeparator(JSeparator.VERTICAL);
        verticalSeparator.setBackground(Color.GRAY);
        verticalSeparator.setBorder(BorderFactory.createLineBorder(Color.GRAY,20,true));

        verticalSeparator2 = new JSeparator(JSeparator.VERTICAL);
        verticalSeparator2.setBackground(Color.GRAY);
        verticalSeparator2.setBorder(BorderFactory.createLineBorder(Color.white,20));
    }

    void initMenu(){
        loadMenu = new JMenuItem();
        loadMenu.setText("Load Data");
        loadMenu.setFont(new Font("Optima", 0 , 16));

        exitMenu = new JMenuItem();
        exitMenu.setText("Exit");
        exitMenu.setFont(new Font("Optima", 0 , 16));

        addMenu = new JMenuItem();
        addMenu.setText("Add");
        addMenu.setFont(new Font("Optima", 0 , 16));

        modifyMenu = new JMenuItem();
        modifyMenu.setText("Modifier");
        modifyMenu.setFont(new Font("Optima", 0 , 16));

        deleteMenu = new JMenuItem();
        deleteMenu.setText("Delete");
        deleteMenu.setFont(new Font("Optima", 0 , 16));

        searchMenu = new JMenuItem();
        searchMenu.setText("Search");
        searchMenu.setFont(new Font("Optima", 0 , 16));

        file = new JMenu();
        file.setText("File");
        file.setFont(new Font("Optima", 0 , 16));

        file.add(loadMenu);
        file.addSeparator();
        file.add(exitMenu);

        tool = new JMenu();
        tool.setText("Tool");
        tool.setFont(new Font("Optima", 0 , 16));

        tool.add(addMenu);
        tool.addSeparator();

        tool.add(modifyMenu);
        tool.addSeparator();

        tool.add(deleteMenu);
        tool.addSeparator();

        tool.add(searchMenu);

        menuBar = new JMenuBar();

        menuBar.add(file);
        menuBar.add(tool);

    }

    void initLabels(){

        lbl_title = new JLabel("City Manager");
        lbl_title.setFont(new Font("Times New Roman", Font.BOLD , 25));
        lbl_title.setHorizontalAlignment(JLabel.CENTER);
        lbl_title.setHorizontalTextPosition(JLabel.CENTER);
        lbl_title.setVerticalTextPosition(JLabel.BOTTOM);
        lbl_title.setIcon(new ImageIcon("icons/ville.png"));
    }

    void initPanels() {
        addCityFrame = new AddCityFram();
        addCityFrame.setPreferredSize(new Dimension(400,450));
        addCityFrame.setVisible(false);

        modifyCityFrame = new ModifyCityFrame();
        modifyCityFrame.setPreferredSize(new Dimension(400,450));
        modifyCityFrame.setVisible(false);

        deleteCityFrame = new DeleteCityFrame();
        deleteCityFrame.setPreferredSize(new Dimension(400,450));
        deleteCityFrame.setVisible(false);

        searchCityFrame = new SearchCityFrame();
        searchCityFrame.setPreferredSize(new Dimension(400,450));
        searchCityFrame.setVisible(false);


        menuPanel = new JPanel();
        menuPanel.setBackground(Color.white);
        menuPanel.setLayout(new BoxLayout(menuPanel,BoxLayout.LINE_AXIS));
        menuPanel.add(btn_menu);
        menuPanel.add(verticalSeparator);


        titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(lbl_title);

        northPanel = new JPanel();
        northPanel.setBackground(new Color(255, 255, 255));
        northPanel.setLayout(new BorderLayout());;
        northPanel.setPreferredSize(new Dimension(100,120));
        northPanel.add(titlePanel,BorderLayout.CENTER);
        northPanel.add(menuPanel,BorderLayout.WEST);


        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.white);
        buttonPanel.setLayout(new GridLayout(4,1,50,10));
        buttonPanel.setBorder(new EmptyBorder(100,10,100,10));
        buttonPanel.add(btn_add);
        buttonPanel.add(btn_modify);
        buttonPanel.add(btn_delete);
        buttonPanel.add(btn_search);

        westPanel = new JPanel();
        westPanel.setBackground(Color.white);
        westPanel.setBorder(new EmptyBorder(0,20,0,20));
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.LINE_AXIS));
        westPanel.add(buttonPanel);
        westPanel.add(verticalSeparator);
        westPanel.setVisible(false);

        eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel ,BoxLayout.LINE_AXIS));
        eastPanel.setBackground(new Color(63, 121, 94));

        eastPanel.add(verticalSeparator2);
        eastPanel.add(addCityFrame);
        eastPanel.add(deleteCityFrame);
        eastPanel.add(searchCityFrame);
        eastPanel.add(modifyCityFrame);
        eastPanel.add(verticalSeparator2);


        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(161, 154, 154));
        centerPanel.setPreferredSize(new Dimension(100,100));
        centerPanel.setLayout(new GridLayout(1,1));
        centerPanel.setBorder(new EmptyBorder(5,5,5,5));
        centerPanel.add(sp);



        southPanel = new JPanel();
        southPanel.setBackground(new Color(187, 187, 189));
        southPanel.setPreferredSize(new Dimension(100,65));
        southPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(btn_save);
        southPanel.add(btn_import);
        southPanel.add(btn_loadData);
        southPanel.add(btn_reset);

        container.add(eastPanel, BorderLayout.EAST);
        container.add(northPanel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);
        container.add(westPanel, BorderLayout.WEST);
        container.add(southPanel, BorderLayout.SOUTH);

    }

    void add(ActionEvent e){
        if (b_add) {
        addCityFrame.setVisible(false);
        b_add = false ;
    }
    else
    {
        addCityFrame.setVisible(true);
        modifyCityFrame.setVisible(false);
        deleteCityFrame.setVisible(false);
        searchCityFrame.setVisible(false);
        b_add = true ;
    }}
    void search(ActionEvent e){
        if (b_search) {
            searchCityFrame.setVisible(false);
            b_search = false ;
        }
        else
        {
            searchCityFrame.setVisible(true);
            modifyCityFrame.setVisible(false);
            addCityFrame.setVisible(false);
            deleteCityFrame.setVisible(false);
            b_search = true ;
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            tableModel = (DefaultTableModel) table.getModel();

            searchCityFrame.btn_search.addActionListener(a -> {
                reset(e);
                serviceVille = new ServiceVille(Path.of(choosedFile.getName()));
                Ville v = serviceVille.chercherVille(searchCityFrame.txt_search.getText());
                tableModel.addRow(new String[] {String.valueOf(v.getId()),v.getNom(), String.valueOf(v.getPopulation()),v.getRegion()});
            });
        }
    }
    void modify(ActionEvent e){
        if (b_modify) {
            modifyCityFrame.setVisible(false);
            b_modify = false;
        } else {
            modifyCityFrame.setVisible(true);
            modifyCityFrame.txt_region.setText("");
            modifyCityFrame.txt_population.setText("");
            modifyCityFrame.txt_nom.setText("");
            modifyCityFrame.txt_id.setText("");
            addCityFrame.setVisible(false);
            deleteCityFrame.setVisible(false);
            searchCityFrame.setVisible(false);

            String nom = JOptionPane.showInputDialog(eastPanel, "Nom de la ville à modifier", "Input Alert", JOptionPane.QUESTION_MESSAGE);
            ServiceVille serviceVille = new ServiceVille(Paths.get("villes.txt"));

            String oldline;
            if (serviceVille.chercherVille(nom).equals("Ville introuvable !"))
                JOptionPane.showMessageDialog(eastPanel, "la ville " + nom + " est introuvable !", "Info", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("icons/info.png"));
            else {
                for (Ville ville : villes) {
                    if (ville.getNom().equals(nom)) {
                        modifyCityFrame.txt_region.setText(ville.getRegion());
                        modifyCityFrame.txt_population.setText(String.valueOf(ville.getPopulation()));
                        modifyCityFrame.txt_id.setText(String.valueOf(ville.getId()));
                        modifyCityFrame.txt_nom.setText(ville.getNom());

                        oldline = ville.getId() + ":" + ville.getNom() + ":" + ville.getPopulation() + ":" + ville.getRegion();
                        System.out.println(oldline);

                        modifyCityFrame.btn_confirm.addActionListener(a ->{
                            String newline = modifyCityFrame.txt_id.getText() + ":" + modifyCityFrame.txt_nom.getText() + ":" + modifyCityFrame.txt_population.getText() + ":" + modifyCityFrame.txt_region.getText() +"\n";
                            System.out.println(newline);


                            Path fichier = Paths.get("ville1.txt");
                            String ligne;
                            for (Ville v : villes)
                            {
                                if (!v.getNom().equals(nom)) {
                                    try {
                                        ligne = v.getId() + ":" + v.getNom() + ":" + v.getPopulation() + ":" + v.getRegion()+ "\n";
                                        Files.write(fichier, ligne.getBytes(),
                                                StandardOpenOption.APPEND, // ajoute à la fin
                                                StandardOpenOption.CREATE  // ajoute le fichier s'il n'existe pas sinon, rien ne change
                                        );
                                    } catch (IOException ex) {
                                        System.out.println(ex.getMessage());
                                    }
                                }
                                else {
                                    try {
                                        Files.write(fichier, newline.getBytes(),
                                                StandardOpenOption.APPEND, // ajoute à la fin
                                                StandardOpenOption.CREATE  // ajoute le fichier s'il n'existe pas sinon, rien ne change
                                        );
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            }
                            try {
                                Files.deleteIfExists(Paths.get("villes.txt"));
                                Files.move(fichier, fichier.resolveSibling("villes.txt"));
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            modifyCityFrame.txt_id.setText("");
                            modifyCityFrame.txt_nom.setText("");
                            modifyCityFrame.txt_population.setText("");
                            modifyCityFrame.txt_region.setText("");

                        });
                    }
                }
            }
        }
    }
    void delete(ActionEvent e){
        if (b_delete) {
            deleteCityFrame.setVisible(false);
            b_delete = false ;
        }
        else
        {
            deleteCityFrame.setVisible(true);
            modifyCityFrame.setVisible(false);
            addCityFrame.setVisible(false);
            searchCityFrame.setVisible(false);
            b_delete = true ;
        }
    }
    void loadData(ActionEvent e){
        reset(e);
        villes = serviceVille.lire_bd_villes_NIO();
        for (Ville v:villes) {
            tableModel.addRow(new String[] {String.valueOf(v.getId()),v.getNom(), String.valueOf(v.getPopulation()),v.getRegion()});
        }
    }
    void reset(ActionEvent e) {
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }
    }

    void initActionListener(){

        btn_loadData.addActionListener(this::loadData);
        loadMenu.addActionListener(this::loadData);

        btn_add.addActionListener(this::add);
        addMenu.addActionListener(this::add);

        exitMenu.addActionListener(e -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));

        btn_menu.addActionListener(e->{
                    if(choosedFile.getName().equals("villes.txt"))
                    {
                        if (b_menu) {
                            westPanel.setVisible(false);
                            b_menu = false ;
                        }
                        else
                        {
                            westPanel.setVisible(true);
                            b_menu = true ;
                        }
                    }
                    else westPanel.setVisible(false);

        });

        btn_reset.addActionListener(this::reset);

        btn_search.addActionListener(this::search);
        searchMenu.addActionListener(this::search);

        btn_modify.addActionListener(this::modify);
        modifyMenu.addActionListener(this::modify);

        btn_delete.addActionListener(this::delete);
        deleteMenu.addActionListener(this::delete);

        btn_import.addActionListener(e-> initFileChooser());

        btn_save.addActionListener(e-> initFileSave());
    }

    CityManager() {
        super("City Manager");
        setBackground(new Color(255, 255, 255));
        initLabels();
        initTable();
        initButtons();
        initMenu();
        initSeparator();
        initPanels();
        initActionListener();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200,650));
        setLocationRelativeTo(null);
        setVisible(true);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setJMenuBar(menuBar);
    }

    public static void main(String[] args) {
        CityManager cityManager = new CityManager();
    }
}