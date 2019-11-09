package com.example.magiccards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class OnetothirteenActivity extends AppCompatActivity {
    Button one, two, three, four, five, six, seven, eight, nine, ten , eleven , twelve, thirteen, fourteen;
    UserSharedPrefernce userSharedPrefernce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onetothirteen);
        one=findViewById(R.id.button1);
        two=findViewById(R.id.button2);
        three=findViewById(R.id.button3);
        four=findViewById(R.id.button4);
        five=findViewById(R.id.button5);
        six=findViewById(R.id.button6);
        seven=findViewById(R.id.button7);
        eight=findViewById(R.id.button8);
        nine=findViewById(R.id.button9);
        ten=findViewById(R.id.button10);
        eleven=findViewById(R.id.button11);
        twelve=findViewById(R.id.button12);
        thirteen=findViewById(R.id.button13);
        fourteen=findViewById(R.id.button14);
        userSharedPrefernce=UserSharedPrefernce.getInstance();

        one.setOnTouchListener(new OnSwipeTouchListener() {
            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("Ace");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);

                return false;

            }
        });
        two.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("2");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        three.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("3");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        four.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("4");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        five.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("5");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        six.setOnTouchListener(new OnSwipeTouchListener() {
            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("6");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        seven.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("7");

                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        eight.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("8");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        nine.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("9");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });ten.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("10");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        eleven.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("Jack");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        twelve.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("Queen");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });
        thirteen.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("KIng");
                Intent intent=new Intent(OnetothirteenActivity.this, ChooseCardActivity.class);
                startActivity(intent);
                return false;
            }
        });fourteen.setOnTouchListener(new OnSwipeTouchListener() {

            public boolean onSwipeLeft() {
                userSharedPrefernce.setNUMBER("Joker");
                userSharedPrefernce.setCARD("null");
                Intent intent=new Intent(OnetothirteenActivity.this, viewImageActivity.class);
                startActivity(intent);
                return false;
            }
        });
















    }
}
