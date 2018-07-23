package khan.lab03_sec_a_api_19;

/**
 * Created by Khan on 04-Feb-17.
 */

public class Song
{
    public int SongId;
    public String Title;
    public String Artist;

    public int getSongId() {
        return SongId;
    }

    public String getTitle() {
        return Title;
    }

    public String getArtist() {
        return Artist;
    }

    public Song( int id, String title, String artist)
    {
        this.SongId = id;
        this.Title = title;
        this.Artist= artist;
    }

}
