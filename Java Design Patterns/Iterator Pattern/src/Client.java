public class Client {
    public static void main(String[] args) {
        // Create playlist
        PlaylistCollection playlist = new PlaylistCollection();
        playlist.addSong(new Song("Shape of You", "Ed Sheeran"));
        playlist.addSong(new Song("Blinding Lights", "The Weeknd"));
        playlist.addSong(new Song("Levitating", "Dua Lipa"));
        playlist.addSong(new Song("Someone You Loved", "Lewis Capaldi"));

        // Get iterator
        Iterator iterator = playlist.getIterator();

        // Traverse and display songs
        System.out.println("Traversing playlist:");
        while (iterator.hasNext()) {
            Song song = iterator.next();
            System.out.println(song);
        }
    }
}
