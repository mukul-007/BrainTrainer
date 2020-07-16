package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timer , result , score , puzzle;
    boolean visible;
    List<Integer> answers;
    List<Button> buttonList;
    GridLayout gridLayout;
    int locationofCorrectAnswer;
    int sc ;
    int numberOfQuestion ;
    Button playAgain;

    public void visible() {

        if (visible) {
            visible = false;
            timer.setVisibility(View.INVISIBLE);
            score.setVisibility(View.INVISIBLE);
            puzzle.setVisibility(View.INVISIBLE);
            gridLayout.setVisibility(View.INVISIBLE);
        } else {
            visible = true;
            timer.setVisibility(View.VISIBLE);
            score.setVisibility(View.VISIBLE);
            puzzle.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.VISIBLE);
        }
    }
    public void start(){

        visible();
        score.setText("0/0");
        result.setText("");
        setRandom();
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.music);
        CountDownTimer countDownTimer = new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished/1000;
                timer.setText(Integer.toString(seconds) + 's');
            }

            @Override
            public void onFinish() {
                visible();
                playAgain.animate().translationYBy(1000);
                result.setText("Score is : " + score.getText().toString());

                mediaPlayer.start();
            }
        }.start();
    }
    public void go(View view){
        Button go = (Button) findViewById(R.id.button);
        go.setVisibility(View.INVISIBLE);
        start();
    }

    public void setPlayAgain(View view){
        numberOfQuestion = 0;
        sc = 0;
        playAgain.animate().translationYBy(-1000);
        start();
    }

    public void toCheck(View view){
        Button button = (Button) view;
        int get = Integer.parseInt(button.getTag().toString());
        if(get == locationofCorrectAnswer){
            result.setText("Correct!");
            result.setVisibility(View.VISIBLE);
            sc++;
        }else{
            result.setVisibility(View.VISIBLE);
            result.setText("Wrong :( ");

        }
        numberOfQuestion++;
        score.setText(Integer.toString(sc) + "/" + Integer.toString(numberOfQuestion));
        setRandom();
    }

    public void setRandom(){
        Random rand = new Random();
        int a = rand.nextInt(20) + 1;
        int b = rand.nextInt(20) + 1;

        String s = Integer.toString(a) + "+" + Integer.toString(b);
        puzzle.setText(s);

        locationofCorrectAnswer = rand.nextInt(4);

        answers.clear();
        for(int i=0;i<4;i++){
            if(i == locationofCorrectAnswer){
                answers.add(a + b);
            }else{
                int wrong = rand.nextInt(40) + 1;
                while(wrong == a + b){
                    wrong = rand.nextInt(40) + 1;
                }
                answers.add(wrong);
            }
        }

        for(int i=0;i<4;i++){
            buttonList.get(i).setText(answers.get(i).toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = (TextView) findViewById(R.id.timer);

        result = (TextView) findViewById(R.id.result);

        puzzle = findViewById(R.id.numbers);

        score = findViewById(R.id.score);

        sc = 0;
        numberOfQuestion = 0;

        gridLayout = findViewById(R.id.gridLayout);
        visible = true;
        visible();
        result.setVisibility(View.INVISIBLE);
        answers = new ArrayList<>();
        buttonList = new ArrayList<>();
        buttonList.add(0,(Button) findViewById(R.id.number1));
        buttonList.add(1,(Button) findViewById(R.id.number2));
        buttonList.add(2,(Button) findViewById(R.id.number3));
        buttonList.add(3,(Button) findViewById(R.id.number4));

        setRandom();
        playAgain = findViewById(R.id.playagain);
        playAgain.setTranslationY(-1000);

    }
}
