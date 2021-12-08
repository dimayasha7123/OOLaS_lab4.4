/**
 * |_|>
 * |_|>    Created by Dimyasha on 06.12.2021
 * |_|>
 */

package OOLaS_lab44.Catalog;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Catalog implements Serializable {
    private String name;
    private Map<String, Disc> discs;

    public Catalog() {
    }

    public Catalog(String name) {
        this.name = name;
        discs = new HashMap<>();
    }

    public Catalog(String name, Map<String, Disc> discs) {
        this.name = name;
        this.discs = discs;
    }

    public String getName() {
        return name;
    }

    public boolean addDisc(Disc disc) {
        if (discs.containsValue(disc)) return false;
        discs.put(disc.getName(), disc);
        return true;
    }

    public Disc deleteDisc(String title) {
        return discs.remove(title);
    }

    public Disc getDisc(String title) {
        return discs.get(title);
    }


    public ArrayList<Song> searchByArtist(String artist) {
        return discs.entrySet().stream().flatMap(d
                        -> d.getValue().getSongs().stream()
                        .filter(s
                                -> s.getArtist().toLowerCase().contains(artist.toLowerCase())))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return getName().equals(catalog.getName()) && discs.equals(catalog.discs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), discs);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        discs.values().forEach((t) -> sb.append("\n").append(t));
        return sb.toString();
    }


    public void SaveTo(String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(this);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ReadFrom(String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            Catalog catalog = ((Catalog) ois.readObject());
            name = catalog.name;
            discs = new HashMap<>(catalog.discs);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}