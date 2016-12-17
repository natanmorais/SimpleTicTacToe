package com.example.fir3destr0yer.tictactoe;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChooseIcons extends AppCompatActivity
{

    public ArrayList<CardView> cards = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TextView coinsText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icons);
        mRecyclerView = (RecyclerView)findViewById(R.id.my_recycler_view);

        coinsText = (TextView)findViewById(R.id.Coins);

        SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);

        int coins = settings.getInt("Coins", 0);
        coinsText.setText(coinsText.getText().toString() + coins);

        ArrayList<Icones> icons = new ArrayList<>();

        Icones i = new Icones(R.drawable.light_o, R.drawable.light_x, "Light Icons", true, true, settings.getInt("Light Icons", 0));
        icons.add(i);
        i = new Icones(R.drawable.hard_o, R.drawable.hard_x, "Hard Icons", false, false, settings.getInt("Hard Icons", 200));
        icons.add(i);
        i = new Icones(R.drawable.basic2_o, R.drawable.basic2_x, "Basic Icons", false, false, settings.getInt("Basic Icons", 400));
        icons.add(i);
        i = new Icones(R.drawable.cromo_o, R.drawable.cromo_x, "Cromo Icons", false, false, settings.getInt("Cromo Icons", 1000));
        icons.add(i);
        i = new Icones(R.drawable.crystal_o, R.drawable.crystal_x, "Crystal Icons", false, false, settings.getInt("Crystal Icons", 1500));
        icons.add(i);
        i = new Icones(R.drawable.logo3d_o, R.drawable.logo3d_x, "Logo 3D Icons", false, false, settings.getInt("Logo 3D Icons", 2000));
        icons.add(i);
        i = new Icones(R.drawable.texture_o, R.drawable.texture_x, "Texture Icons", false, false, settings.getInt("Texture Icons", 3000));
        icons.add(i);
        i = new Icones(R.drawable.neon_o, R.drawable.neon_x, "Neon Icons", false, false, settings.getInt("Neon Icons", 10000));
        icons.add(i);

        mRecyclerView.setAdapter(new MyAdapter(icons, this, this));

        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layout);

    }

    public void atualiza()
    {
        super.onUserInteraction();
        SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
        int coins = settings.getInt("Coins", 0);
        coinsText.setText("Coins: " + coins);
    }

}
