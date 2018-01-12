package com.game.amw.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    TextView score;
    TextView score_max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CardGrid cardGrid = findViewById(R.id.grid);
        Button newgame = findViewById(R.id.newgame);
        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardGrid.newGame();
            }
        });

        score = findViewById(R.id.score);
        score_max = findViewById(R.id.score_max);
        cardGrid.setTv_score(score);
        cardGrid.setTv_score_max(score_max);

      }
}
