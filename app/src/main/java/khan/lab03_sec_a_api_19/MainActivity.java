package khan.lab03_sec_a_api_19;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {

    private ArrayList<Song> songList = new ArrayList<Song>();
    private ListView songView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSongList();

        SongAdapter  songadapter = new SongAdapter(this,songList);

        songView = (ListView) findViewById(R.id.musiclist);

        songView.setAdapter(songadapter);
    }
    private void getSongList()
    {
        //songList = new ArrayList<Song>();
        Field[] fields=R.raw.class.getFields();
        for(int count=1; count < fields.length-1; count++)
        {
            songList.add(new Song(count ,fields[count].getName(),"Artisit1"));
        }
    }
    public void songPicked(View view)
    {
        TextView songTitle = (TextView) view.findViewById(R.id.songtitle);
        Intent i = new Intent(this,Activity2.class);
        i.putExtra("SongName", songTitle.getText().toString()  );
        startActivity(i);
    }
}
