/**
 * |_|>
 * |_|>    Created by Dimyasha on 06.12.2021
 * |_|>
 */

package OOLaS_lab44.Catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import UTFE.TableOutput.Table;

public class Disc implements Serializable {
    private final String name;

    public ArrayList<Song> getSongs() {
        return songs;
    }

    private final ArrayList<Song> songs;

    public Disc(String name) {
        this.name = name;
        songs = new ArrayList<>();
    }

    public Disc(String name, ArrayList<Song> songs) {
        this.name = name;
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void addSong(Song song) {
        addSong(song, songs.size());
    }

    public void addSong(Song song, int place) {
        songs.add(place, song);
    }

    public Song deleteAt(int index) {
        return songs.remove(index);
    }

    public boolean delete(Object o) {
        return songs.remove(o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disc disc = (Disc) o;
        return getName().equals(disc.getName()) && songs.equals(disc.songs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), songs);
    }

    @Override
    public String toString() {
        if (songs.size() == 0) return name + ":\nДиск пуст.";
        ArrayList<Object[]> table = new ArrayList<>();
        table.add(new Object[]{"#", "name&artist", "album"});
        final int[] it = {1};
        songs.forEach(t -> table.add(new Object[] {it[0]++, t.getName() + "\n" + t.getArtist(), t.getAlbum()}));
        return name + ":\n" + Table.TableToString(table.toArray(Object[][]::new));
    }
}