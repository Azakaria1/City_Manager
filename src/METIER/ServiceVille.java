package METIER;

import MODEL.Ville;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class ServiceVille {
    Path source;


    public ServiceVille(Path source) {
        this.source = source;
    }

    public void ajouterVille(Ville ville){
        Path fichier = Paths.get("villes.txt");
        try {
            String ligne = "\n"+ ville.getId() +":"+ ville.getNom() +":"+ ville.getPopulation() +":" + ville.getRegion() ;
            Files.write(fichier,ligne.getBytes(),
                    StandardOpenOption.APPEND , // ajoute à la fin
                    StandardOpenOption.CREATE  // ajoute le fichier s'il n'existe pas sinon, rien ne change
            );
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Ville chercherVille(String nom){

            List<Ville> villes = lire_bd_villes_NIO();
            for (Ville v : villes) {
                if (v.getNom().equals(nom))
                {
                    return v ;
                }
            }
            return null ;
        }


    public void supprimerVille(String nom){
        Path fichier = Paths.get("villes.txt");
        try {
            ServiceVille serviceVille = new ServiceVille(fichier);

            List<Ville> villes = serviceVille.lire_bd_villes_NIO();
            for (Ville v:villes) {
                if (v.getNom().equals(nom))
                {
                    villes.remove(v);
                    try {
                        Files.delete(fichier);
                        Files.createFile(fichier);
                    }  catch (IOException e) {
                        e.printStackTrace();
                    }
                    for (Ville ville1 :villes) {
                        ajouterVille(ville1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ville> lire_bd_villes_NIO () {

        Path path = Paths.get(source.toFile().toString());
        List<Ville> villes = new ArrayList<>();
        Ville ville = new Ville();
        List<String> lines = null;

        try {
            lines = Files.readAllLines(path);
            for (int i = 1; i < lines.size(); i++) {
                StringTokenizer st = new StringTokenizer(lines.get(i), ":");
                Ville v = new Ville();
                v.setId(Integer.parseInt(st.nextToken()));
                v.setNom(st.nextToken());
                v.setPopulation(Integer.parseInt(st.nextToken()));
                v.setRegion(st.nextToken());
                villes.add(v);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return villes ;
    }

    public List<Ville> lire_bd_villes_Scanner() throws IOException {
        List<Ville> villes = new ArrayList<>();
        Scanner scanner = new Scanner(Paths.get("villes.txt"));
        scanner.nextLine();
        while (scanner.hasNextLine()) {
                StringTokenizer stringTokenizer = new StringTokenizer(scanner.nextLine(), ":");
                while (stringTokenizer.hasMoreTokens()) {
                    Ville ville = new Ville();

                    ville.setId(Integer.parseInt(stringTokenizer.nextToken()));
                    ville.setNom(stringTokenizer.nextToken());
                    ville.setPopulation(Long.parseLong(stringTokenizer.nextToken()));
                    ville.setRegion(stringTokenizer.nextToken());
                    villes.add(ville);

                }
        }
        return villes;
    }

    public List<Ville> lire_bd_villes_IO() {
            List<Ville> villes = new ArrayList<>();
            File fichier = new File(String.valueOf(source.toFile()));
            System.out.println(fichier.exists());
            System.out.println(fichier.exists() ? "Fichier test.txt existe" : "Fichier introuvable");
            InputStream inputStream = null;

            try {
                System.out.println("ouverture de flux pour lecture");
                inputStream = new FileInputStream(fichier);
                System.out.println("Flux pour lecture ouvert !");
                List<String> lignes = new ArrayList<>();
                int i;
                String ligne = "";
                while ((i = inputStream.read()) != -1) {
                    if (i != '\n')
                    {
                        ligne += (char) i;
                    }
                    else {
                        lignes.add(ligne);
                        ligne = "";
                    }

                }
                System.out.println(lignes);
                for (int j = 1; j < lignes.size(); j++) {
                    StringTokenizer st = new StringTokenizer(lignes.get(j), ":");
                    Ville v = new Ville();
                    v.setId(Integer.parseInt(st.nextToken()));
                    v.setNom(st.nextToken());
                    v.setPopulation(Integer.parseInt(st.nextToken()));
                    v.setRegion(st.nextToken());
                    villes.add(v);
                }
/*                for (int j = 1; j < lignes.size(); j++) {

                    strings = lignes.get(j).split(":");
                    Ville ville = new Ville();

                    ville.setId(Integer.parseInt(strings[0]));
                    ville.setNom(strings[1]);
                    ville.setPopulation(Long.parseLong(strings[2]));
                    ville.setRegion(strings[3]);

                    villes.add(ville);
*/
            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());
            } catch (IOException e) {
                System.err.println(e.getMessage());
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                        System.out.println("fermeture de fichier réussite !");
                    }
                } catch (IOException e) {
                    System.err.println("Problème dans la feermeture du fichier !!");
                }
            }
            return villes;
        }

    public void tri_villes (List<Ville> villes) {
            Collections.sort(villes, Comparator.comparing(Ville::getNom));
            try {
                for (int i = 0; i < villes.size(); i++) {
                    String  ligne="\n"+villes.get(i).toString();
                    Files.write(Path.of("villesParNom.txt"), ligne.getBytes(),
                            StandardOpenOption.CREATE  );
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }

            Collections.sort(villes, Comparator.comparing(Ville::getPopulation));
        try {
            for (int i = 0; i < villes.size(); i++) {
                String  ligne="\n"+villes.get(i).toString();
                Files.write(Path.of("villesParPopulation.txt"), ligne.getBytes(),
                        StandardOpenOption.CREATE  );
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
            Collections.sort(villes, Comparator.comparing(Ville::getRegion));
        try {
            for (int i = 0; i < villes.size(); i++) {
                String  ligne="\n"+villes.get(i).toString();
                Files.write(Path.of("villesParRegion.txt"), ligne.getBytes(),
                        StandardOpenOption.CREATE  );
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        }
    }