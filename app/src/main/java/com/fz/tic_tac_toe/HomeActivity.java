package com.fz.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    boolean active = true;                                  //var for checking game is active or not
    int playerActive = 0;                                   //0-X 1-X
    int count = 0;                                          //count variable for no of times screen is tapped
    int winOccur = 0;                                       //if anyone has won
    int[] gameArray = {9,9,9,9,9,9,9,9,9};                  //initial game state
    int[][] win = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{0,4,8},{2,4,6},{2,5,8}};  //winning combination of same symbol
    public void playerTap(View view)
    {
        if(count==10)
        {
            resetGame(view);
            return;
        }
        ImageView img = (ImageView) view;
        int imageTag = Integer.parseInt(img.getTag().toString());
        if(active != true)
        {
            resetGame(view);
            return;
        }
        if(gameArray[imageTag] != 9)            //check if tapped column is already occupied
        {
            String player;
            if(playerActive == 0)
            {
                player = "X";
            }
            else
            {
                player = "O";
            }
            TextView status = findViewById(R.id.status);
            status.setText("Invalid Move - " + player + "'s Turn");
        }
        if(gameArray[imageTag] == 9)  //Enter symbol if tapped place is empty
        {
            count = count + 1;
            gameArray[imageTag] = playerActive;
            img.setTranslationY(-1000f);
            if(playerActive == 0)
            {
                img.setImageResource(R.drawable.cross);
                playerActive = 1;
                TextView status = findViewById(R.id.status);
                status.setText("Player O's Turn - Tap To Play");
            }
            else
            {
                img.setImageResource(R.drawable.zero);
                playerActive = 0;
                TextView status = findViewById(R.id.status);
                status.setText("Player X's Turn - Tap To Play");
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }
        //Player Won
        for(int[] winPosition: win)
        {
            if(gameArray[winPosition[0]]== gameArray[winPosition[1]] && gameArray[winPosition[1]]== gameArray[winPosition[2]] && gameArray[winPosition[1]]!=9)
            {
                String winner;
                active = false;
                if(gameArray[winPosition[0]] == 0)
                {
                    winOccur=1;
                    winner = "Congrats! Player X has Won";
                }
                else
                {
                    winOccur=1;
                    winner = "Congrats! Player O has Won";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winner);
            }
        }

        if(count == 9 && winOccur==0)  // checks if match tied
        {
            count=10;
            TextView status = findViewById(R.id.status);
            status.setText("Match Tied");
        }
        /*if(count==10)
        {
            resetGame(view);
        }*/
    }
    public void resetGame(View view)             //resets the game
    {
        active = true;
        count = 0;
        winOccur = 0;
        playerActive = 0;
        for (int i=0; i<gameArray.length; i++)
        {
            gameArray[i] = 9;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Player X's Turn - Tap To Play");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
