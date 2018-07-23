package khan.lab03_sec_a_api_19;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Khan on 04-Feb-17.
 */

public class SongAdapter extends ArrayAdapter<Song>
{
    public SongAdapter(Context context, ArrayList<Song> songs)
    {
        super(context,0,songs);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Song song = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song,parent,false);
        }

        TextView sid = (TextView) convertView.findViewById(R.id.songid);
        TextView stitle = (TextView) convertView.findViewById(R.id.songtitle);

        sid.setText( song.SongId+"" );
        stitle.setText(song.Title);
        return convertView;

    }
}
