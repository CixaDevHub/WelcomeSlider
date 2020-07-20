package com.example.myapplicatio; // Add/change Your Package Name If You Are Copying And Pasting It On Existing App

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class WelcomeSliderActivity extends AppCompatActivity {
    private WelcomeAdapter welcomeAdapter;
    private LinearLayout LayoutWelcomeIndicators;
    private MaterialButton buttonWelcomeAction;
    private PrefManager prefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager = new PrefManager(this);
        if (!prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }

        setContentView(R.layout.activity_welcomeslider);

        LayoutWelcomeIndicators = findViewById(R.id.layoutWelcomeIndicators);
        buttonWelcomeAction = findViewById(R.id.buttonWelcomeAction);

        setupWelcomeItems();

        final ViewPager2 welcomeViewPager = findViewById(R.id.welcomeViewPager);
        welcomeViewPager.setAdapter(welcomeAdapter);

        setupWelcomeIndicators();
        setCurrentWelcomeIndicator(0);

        welcomeViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback(){
            @Override
            public void onPageSelected(int position){
                super.onPageSelected(position);
                setCurrentWelcomeIndicator(position);
            }
        });

        buttonWelcomeAction.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View view){
                if(welcomeViewPager.getCurrentItem() + 1 < welcomeAdapter.getItemCount()){
                welcomeViewPager.setCurrentItem(welcomeViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class)); // When your welcome screen ends it will take You to main activity page however if want to go into another activity just change MainActivity.class to YourActivity.class
                    finish();
                }
            }
        });

    }

    private void setupWelcomeItems(){
        List<WelcomeItem> welcomeItems = new ArrayList<>();
        // Here You Can Create As Many Screens You Want Just Copy And Paste Below Code Again And Again. Here We Have Created 4 Screens
        WelcomeItem item1 = new WelcomeItem();
        item1.setTitle("Title 1");  // Here Add Your Title For Screen 1
        item1.setDescription("Your Description Here"); // Here Add Your Description For Screen 1
        item1.setImage(R.drawable.image1); // Here Replace Image1 with your image title or image name for ex: image1 to displayimage1

        WelcomeItem item2 = new WelcomeItem();
        item2.setTitle("Title 2");  // Here Add Your Title For Screen 2
        item2.setDescription("Your Description Here"); // Here Add Your Description For Screen 2
        item2.setImage(R.drawable.image2); // Here Replace Image2 with your image title or image name for ex: image2 to donkeyimage1

        WelcomeItem item3 = new WelcomeItem();
        item3.setTitle("Title 3");  // Here Add Your Title For Screen 3
        item3.setDescription("Your Description Here"); // Here Add Your Description For Screen 3
        item3.setImage(R.drawable.image3); // Here Replace Image3 with your image title or image name for ex: image3 to Ratimage2

        // Optional : If You Want To Reduce No. Of Screen. For Ex: If you want 3 screens then delete the item4 code area.
        WelcomeItem item4 = new WelcomeItem();
        item4.setTitle("Title 4");  // Here Add Your Title For Screen 4
        item4.setDescription("Your Description Here"); // Here Add Your Description For Screen 4
        item4.setImage(R.drawable.image4); // Here Replace Image4 with your image title or image name for ex: image4 to snakeimage25

        // Note : If you have deleted any screen from above then delete that item call function from below.
        // Note : If you have Added any new screen above then don't forget to add that call function below, just like i have added below
        welcomeItems.add(item1);
        welcomeItems.add(item2);
        welcomeItems.add(item3);
        welcomeItems.add(item4);

        welcomeAdapter = new WelcomeAdapter(welcomeItems);
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeSliderActivity.this, MainActivity.class));
        finish();
    }

    private void setupWelcomeIndicators(){
        ImageView[] indicators = new ImageView[welcomeAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.welcome_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            LayoutWelcomeIndicators.addView(indicators[i]);
        }
    }

    private void setCurrentWelcomeIndicator(int index){
        int childCount = LayoutWelcomeIndicators.getChildCount();
        for(int i = 0; i < childCount; i++){
            ImageView ImageView = (ImageView)LayoutWelcomeIndicators.getChildAt(i);
            if(i == index){
                ImageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.welcome_indicator_active)
                );
            } else {
                ImageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.welcome_indicator_inactive)
                );
            }
        }
        if(index == welcomeAdapter.getItemCount() - 1){
            buttonWelcomeAction.setText("Finish");
        } else {
            buttonWelcomeAction.setText("Next");
        }
    }
}