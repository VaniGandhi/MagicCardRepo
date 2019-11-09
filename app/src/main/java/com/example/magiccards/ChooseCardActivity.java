package com.example.magiccards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class ChooseCardActivity extends AppCompatActivity {

    Button heart, spade,
            club, diamond;
    UserSharedPrefernce userSharedPrefernce;
    LinearLayout ll1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_card);
        heart=findViewById(R.id.hearimage);
        spade=findViewById(R.id.spadeimage);
        club=findViewById(R.id.clubimage);
        diamond=findViewById(R.id.diamondimage);
        ll1=findViewById(R.id.ll1);
        userSharedPrefernce=UserSharedPrefernce.getInstance();

        heart.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                Toast.makeText(ChooseCardActivity.this, "swpied", Toast.LENGTH_SHORT).show();
                userSharedPrefernce.setCARD("Hearts");
                Intent intent=new Intent(ChooseCardActivity.this, viewImageActivity.class);
                startActivity(intent);
                return false;
            }
        });
        spade.setOnTouchListener(new OnSwipeTouchListener() {
            public boolean onSwipeLeft() {
                userSharedPrefernce.setCARD("Spades");
                Intent intent=new Intent(ChooseCardActivity.this, viewImageActivity.class);
                startActivity(intent);
                return false;
            }
        });
        club.setOnTouchListener(new OnSwipeTouchListener() {
            public boolean onSwipeLeft() {
                userSharedPrefernce.setCARD("Clubs");
                Intent intent=new Intent(ChooseCardActivity.this, viewImageActivity.class);
                startActivity(intent);
                return false;
            }
        });
        diamond.setOnTouchListener(new OnSwipeTouchListener() {
            public boolean onSwipeLeft() {
                userSharedPrefernce.setCARD("Diamonds");
                Intent intent=new Intent(ChooseCardActivity.this, viewImageActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
}
