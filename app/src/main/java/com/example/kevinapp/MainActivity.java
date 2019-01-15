package com.example.kevinapp;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ObjectAnimator objAnim;
    private void startSizeAnimation(View v){
        objAnim = ObjectAnimator.ofPropertyValuesHolder(v,
                                                        PropertyValuesHolder.ofFloat("scaleX", 1.2f),
                                                        PropertyValuesHolder.ofFloat("scaleY", 1.2f));
        objAnim.setDuration(300);
        objAnim.setRepeatCount(ObjectAnimator.INFINITE);
        objAnim.setRepeatMode(ObjectAnimator.REVERSE);
        objAnim.start();
    };

    ValueAnimator colorAnim;
    private void startColorAnimation(View v){
        colorAnim = ObjectAnimator.ofInt(v, "backgroundColor", v.getSolidColor(), 0xffff0000);
        colorAnim.setDuration(50);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ObjectAnimator.RESTART);
        colorAnim.setRepeatMode(ObjectAnimator.REVERSE);
        colorAnim.start();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonYes = (Button) (findViewById(R.id.buttonYes));
        final Button buttonNo = (Button) (findViewById(R.id.buttonNo));
        final Button buttonClose = (Button) (findViewById(R.id.buttonClose));

        final TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);

        startSizeAnimation(findViewById(R.id.buttonYes));

        buttonYes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                objAnim.cancel();
                findViewById(R.id.constrainLayout).setBackgroundColor(Color.GREEN);
                textViewQuestion.setText(textViewQuestion.getText()+ "\nYou did say YES");
                findViewById(R.id.textViewThank).setVisibility(View.VISIBLE);
                buttonYes.setVisibility(View.INVISIBLE);
                buttonNo.setVisibility(View.INVISIBLE);
                buttonClose.setVisibility(View.VISIBLE);
                startSizeAnimation(findViewById(R.id.buttonClose));

            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (!objAnim.isRunning())
                    objAnim.start();
                startColorAnimation(findViewById(R.id.constrainLayout));
                Vibrator vi = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                // Each element then alternates between vibrate, sleep, vibrate, sleep...
                long[] pattern = {0, 100};
                vi.vibrate(pattern, -1);
            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}
