package com.example.fir3destr0yer.tictactoe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class Tabuleiro extends View
{
    private int dimensao;
    private final Paint tracoPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint casaPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Boolean[][] mTabuleiro;
    private int jogadorInicial;
    private int jogadorAtual;
    private int nJogadas = 0;
    private OnTabuleiroListener tabuleiroListener;

    //Desenhos
    private float intervalo, tamanhoIdeal, startX, startY, endX, endY;

    public Tabuleiro(Context context)
    {
        this(context, null);
    }

    public Tabuleiro(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    private void init()
    {
        tracoPaint.setColor(Color.BLACK);
        tracoPaint.setStrokeWidth(5);
        tracoPaint.setStyle(Paint.Style.STROKE);
        tracoPaint.setStrokeCap(Paint.Cap.ROUND);

        casaPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //0 = O, 1 = X
        setJogadorInicial(0);
        setDimensao(3);
    }

    public void setOnTabuleiroListener(OnTabuleiroListener listener)
    {
        this.tabuleiroListener = listener;
    }

    public void setDimensao(int dimensao)
    {
        this.dimensao = Math.min(Math.max(dimensao, 3), 10);
        reset();
    }

    public int getDimensao()
    {
        return dimensao;
    }

    public void setJogadorInicial(int jogadorInicial)
    {
        this.jogadorInicial = jogadorInicial;
    }

    public int getJogadorInicial()
    {
        return jogadorInicial;
    }

    public void reset()
    {
        nJogadas = 0;
        jogadorAtual = jogadorInicial;
        mTabuleiro = new Boolean[getDimensao()][getDimensao()];
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);

        final int w = right - left;
        final int h = bottom - top;
        final float centroX = w / 2f;
        final float centroY = h / 2f;
        tamanhoIdeal = Math.min(w, h) - 10;
        startX = centroX - tamanhoIdeal / 2;
        endX = centroX + tamanhoIdeal / 2;
        startY = centroY - tamanhoIdeal / 2;
        endY = centroY + tamanhoIdeal / 2;
        intervalo = tamanhoIdeal / (float)dimensao;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        //Desenhar linhas.
        for(int i = 1; i < dimensao; i++)
        {
            //Horizontal.
            canvas.drawLine(startX, startY + i * intervalo, endX, startY + i * intervalo, tracoPaint);
            //Vertical.
            canvas.drawLine(startX + i * intervalo, startY, startX + i * intervalo, endY, tracoPaint);
        }

        //Desenhar as casas selecionadas.
        for(int i = 0; i < dimensao; i++)
        {
            for(int k = 0; k < dimensao; k++)
            {
                //centro.
                final float cx = startX + (k + 0.5f) * intervalo;
                final float cy = startY + (i + 0.5f) * intervalo;

                if(mTabuleiro[i][k] != null)
                {
                    //Plotar os desenhos aqui!!!
                    if(mTabuleiro[i][k]) casaPaint.setColor(Color.RED);
                    else casaPaint.setColor(Color.BLUE);
                    canvas.drawCircle(cx, cy, 10, casaPaint);
                }
            }
        }
    }

    public boolean selecionarCasa(int row, int col)
    {
        //Casa vazia.
        if(mTabuleiro[row][col] == null)
        {
            //Preeche-la com o jogador atual.
            mTabuleiro[row][col] = jogadorAtual == 1;
            //Jogada feita.
            nJogadas++;
            //Repintar a tela.
            invalidate();
            //Invocar os eventos.
            if(tabuleiroListener != null)
            {
                tabuleiroListener.onJogadaFinalizada(this, jogadorAtual, row, col);
                //Numero maximo de jogadas atingido.
                if(nJogadas == getDimensao() * getDimensao())
                {
                    tabuleiroListener.onSemJogadas(this);
                }
            }
            //Troca o jogador.
            jogadorAtual ^= 1;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            final float x = event.getX();
            final float y = event.getY();
            final RectF posicao = new RectF();

            for(int row = 0; row < dimensao; row++)
            {
                for(int col = 0; col < dimensao; col++)
                {
                    //Define a localizacao da casa.
                    posicao.set(startX + col * intervalo, startY + row * intervalo,
                            startX + (col + 1) * intervalo, startY + (row + 1) * intervalo);
                    if(posicao.contains(x, y))
                    {
                        return selecionarCasa(row, col);
                    }
                }
            }
        }

        return super.onTouchEvent(event);
    }

    public interface OnTabuleiroListener
    {
        void onJogadaFinalizada(Tabuleiro view, int jogador, int row, int col);

        void onSemJogadas(Tabuleiro view);
    }
}
