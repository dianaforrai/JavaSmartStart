import java.util.List;

public class SongIterator implements Iterator {
    private List<Song> songs;
    private int position = 0;

    public SongIterator(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public Song next() {
        if (hasNext()) {
            return songs.get(position++);
        }
        return null;
    }
}
