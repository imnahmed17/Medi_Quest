package com.example.mediquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HealthTipsActivity extends AppCompatActivity {

    private ImageButton nutrition1,fitness1,advice1;
    private Button nutrition2,fitness2,advice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_tips);

        nutrition1 = (ImageButton) findViewById(R.id.nutrition1);
        nutrition2 = (Button) findViewById(R.id.nutrition2);
        nutrition2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNutrition();
            }
        });
        nutrition1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNutrition();
            }
        });

        fitness1 = (ImageButton) findViewById(R.id.fitness1);
        fitness1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFitness();
            }
        });
        fitness2 = (Button) findViewById(R.id.fitness2);
        fitness2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFitness();
            }
        });

        advice1 = (ImageButton) findViewById(R.id.advice1);
        advice2 = (Button) findViewById(R.id.advice2);
        advice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvice();
            }
        });
        advice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAdvice();
            }
        });
    }

    public void openFitness() {
        Intent intent = new Intent(this, Fitness.class);
        startActivity(intent);
    }

    public void openNutrition() {
        Intent intent = new Intent(this, Nutrition.class);
        startActivity(intent);
    }

    public void openAdvice() {
        Intent intent = new Intent(this, Advice.class);
        startActivity(intent);
    }
}