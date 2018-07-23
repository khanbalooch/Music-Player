package khan.lab03_sec_a_api_19;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

public class Activity2 extends AppCompatActivity {

    MediaPlayer mp;
    Button play_resume,replay_reset;
    TextView timeShow;
    SeekBar seekBar,volumeBar;
    Handler seekHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Initialize();
        seekUpdation();
    }

    Runnable run = new Runnable()
    {
        @Override
        public void run() {
            seekUpdation();
        }
    };
    public void seekUpdation() {

        seekBar.setProgress(mp.getCurrentPosition());
        seekHandler.postDelayed(run, 100);
        timeShow.setText(getTimeString(mp.getCurrentPosition()));
        //timeShow.setText( DateUtils.formatElapsedTime(mp.getCurrentPosition()));
    }
    private String getTimeString(long millis) {
        StringBuffer buf = new StringBuffer();

        int hours = (int) (millis / (1000 * 60 * 60));
        int minutes = (int) ((millis % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((millis % (1000 * 60 * 60)) % (1000 * 60)) / 1000);

        buf
                .append(String.format("%02d", hours))
                .append(":")
                .append(String.format("%02d", minutes))
                .append(":")
                .append(String.format("%02d", seconds));

        return buf.toString();
    }
    public void Initialize()
    {
        String filename = getIntent().getStringExtra("SongName");
        Uri path = Uri.parse("android.resource://com.my.package/raw/"+filename);

        TextView songName = (TextView) findViewById(R.id.SongName);
        songName.setText(filename);

        play_resume = (Button)findViewById(R.id.play_resume);
        replay_reset = (Button) findViewById(R.id.replay_reset);
        replay_reset.setEnabled(false);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        volumeBar = (SeekBar) findViewById(R.id.volumeBar);
        volumeBar.setMax(100);
        volumeBar.setProgress(100);
        timeShow = (TextView)findViewById(R.id.timeShow);
        mp = MediaPlayer.create(this,R.raw.azan_1);
        seekBar.setMax(mp.getDuration());
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                replay_reset.setBackgroundResource(R.drawable.replay);
                play_resume.setBackgroundResource(R.drawable.ic_media_play);
            }
        });
        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mp.setVolume(progress*0.01f,progress*0.01f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                if(fromUser)
                {
                    mp.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }
    private void gotoPauseState()
    {
        mp.pause();
        replay_reset.setBackgroundResource(R.drawable.replay);
        play_resume.setBackgroundResource(R.drawable.ic_media_play);
    }
    private void gotoPlayState()
    {
        mp.start();
        replay_reset.setBackgroundResource(R.drawable.ic_media_stop);
        replay_reset.setEnabled(true);
        play_resume.setBackgroundResource(R.drawable.ic_media_pause);
    }
    private void gotoReadyState()
    {
        gotoPauseState();
        mp.seekTo(0);
    }
    public void setPlay_resume(View view)
    {
        if(mp.isPlaying())
        {
            gotoPauseState();
        }else
        {
            gotoPlayState();
        }

    }
    public void setReplay_reset(View view )
    {
        if(mp.isPlaying())
        {
            gotoReadyState();
        }else
        {
            gotoReadyState();
            gotoPlayState();
        }

    }

}