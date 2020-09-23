package com.example.mediquest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import maes.tech.intentanim.CustomIntent;

public class BMIActivity extends AppCompatActivity {

    ImageView back;
    TextView bmi, status, idealWeight;
    EditText heightft, heightin, weight;
    Button calculate;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_m_i);

        layout = findViewById(R.id.layout_id);
        back = (ImageView) findViewById(R.id.back_icon_Id);
        bmi = (TextView) findViewById(R.id.bmi_id);
        status = (TextView) findViewById(R.id.status_id);
        idealWeight = (TextView) findViewById(R.id.ideal_weight_id);
        heightft = (EditText) findViewById(R.id.height_feet_id);
        heightin = (EditText) findViewById(R.id.height_inch_id);
        weight = (EditText) findViewById(R.id.weight_id);
        calculate = (Button) findViewById(R.id.calculate_id);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strHeightFt = heightft.getText().toString();
                String strHeightIn = heightin.getText().toString();
                String strWeight = weight.getText().toString();

                if(strHeightFt.equals("")) {
                    heightft.setError("Please Enter Your Height");
                    heightft.requestFocus();
                    return;
                }
                if(strHeightIn.equals("")) {
                    heightin.setError("Please Enter Your Height");
                    heightin.requestFocus();
                    return;
                }
                if(strWeight.equals("")) {
                    weight.setError("Please Enter Your Weight");
                    weight.requestFocus();
                    return;
                }

                double feetTometre = Float.parseFloat(strHeightFt)/3.28084;
                double inchTometre = Float.parseFloat(strHeightIn)/39.37;
                double height1 = feetTometre + inchTometre;
                int feetToinch = Integer.parseInt(strHeightFt)*12;
                int inch = Integer.parseInt(strHeightIn);
                int heightIninch = feetToinch + inch;
                double weight1 = Float.parseFloat(strWeight);
                double bmiValue = BMICalculate(height1, weight1);

                bmi.setText(String.format("BMI  %.2f", bmiValue));
                status.setText(interpretBMI(bmiValue));
                idealWeight.setText(interpretIdealWeight(heightIninch));

                if(status.getVisibility() == View.GONE) {
                    status.setVisibility(View.VISIBLE);
                    idealWeight.setVisibility(View.VISIBLE);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BMIActivity.this, HomePageActivity.class);
                startActivity(i);
                CustomIntent.customType(BMIActivity.this, "right-to-left");
            }
        });

        load_setting();
    }
    // Dark Mood
    private void load_setting() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        boolean chk_night = sp.getBoolean("NIGHT", false);
        if (chk_night) {
            layout.setBackgroundColor(Color.parseColor("#141d26"));
            //phone.setTextColor(Color.parseColor("#ffffff"));

        } else {
            layout.setBackgroundColor(Color.parseColor("#ffffff"));
            //phone.setTextColor(Color.parseColor("#000000"));
        }

        String orien = sp.getString("ORIENTATION", "false");
        if("1".equals(orien)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
        }
        else if("2".equals(orien)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        else if("3".equals(orien)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
    // Calculate BMI
    public double BMICalculate(double height, double weight) {
        return weight / (height * height);
    }
    // Define Status
    public  String interpretBMI(double bmiValue) {
        if(bmiValue < 18.5) {
            return "Weight Status : Underweight";
        }
        else if(bmiValue < 25) {
            return "Weight Status : Normal";
        }
        else if(bmiValue < 30) {
            return "Weight Status : Overweight";
        }
        else {
            return "Weight Status : Obese";
        }
    }
    // Define Healthy Weight
    public String interpretIdealWeight(int height) {
        if(height == 58) {
            return "Healthy weight for your height : 41kg - 52kg";
        }
        else if(height == 59) {
            return "Healthy weight for your height : 42.5kg - 54kg";
        }
        else if(height == 60) {
            return "Healthy weight for your height : 44kg - 55.5kg";
        }
        else if(height == 61) {
            return "Healthy weight for your height : 45.5kg - 57.5kg";
        }
        else if(height == 62) {
            return "Healthy weight for your height : 47kg - 59.5kg";
        }
        else if(height == 63) {
            return "Healthy weight for your height : 48.5kg - 61kg";
        }
        else if(height == 64) {
            return "Healthy weight for your height : 50kg - 63.5kg";
        }
        else if(height == 65) {
            return "Healthy weight for your height : 51.5kg - 65kg";
        }
        else if(height == 66) {
            return "Healthy weight for your height : 53.5kg - 67kg";
        }
        else if(height == 67) {
            return "Healthy weight for your height : 55kg - 69kg";
        }
        else if(height == 68) {
            return "Healthy weight for your height : 56.5kg - 71.5kg";
        }
        else if(height == 69) {
            return "Healthy weight for your height : 58kg - 73.5kg";
        }
        else if(height == 70) {
            return "Healthy weight for your height : 60kg - 75.5kg";
        }
        else if(height == 71) {
            return "Healthy weight for your height : 61.5kg - 78kg";
        }
        else if(height == 72) {
            return "Healthy weight for your height : 63.5kg - 80kg";
        }
        else if(height == 73) {
            return "Healthy weight for your height : 65kg - 82.5kg";
        }
        else if(height == 74) {
            return "Healthy weight for your height : 67kg - 84kg";
        }
        else if(height == 75) {
            return "Healthy weight for your height : 69kg - 87kg";
        }
        else if(height == 76) {
            return "Healthy weight for your height : 70.5kg - 89kg";
        }
        else {
            return "";
        }
    }

    @Override
    protected void onResume() {
        load_setting();
        super.onResume();
    }
}