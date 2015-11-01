package com.bojie.tictactoe_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // 0 = yellow, 1 = red
    int activePlayer = 0;

    // 2 means unplayed
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5},
            {6, 7, 8}, {0, 3, 6},
            {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        Log.d("TAG", counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2) {

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {

                    // Someone has won

                    String winner = "Red";
                    if (gameState[winningPosition[0]] == 0) {
                        winner = "Yellow";
                    }
                    TextView winnerMessage = (TextView) findViewById(R.id.winning_message);
                    winnerMessage.setText(winner + " has won!");

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.play_again_layout);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.play_again_layout);
        linearLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid_board);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageDrawable(null);
        }
    }
}
