package test;

import METIER.ServiceVille;
import MODEL.Ville;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ServiceVille serviceVille = new ServiceVille(Paths.get("villes.txt"));

        List<Ville> villes = serviceVille.lire_bd_villes_NIO();

        serviceVille.lire_bd_villes_IO().forEach(System.out::println);

        serviceVille.lire_bd_villes_Scanner().forEach(System.out::println) ;

        serviceVille.tri_villes(villes);
    }
}