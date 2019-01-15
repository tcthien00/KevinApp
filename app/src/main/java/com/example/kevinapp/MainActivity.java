package com.example.kevinapp;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    float origPosY = 0;

    ObjectAnimator objAnim;
    private void pulseAnimation(){
        objAnim = ObjectAnimator.ofPropertyValuesHolder(findViewById(R.id.buttonYes),
                                                        PropertyValuesHolder.ofFloat("scaleX", 1.5f),
                                                        PropertyValuesHolder.ofFloat("scaleY", 1.5f));
        objAnim.setDuration(300);
        objAnim.setRepeatCount(ObjectAnimator.INFINITE);
        objAnim.setRepeatMode(ObjectAnimator.REVERSE);
        objAnim.start();
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button buttonYes = (Button) (findViewById(R.id.buttonYes));
        final Button buttonNo = (Button) (findViewById(R.id.buttonNo));
        final Button buttonClose = (Button) (findViewById(R.id.buttonClose));

        final TextView textViewQuestion = (TextView) findViewById(R.id.textViewQuestion);
        final TextView textViewThank = (TextView) findViewById(R.id.textViewThank);


//        origPosY = buttonNo.getY();
//        System.out.println(buttonYes.getScaleX());
//        System.out.println(buttonYes.getScaleY());

        System.out.println(origPosY);


        this.pulseAnimation();


        buttonYes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                objAnim.cancel();
                findViewById(R.id.Screen).setBackgroundColor(Color.GREEN);
//                buttonYes.setScaleX(1);
//                buttonYes.setScaleY(1);
                textViewQuestion.setText(textViewQuestion.getText()+ "\nYou did say Yes");
                findViewById(R.id.textViewThank).setVisibility(View.VISIBLE);
                buttonYes.setVisibility(View.INVISIBLE);
                buttonNo.setVisibility(View.INVISIBLE);
                buttonClose.setVisibility(View.VISIBLE);

            }
        });

        buttonNo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                findViewById(R.id.textViewThank).setVisibility(View.INVISIBLE);
                if (!objAnim.isRunning())
                    objAnim.start();
//                float bottomY = findViewById(R.id.Screen).getHeight()-buttonNo.getHeight();
//                float currentY = buttonNo.getY() + buttonNo.getHeight();
//                System.out.println("Y=" + currentY + findViewById(R.id.Screen).getY());
                findViewById(R.id.Screen).setBackgroundColor(Color.RED);

//                if (currentY < bottomY)
//                    buttonNo.setY(currentY);
//                else
//                    buttonNo.setY(origPosY);
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
