package com.example.fir3destr0yer.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.multidex.MultiDex;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends Activity
{

    public ImageView single, two, icons, logo;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /*Para teste..

        setContentView(R.layout.activity_partida);

        Tabuleiro tabuleiro = (Tabuleiro)findViewById(R.id.tabuleiro);
        tabuleiro.setDimensao(6);
        tabuleiro.setOnTabuleiroListener(new Tabuleiro.OnTabuleiroListener()
        {
            @Override
            public void onJogadaFinalizada(Tabuleiro view, int jogador, int row, int col)
            {
                Toast.makeText(MainActivity.this, String.format("jogador: %d row: %d col: %d", jogador, row, col), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSemJogadas(Tabuleiro view)
            {
                Toast.makeText(MainActivity.this, "FIM", Toast.LENGTH_SHORT).show();
            }
        });

        if(true) return;

        */
        logo = (ImageView)findViewById(R.id.Logo);
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

        MobileAds.initialize(getApplicationContext(), "ca-app-pub-5374403399295176~6513503249");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }


}
