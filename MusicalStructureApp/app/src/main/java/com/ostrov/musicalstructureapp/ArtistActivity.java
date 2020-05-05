package com.ostrov.musicalstructureapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ostrov.musicalstructureapp.databinding.ActivityArtistBinding;

import java.util.ArrayList;

public class ArtistActivity extends AppCompatActivity {
    private ArrayList<Artist> artists;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        menu.findItem(R.id.menu_artists).setVisible(false);
        menu.findItem(R.id.menu_albums).setVisible(false);

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityArtistBinding binding = ActivityArtistBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        addSongs();
        ArtistAdapter adapter = new ArtistAdapter(getApplicationContext(), artists, item -> {
            // start AlbumActivity
            Intent intent = new Intent(ArtistActivity.this, AlbumActivity.class);
            intent.putExtra(getString(R.string.artists), item);
            startActivity(intent);
        });
        binding.rvArtists.setAdapter(adapter);

        GridLayoutManager manager = new GridLayoutManager(getApplicationContext(),3);
        binding.rvArtists.setLayoutManager(manager);

        binding.tvArtistPlay.setOnClickListener(clickView -> {
            // create playlist from all artists
            Playlist playlist = getSongsList();

            // start PlaylistActivity
            Intent intent = new Intent(ArtistActivity.this, PlaylistActivity.class);
            intent.putExtra(getString(R.string.playlist), playlist);
            startActivity(intent);
        });
    }

    /**
     * Create playlist from all artists
     * @return playlist of songs
     */
    private Playlist getSongsList() {
        Playlist playlist = new Playlist(Parent.ALL);
        for (Artist a : artists)
            for (Album al : a.getAlbums())
                playlist.setSongs(al.getSongs());

        return playlist;
    }

    /**
     * Create list of songs
     */
    private void addSongs() {
        artists = new ArrayList<>();

        addNewSong("Alice Merton", "Mint", 2019, "Learn To Live");
        addNewSong("Alice Merton", "Mint", 2019, "2 Kids");
        addNewSong("Alice Merton", "Mint", 2019, "No Roots");
        addNewSong("Alice Merton", "Mint", 2019, "Funny Business");
        addNewSong("Alice Merton", "Mint", 2019, "Homesick");
        addNewSong("Alice Merton", "Mint", 2019, "Lash Out");
        addNewSong("Alice Merton", "Mint", 2019, "Speak Your Mind");
        addNewSong("Alice Merton", "Mint", 2019, "I Don’t Hold A Grudge");
        addNewSong("Alice Merton", "Mint", 2019, "Honeymoon Heartbreak");
        addNewSong("Alice Merton", "Mint", 2019, "Trouble In Paradise");
        addNewSong("Alice Merton", "Mint", 2019, "Why So Serious");

        addNewSong("Ghost", "Prequelle", 2018, "Ashes");
        addNewSong("Ghost", "Prequelle", 2018, "Rats");
        addNewSong("Ghost", "Prequelle", 2018, "Faith");
        addNewSong("Ghost", "Prequelle", 2018, "See The Light");
        addNewSong("Ghost", "Prequelle", 2018, "Miasma");
        addNewSong("Ghost", "Prequelle", 2018, "Dance Macabre");
        addNewSong("Ghost", "Prequelle", 2018, "Pro Memoria");
        addNewSong("Ghost", "Prequelle", 2018, "Dance Macabre");
        addNewSong("Ghost", "Prequelle", 2018, "Helvetesfönster");
        addNewSong("Ghost", "Prequelle", 2018, "Life Eternal");

        addNewSong("Ghost", "Meliora", 2015, "Spirit");
        addNewSong("Ghost", "Meliora", 2015, "From The Pinnacle To The Pit");
        addNewSong("Ghost", "Meliora", 2015, "Cirice");
        addNewSong("Ghost", "Meliora", 2015, "Spöksonat");
        addNewSong("Ghost", "Meliora", 2015, "He Is");
        addNewSong("Ghost", "Meliora", 2015, "Mummy Dust");
        addNewSong("Ghost", "Meliora", 2015, "Majesty");
        addNewSong("Ghost", "Meliora", 2015, "Devil Church");
        addNewSong("Ghost", "Meliora", 2015, "Absolution");
        addNewSong("Ghost", "Meliora", 2015, "Deus In Absentia");

        addNewSong("Ghost", "Infestissumam", 2013, "Infestissumam");
        addNewSong("Ghost", "Infestissumam", 2013, "Per Aspera Ad Inferi");
        addNewSong("Ghost", "Infestissumam", 2013, "Secular Haze");
        addNewSong("Ghost", "Infestissumam", 2013, "Jigolo Har Megiddo");
        addNewSong("Ghost", "Infestissumam", 2013, "Ghuleh / Zombie Queen");
        addNewSong("Ghost", "Infestissumam", 2013, "Year Zero");
        addNewSong("Ghost", "Infestissumam", 2013, "Body And Blood");
        addNewSong("Ghost", "Infestissumam", 2013, "Idolatrine");
        addNewSong("Ghost", "Infestissumam", 2013, "Depth Of Satan's Eyes");
        addNewSong("Ghost", "Infestissumam", 2013, "Monstrance Clock");

        addNewSong("Ghost", "Opus Eponymous", 2010, "Deus Culpa");
        addNewSong("Ghost", "Opus Eponymous", 2010, "Con Clavi Con Dio");
        addNewSong("Ghost", "Opus Eponymous", 2010, "Ritual");
        addNewSong("Ghost", "Opus Eponymous", 2010, "Elizabeth");
        addNewSong("Ghost", "Opus Eponymous", 2010, "Stand by Him");
        addNewSong("Ghost", "Opus Eponymous", 2010, "Satan Prayer");
        addNewSong("Ghost", "Opus Eponymous", 2010, "Death Knell");
        addNewSong("Ghost", "Opus Eponymous", 2010, "Prime Mover");
        addNewSong("Ghost", "Opus Eponymous", 2010, "Genesis");

        addNewSong("Lady Gaga", "Joanne", 2016, "Diamond Heart");
        addNewSong("Lady Gaga", "Joanne", 2016, "A-Yo");
        addNewSong("Lady Gaga", "Joanne", 2016, "Joanne");
        addNewSong("Lady Gaga", "Joanne", 2016, "John Wayne");
        addNewSong("Lady Gaga", "Joanne", 2016, "Dancin' in Circles");
        addNewSong("Lady Gaga", "Joanne", 2016, "Perfect Illusion");
        addNewSong("Lady Gaga", "Joanne", 2016, "Million Reasons");
        addNewSong("Lady Gaga", "Joanne", 2016, "Sinner's Prayer");
        addNewSong("Lady Gaga", "Joanne", 2016, "Come to Mama");
        addNewSong("Lady Gaga", "Joanne", 2016, "Hey Girl");
        addNewSong("Lady Gaga", "Joanne", 2016, "Angel Down");
        addNewSong("Lady Gaga", "Joanne", 2016, "Grigio Girls");
        addNewSong("Lady Gaga", "Joanne", 2016, "Just Another Day");
        addNewSong("Lady Gaga", "Joanne", 2016, "Angel Down");

        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "Anything Goes");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "Cheek To Cheek");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "Nature Boy");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "I Can't Give You Anything But Love");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "I Won't Dance");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "Firefly");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "Lush Life");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "Sophisticated Lady");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "Let's Face The Music And Dance");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "But Beautiful");
        addNewSong("Lady Gaga", "Cheek To Cheek", 2014, "It Don't Mean A Thing");

        addNewSong("Lady Gaga", "Artpop", 2013, "Aura");
        addNewSong("Lady Gaga", "Artpop", 2013, "Venus");
        addNewSong("Lady Gaga", "Artpop", 2013, "G.U.Y.");
        addNewSong("Lady Gaga", "Artpop", 2013, "X Dreams");
        addNewSong("Lady Gaga", "Artpop", 2013, "Jewels N");
        addNewSong("Lady Gaga", "Artpop", 2013, "MANiCURE");
        addNewSong("Lady Gaga", "Artpop", 2013, "ARTPOP");
        addNewSong("Lady Gaga", "Artpop", 2013, "Swine");
        addNewSong("Lady Gaga", "Artpop", 2013, "Donatella");
        addNewSong("Lady Gaga", "Artpop", 2013, "Fashion!");
        addNewSong("Lady Gaga", "Artpop", 2013, "Mary Jane Holland");
        addNewSong("Lady Gaga", "Artpop", 2013, "Dope");
        addNewSong("Lady Gaga", "Artpop", 2013, "Gypsy");
        addNewSong("Lady Gaga", "Artpop", 2013, "Applause");

        addNewSong("Lady Gaga", "Born This Way", 2011, "Marry The Night");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Born This Way");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Government Hooker");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Judas");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Americano");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Hair");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Scheiße");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Bloody Mary");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Bad Kids");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Highway Unicorn (Road To Love)");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Heavy Metal Lover");
        addNewSong("Lady Gaga", "Born This Way", 2011, "Electric Chapel");
        addNewSong("Lady Gaga", "Born This Way", 2011, "You And I");
        addNewSong("Lady Gaga", "Born This Way", 2011, "The Edge Of Glory");

        addNewSong("Lady Gaga", "The Fame", 2008, "Just Dance");
        addNewSong("Lady Gaga", "The Fame", 2008, "LoveGame");
        addNewSong("Lady Gaga", "The Fame", 2008, "Paparazzi");
        addNewSong("Lady Gaga", "The Fame", 2008, "Poker Face");
        addNewSong("Lady Gaga", "The Fame", 2008, "Eh, Eh (Nothing Else I Can Say)");
        addNewSong("Lady Gaga", "The Fame", 2008, "Beautiful, Dirty, Rich");
        addNewSong("Lady Gaga", "The Fame", 2008, "The Fame");
        addNewSong("Lady Gaga", "The Fame", 2008, "Money Honey");
        addNewSong("Lady Gaga", "The Fame", 2008, "Starstruck");
        addNewSong("Lady Gaga", "The Fame", 2008, "Boys Boys Boys");
        addNewSong("Lady Gaga", "The Fame", 2008, "Paper Gangsta");
        addNewSong("Lady Gaga", "The Fame", 2008, "Brown Eyes");
        addNewSong("Lady Gaga", "The Fame", 2008, "I Like It Rough");
        addNewSong("Lady Gaga", "The Fame", 2008, "Summerboy");
        addNewSong("Lady Gaga", "The Fame", 2008, "Disco Heaven");

        addNewSong("The Phantoms", "The Fight", 2018, "Unstoppable Now");
        addNewSong("The Phantoms", "The Fight", 2018, "How It's Done");
        addNewSong("The Phantoms", "The Fight", 2018, "Tell Me How You Do It");
        addNewSong("The Phantoms", "The Fight", 2018, "Look At Me Now");
        addNewSong("The Phantoms", "The Fight", 2018, "Runnin' Wild");
        addNewSong("The Phantoms", "The Fight", 2018, "Don't Get No Better");
        addNewSong("The Phantoms", "The Fight", 2018, "Bad Things");
        addNewSong("The Phantoms", "The Fight", 2018, "I Can Already Tell");
        addNewSong("The Phantoms", "The Fight", 2018, "Pretty Much");
        addNewSong("The Phantoms", "The Fight", 2018, "Outlaw");
        addNewSong("The Phantoms", "The Fight", 2018, "The Fight");

        addNewSong("The Phantoms", "World Gone Mad", 2017, "Made For This");
        addNewSong("The Phantoms", "World Gone Mad", 2017, "Gonna Be A Legend");
        addNewSong("The Phantoms", "World Gone Mad", 2017, "Not Over Yet (It's Only Begun)");
        addNewSong("The Phantoms", "World Gone Mad", 2017, "Fight For My Survival");
        addNewSong("The Phantoms", "World Gone Mad", 2017, "Warrior");
        addNewSong("The Phantoms", "World Gone Mad", 2017, "Find You");
        addNewSong("The Phantoms", "World Gone Mad", 2017, "Get What I Came For");
        addNewSong("The Phantoms", "World Gone Mad", 2017, "World Gone Mad");

        addNewSong("The Phantoms", "Take The World", 2016, "Take The World (Let's Go)");
        addNewSong("The Phantoms", "Take The World", 2016, "Watch Me");
        addNewSong("The Phantoms", "Take The World", 2016, "Good As Gold");
        addNewSong("The Phantoms", "Take The World", 2016, "Can't Get Enough");
        addNewSong("The Phantoms", "Take The World", 2016, "Stand Out");
        addNewSong("The Phantoms", "Take The World", 2016, "Wild");

        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Brown Sugar");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Roll Over Beethoven");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "You Cant Always Get What You Want");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Mustang Sally");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Wasted Days And Wasted Nights");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Little Queenie");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Steiermark");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Crossroads");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Hey Joe");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Arbeit");
        addNewSong("The Phantoms", "The Best Of The Phantoms", 2015, "Tumbling Dice");

        addNewSong("The Phantoms", "Different Drum", 2014, "Different Drum");
        addNewSong("The Phantoms", "Different Drum", 2014, "Green = Go");
        addNewSong("The Phantoms", "Different Drum", 2014, "Am I Am");
        addNewSong("The Phantoms", "Different Drum", 2014, "It's A Beautiful Thing");
        addNewSong("The Phantoms", "Different Drum", 2014, "Dark Days");
        addNewSong("The Phantoms", "Different Drum", 2014, "Gonna Celebrate");
        addNewSong("The Phantoms", "Different Drum", 2014, "Bring-It");
        addNewSong("The Phantoms", "Different Drum", 2014, "All For One");
        addNewSong("The Phantoms", "Different Drum", 2014, "Rise");
        addNewSong("The Phantoms", "Different Drum", 2014, "Need (Ooh Lala)");
        addNewSong("The Phantoms", "Different Drum", 2014, "Season of the Witch");
    }

    /**
     * Create new Song object
     * @param artistName name of artist
     * @param albumName title of album
     * @param albumYear album year
     * @param songName title of song
     */
    private void addNewSong(String artistName, String albumName, int albumYear, String songName) {
        Artist artist = findArtist(artistName);
        Album album = findAlbum(artist, albumName, albumYear);
        Song song = findSong(album, songName);
    }

    /**
     * Add new artist to list of artists
     * @param artistName name of artist
     * @return artist object
     */
    private Artist findArtist(String artistName) {
        for (Artist a : artists)
            if (a.getTitle().equals(artistName))
                return a;
        Artist artist = new Artist(artistName);
        artists.add(artist);
        return artist;
    }

    /**
     * Add new album to list of albums
     * @param artist album artist
     * @param albumName name of artist
     * @param albumYear album year
     * @return album object
     */
    private Album findAlbum(Artist artist, String albumName, int albumYear) {
        for (Album a : artist.getAlbums())
            if (a.getTitle().equals(albumName))
                return a;
        return new Album(albumName, albumYear, artist);
    }

    /**
     * Add new song to list of songs
     * @param album song album
     * @param songName name of song
     * @return song object
     */
    private Song findSong(Album album, String songName) {
        for (Song s : album.getSongs())
            if (s.getTitle().equals(songName))
                return s;
        return new Song(songName, album);
    }
}
