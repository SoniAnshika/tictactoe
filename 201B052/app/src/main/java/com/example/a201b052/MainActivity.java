package com.example.a201b052;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player = 1;
    boolean isWinner = false;
    int imageClicked = -1;
    int [][]winStates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int []currState = {-1,-1,-1,-1,-1,-1,-1,-1,-1};
    public void load(View view)
    {
        ImageView v = (ImageView) view;
        int tag = Integer.parseInt(v.getTag().toString());
        imageClicked = currState[tag];

        if(!isWinner && imageClicked == -1)
        {
            if(player == 1)
            {
                v.setImageResource(R.drawable.cross__1_);
                currState[tag] = player;
                Toast.makeText(this, tag+" "+"Cross",Toast.LENGTH_SHORT).show();
                player = 2;
            }else
            {
                v.setImageResource(R.drawable.zero__1_);
                currState[tag] = player;
                Toast.makeText(this, tag+" "+"Zero",Toast.LENGTH_SHORT).show();
                player = 1;
            }

            for(int i = 0; i < winStates.length; i++){
                if(currState[winStates[i][0]] == currState[winStates[i][1]] && currState[winStates[i][1]] == currState[winStates[i][2]] && currState[winStates[i][0]] != -1)
                {
                    Toast.makeText(this, "Player "+ (player==2?1:2)+" is winner",Toast.LENGTH_SHORT).show();
                    isWinner = true;
                }
            }
            int count = 0;

            for(int i = 0; i < currState.length; i++)
            {
                if(currState[i] != -1)
                {
                    count++;
                }
            }

            if(count == currState.length && !isWinner)
            {
                Toast.makeText(this,"Match Draw",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void reset(View view)
    {
       GridLayout gridLayout = findViewById(R.id.gridLayout);
        int total_images = gridLayout.getChildCount();
        for(int i = 0; i < total_images; i++){
            ImageView v = (ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);
        }
        isWinner = false;
        imageClicked = -1;
        for(int i = 0; i < currState.length; i++){
            currState[i] = -1;
        }
        player = 1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}