package com.example.assign07audio6206021611125;

import androidx.appcompat.app.AppCompatActivity;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnItemClickListener, OnClickListener {
    ListView listView;
    String[] items = {"1 Billionaire", "2 I Wont Give Up", "3 Adore You", "4 Royals", "5 Thinking About You"};
    int resId = R.raw.music01;
    MediaPlayer mPlayer;
    boolean isPause = false;
    ImageView show;
    TextView artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, items);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        ImageButton btnPlay = (ImageButton) findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(this);
        ImageButton btnPause = (ImageButton) findViewById(R.id.btnPause);
        btnPause.setOnClickListener(this);
        ImageButton btnStop = (ImageButton) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);
        mPlayer = MediaPlayer.create(this, resId);
        show = (ImageView) findViewById(R.id.show);
        artist = (TextView) findViewById(R.id.artist);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int id, long l) {
        switch (id){
            case 0:
                resId = R.raw.music01;
                show.setImageResource(R.drawable.img);
                artist.setText("Travie McCoy ft. Bruno Mars");
                break;
            case 1:
                resId = R.raw.music02;
                show.setImageResource(R.drawable.img_1);
                artist.setText("Jason Mraz");
                break;
            case 2:
                resId = R.raw.music03;
                show.setImageResource(R.drawable.img_2);
                artist.setText("Miley Cyrus");
                break;
            case 3:
                resId = R.raw.music04;
                show.setImageResource(R.drawable.img_3);
                artist.setText("Lorde");
                break;
            case 4:
                resId = R.raw.music05;
                show.setImageResource(R.drawable.img_4);
                artist.setText("Frank Ocean");
                break;
        }
        if ((mPlayer != null) && (mPlayer.isPlaying())){
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
        mPlayer = MediaPlayer.create(this, resId);
        isPause = false;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnPlay) {
            if (mPlayer == null){
                mPlayer = MediaPlayer.create(this, resId);
                mPlayer.start();
            }
            else {
                if (!mPlayer.isPlaying()){
                    if(isPause){
                        mPlayer.start();
                    }
                    else {
                        mPlayer = MediaPlayer.create(this, resId);
                        mPlayer.start();
                    }
                }
            }
        } else if (view.getId() == R.id.btnStop) {
            if ((mPlayer != null) && (mPlayer.isPlaying())) {
                mPlayer.stop();
                mPlayer.release();
                mPlayer = null;
                isPause = false;
            }
        } else if (view.getId() == R.id.btnPause) {
            if (mPlayer.isPlaying()) {
                mPlayer.pause();
                isPause = true;
            }
        }
    }
}