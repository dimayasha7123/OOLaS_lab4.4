/**
 * |_|>
 * |_|>    Created by Dimyasha on 06.12.2021
 * |_|>
 */

package OOLaS_lab44.Catalog;

import java.io.Serializable;
import java.util.Objects;

public class Song implements Serializable {
    private final String name;
    private final String album;
    private final String artist;

    public Song(String name, String album, String artist) {
        this.name = name;
        this.album = album;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return getName().equals(song.getName()) && getAlbum().equals(song.getAlbum()) && getArtist().equals(song.getArtist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAlbum(), getArtist());
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", album='" + album + '\'' +
                ", artist='" + artist + '\'' +
                '}';
    }
}