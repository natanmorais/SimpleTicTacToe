package com.example.fir3destr0yer.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity
{

    public ImageView single, two, icons;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        single = (ImageView)findViewById(R.id.Single);
        two = (ImageView)findViewById(R.id.Two);
        icons = (ImageView)findViewById(R.id.Choose);

        single.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, SinglePlayer.class);
                startActivity(intent);
                finish();
            }
        });
        two.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, TwoPlayers.class);
                startActivity(intent);
                finish();
            }
        });

        icons.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, ChooseIcons.class);
                startActivity(intent);
            }
        });

        SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
        int coins = settings.getInt("Coins", 0);
    }


}
