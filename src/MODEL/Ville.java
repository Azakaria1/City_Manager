package MODEL;

import java.util.Objects;

public class Ville implements  Comparable<Ville>{
    private int id;
    private String nom;
    private long population;
    private String region;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Ville "+ id +
                ", nom=" + nom  +
                ", population=" + population +
                ", region=" + region ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ville ville = (Ville) o;
        return id == ville.id && population == ville.population && Objects.equals(nom, ville.nom) && Objects.equals(region, ville.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, population, region);
    }

    public long getPopulation() {
        return population;
    }

    public String getRegion() {
        return region;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    @Override
    public int compareTo(Ville ville) {
        return this.getNom().compareTo(ville.getNom());
    }

}

