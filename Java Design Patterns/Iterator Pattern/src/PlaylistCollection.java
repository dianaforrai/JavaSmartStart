import java.util.ArrayList;
import java.util.List;

public class PlaylistCollection {
    private List<Song> songs;

    public PlaylistCollection() {
        songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public Iterator getIterator() {
        return new SongIterator(songs);
    }
}
