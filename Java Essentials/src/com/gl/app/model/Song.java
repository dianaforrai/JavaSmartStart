package com.gl.app.model;
import java.util.*;

// Interface for playlist operations
interface Playable {
    boolean addSong(Song song);
    boolean removeSong(String title);
}

// Interface for user playlist management
interface Manageable {
    boolean createPlaylist(String name);
    boolean addSongToPlaylist(String playlistName, Song song);
    boolean removeSongFromPlaylist(String playlistName, String songTitle);
}

// Song class
class Song {
    private String title;
    private String artist;
    private double duration; // in minutes

    public Song(String title, String artist, double duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public double getDuration() { return duration; }

    @Override
    public String toString() {
        return String.format("%s by %s (%.2f mins)", title, artist, duration);
    }
}

// Playlist class
class Playlist implements Playable {
    private String name;
    private List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() { return name; }
    public List<Song> getSongs() { return songs; }

    @Override
    public boolean addSong(Song song) {
        if (song == null) {
            System.out.println("Cannot add a null song.");
            return false;
        }
        songs.add(song);
        return true;
    }

    @Override
    public boolean removeSong(String title) {
        return songs.removeIf(song -> song.getTitle().equalsIgnoreCase(title));
    }

    @Override
    public String toString() {
        return "Playlist: " + name + " | Songs: " + songs.size();
    }
}

// User class
class User implements Manageable {
    private String userId;
    private String username;
    private List<Playlist> playlists;

    public User(String userId, String username) {
        this.userId = userId;
        this.username = username;
        this.playlists = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public List<Playlist> getPlaylists() { return playlists; }

    @Override
    public boolean createPlaylist(String name) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equalsIgnoreCase(name)) {
                System.out.println("Playlist with name '" + name + "' already exists.");
                return false;
            }
        }
        playlists.add(new Playlist(name));
        return true;
    }

    @Override
    public boolean addSongToPlaylist(String playlistName, Song song) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equalsIgnoreCase(playlistName)) {
                return playlist.addSong(song);
            }
        }
        System.out.println("Playlist '" + playlistName + "' not found.");
        return false;
    }

    @Override
    public boolean removeSongFromPlaylist(String playlistName, String songTitle) {
        for (Playlist playlist : playlists) {
            if (playlist.getName().equalsIgnoreCase(playlistName)) {
                return playlist.removeSong(songTitle);
            }
        }
        System.out.println("Playlist '" + playlistName + "' not found.");
        return false;
    }

    @Override
    public String toString() {
        return "User: " + username + " (" + userId + ")";
    }
}

// MusicService class
class MusicService {
    private Map<String, Song> songs;
    private Map<String, User> users;

    public MusicService() {
        songs = new HashMap<>();
        users = new HashMap<>();
    }

    public boolean addSong(String id, Song song) {
        if (songs.containsKey(id)) {
            System.out.println("Song with ID '" + id + "' already exists.");
            return false;
        }
        songs.put(id, song);
        return true;
    }

    public boolean addUser(User user) {
        if (users.containsKey(user.getUserId())) {
            System.out.println("User with ID '" + user.getUserId() + "' already exists.");
            return false;
        }
        users.put(user.getUserId(), user);
        return true;
    }

    public Song getSongById(String id) {
        return songs.get(id);
    }

    public User getUserById(String id) {
        return users.get(id);
    }
}

// Example usage
class Main {
    public static void main(String[] args) {
        MusicService service = new MusicService();

        // Add songs
        service.addSong("S1", new Song("Shape of You", "Ed Sheeran", 4.24));
        service.addSong("S2", new Song("Blinding Lights", "The Weeknd", 3.20));

        // Add a user
        User user1 = new User("U1", "Alice");
        service.addUser(user1);

        // Create playlist
        user1.createPlaylist("Favorites");

        // Add song to playlist
        Song song = service.getSongById("S1");
        user1.addSongToPlaylist("Favorites", song);

        // Attempt to add non-existent song
        user1.addSongToPlaylist("Favorites", service.getSongById("S99"));

        // Remove song from playlist
        user1.removeSongFromPlaylist("Favorites", "Shape of You");

        // Display user info
        System.out.println(user1);
        for (Playlist playlist : user1.getPlaylists()) {
            System.out.println(playlist);
            for (Song s : playlist.getSongs()) {
                System.out.println("  - " + s);
            }
        }
    }
}
