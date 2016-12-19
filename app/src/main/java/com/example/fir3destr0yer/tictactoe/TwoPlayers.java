package com.example.fir3destr0yer.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class TwoPlayers extends AppCompatActivity
{

    Tela t;
    int[][] tabuleiro = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
    boolean vezdox = true;
    int w, h, jogadas = 0, p1 = 0, p2 = 0;
    Canvas can;
    int pO, pX;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        t = new Tela(this);
        setContentView(t);

        SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
        String selected = settings.getString("Selected", "Light Icons");
        if(selected.equals("Light Icons"))
        {
            pO = R.drawable.light_o;
            pX = R.drawable.light_x;
        }
        else if(selected.equals("Hard Icons"))
        {
            pO = R.drawable.hard_o;
            pX = R.drawable.hard_x;
        }
        else if(selected.equals("Basic Icons"))
        {
            pO = R.drawable.basic2_o;
            pX = R.drawable.basic2_x;
        }
        else if(selected.equals("Cromo Icons"))
        {
            pO = R.drawable.cromo_o;
            pX = R.drawable.cromo_x;
        }
        else if(selected.equals("Crystal Icons"))
        {
            pO = R.drawable.crystal_o;
            pX = R.drawable.crystal_x;
        }
        else if(selected.equals("Logo 3D Icons"))
        {
            pO = R.drawable.logo3d_o;
            pX = R.drawable.logo3d_x;
        }
        else if(selected.equals("Neon Icons"))
        {
            pO = R.drawable.neon_o;
            pX = R.drawable.neon_x;
        }
        else if(selected.equals("Texture Icons"))
        {
            pO = R.drawable.texture_o;
            pX = R.drawable.texture_x;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Intent intent = new Intent(TwoPlayers.this, MainActivity.class);
            TwoPlayers.this.startActivity(intent);
            TwoPlayers.this.finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    class Tela extends View
    {
        Context con;

        Tela(Context c)
        {
            super(c);
            con = c;
        }

        @Override
        public boolean onTouchEvent(MotionEvent me)
        {
            if(me.getAction() == MotionEvent.ACTION_UP)
            {
                float x = me.getX();
                float y = me.getY();
                for(int n = 0; n < 3; n++)
                    for(int m = 0; m < 3; m++)
                        if(x > m * (w / 3) && x < (m + 1) * (w / 3) && y > ((h - w) / 2) + (n * (w / 3) + 2*(h/25)) && y < ((h - w) / 2) + ((n + 1) * (w / 3)) + 2*(h/25))
                            if(tabuleiro[m][n] == 0)
                            {
                                tabuleiro[m][n] = (vezdox ? 1 : 2);
                                vezdox = !vezdox;
                                jogadas++;
                                checaFim();
                            }
                t.invalidate();
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas c)
        {
            super.onDraw(c);
            can = c;
            Paint p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            //Display display = getWindowManager().getDefaultDisplay();
            //Point pt = new Point();
            //display.getSize(pt);
            p.setTextSize(30);

            w = c.getWidth();
            h = c.getHeight();
            int diferenca = h - w;

            Bitmap bmp;
            bmp = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.player1));
            bmp.prepareToDraw();
            int left = w / 10 + 20, top = h / 25, right = left + w / 3, bottom = (int)(top + (right - left) * 0.3125);
            c.drawBitmap(bmp, null, new Rect(left, top, right, bottom), p);

            bmp = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.player2));
            bmp.prepareToDraw();
            int left2 = w / 10 + 70 + w / 3, top2 = h / 25, right2 = left2 + w / 3, bottom2 = (int)(top2 + (right2 - left2) * 0.3125);
            c.drawBitmap(bmp, null, new Rect(left2, top2, right2, bottom2), p);

            bmp = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.placar_0 + p1 / 10));
            bmp.prepareToDraw();
            c.drawBitmap(bmp, null, new Rect((right + left) / 2 - w / 10, bottom + 10, (right + left) / 2, bottom + 10 + w / 10), p);

            bmp = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.placar_0 + p1 % 10));
            bmp.prepareToDraw();
            c.drawBitmap(bmp, null, new Rect((right + left) / 2, bottom + 10, (right + left) / 2 + w / 10, bottom + 10 + w / 10), p);

            bmp = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.placar_0 + p2 / 10));
            bmp.prepareToDraw();
            c.drawBitmap(bmp, null, new Rect((right2 + left2) / 2 - w / 10, bottom2 + 10, (right2 + left2) / 2, bottom2 + 10 + w / 10), p);

            bmp = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.placar_0 + p2 % 10));
            bmp.prepareToDraw();
            c.drawBitmap(bmp, null, new Rect((right2 + left2) / 2, bottom2 + 10, (right2 + left2) / 2 + w / 10, bottom2 + 10 + w / 10), p);

            p.setColor(Color.parseColor("#858C00"));
            SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
            int coins = settings.getInt("Coins", 0);
            c.drawText("Coins: " + coins, w / 25 + 20, 24 * h / 25, p);
            p.setColor(new Color().rgb(30, 30, 30));

            c.drawLine((w / 3), diferenca / 2 + 2*(h/25), (w / 3), h - diferenca / 2 + 2*(h/25), p);
            c.drawLine(2 * (w / 3), diferenca / 2 + 2*(h/25), 2 * (w / 3), h - diferenca / 2 + 2*(h/25), p);
            c.drawLine(25, (diferenca / 2) + (w / 3) + 2*(h/25), w - 25, (diferenca / 2) + (w / 3) + 2*(h/25), p);
            c.drawLine(25, (diferenca / 2) + 2 * (w / 3) + 2*(h/25), w - 25, (diferenca / 2) + 2 * (w / 3) + 2*(h/25), p);

            for(int n = 0; n < 3; n++)
                for(int m = 0; m < 3; m++)
                    if(tabuleiro[m][n] == 2)
                    {
                        Bitmap bmpC;
                        bmpC = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), pO));
                        bmpC.prepareToDraw();
                        int l = m * (w / 3) + w / 20, t = (diferenca / 2) + n * (w / 3) + w / 20 + 2*(h/25), r = (m + 1) * (w / 3) - w / 20, b = (diferenca / 2) + (n + 1) * (w / 3) - w / 20 + 2*(h/25);
                        c.drawBitmap(bmpC, null, new Rect(l, t, r, b), p);
                    }

            p.setColor(new Color().rgb(30, 30, 230));
            for(int n = 0; n < 3; n++)
                for(int m = 0; m < 3; m++)
                    if(tabuleiro[m][n] == 1)
                    {
                        Bitmap bmpX;
                        bmpX = Bitmap.createBitmap(BitmapFactory.decodeResource(this.getResources(), pX));
                        bmpX.prepareToDraw();
                        int l = m * (w / 3) + w / 20, t = (diferenca / 2) + n * (w / 3) + w / 20 + 2*(h/25), r = (m + 1) * (w / 3) - w / 20, b = (diferenca / 2) + (n + 1) * (w / 3) - w / 20 + 2*(h/25);
                        c.drawBitmap(bmpX, null, new Rect(l, t, r, b), p);
                    }
        }

        private void ganhaMoedas(int valor)
        {
            SharedPreferences settings = getSharedPreferences("Settings", MODE_PRIVATE);
            int coins = settings.getInt("Coins", 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("Coins", coins + valor);
            editor.commit();
        }

        private void checaFim()
        {
            Paint p = new Paint();
            p.setStyle(Paint.Style.STROKE);
            p.setColor(new Color().rgb(30, 30, 30));
            for(int i = 0; i < 3; i++)
            {
                if(tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][1] == tabuleiro[i][2] && tabuleiro[i][2] != 0)
                {
                    Log.d("Fim", "Fim nas colunas");
                    if(tabuleiro[i][2] == 1)
                    {
                        p1++;
                    }
                    else
                    {
                        p2++;
                    }
                    for(int j = 0; j < 3; j++)
                    {
                        for(int k = 0; k < 3; k++)
                        {
                            tabuleiro[j][k] = 0;
                        }
                    }
                    jogadas = 0;
                    ganhaMoedas(1);
                }
            }
            for(int i = 0; i < 3; i++)
            {
                if(tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[1][i] == tabuleiro[2][i] && tabuleiro[2][i] != 0)
                {
                    Log.d("Fim", "Fim nas linhas");
                    if(tabuleiro[2][i] == 1)
                    {
                        p1++;
                    }
                    else
                    {
                        p2++;
                    }
                    for(int j = 0; j < 3; j++)
                    {
                        for(int k = 0; k < 3; k++)
                        {
                            tabuleiro[j][k] = 0;
                        }
                    }
                    jogadas = 0;
                    ganhaMoedas(1);
                }
            }
            if(tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2] && tabuleiro[2][2] != 0)
            {
                Log.d("Fim", "Fim diagonal cima/baixo");
                if(tabuleiro[1][1] == 1)
                {
                    p1++;
                }
                else
                {
                    p2++;
                }
                for(int j = 0; j < 3; j++)
                {
                    for(int k = 0; k < 3; k++)
                    {
                        tabuleiro[j][k] = 0;
                    }
                }
                jogadas = 0;
                ganhaMoedas(1);
            }
            if(tabuleiro[2][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[0][2] && tabuleiro[0][2] != 0)
            {
                Log.d("Fim", "Fim diagonal baixo/cima");
                if(tabuleiro[1][1] == 1)
                {
                    p1++;
                }
                else
                {
                    p2++;
                }
                for(int j = 0; j < 3; j++)
                {
                    for(int k = 0; k < 3; k++)
                    {
                        tabuleiro[j][k] = 0;
                    }
                }
                jogadas = 0;
                ganhaMoedas(1);
            }
            if(jogadas == 9)
            {
                Log.d("Fim", "Deu velha");
                for(int j = 0; j < 3; j++)
                {
                    for(int k = 0; k < 3; k++)
                    {
                        tabuleiro[j][k] = 0;
                    }
                }
                jogadas = 0;
                ganhaMoedas(1);
            }
        }
    }
}
