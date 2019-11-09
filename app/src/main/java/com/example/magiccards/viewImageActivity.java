package com.example.magiccards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class viewImageActivity extends AppCompatActivity {
    UserSharedPrefernce userSharedPrefernce;
    int type;
    String title, card, number;
    String usercard=null;
    Button gallary, camera;
    ImageView choosenimage;
    Bitmap myBitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_image);
        gallary=findViewById(R.id.gallary);
        camera=findViewById(R.id.camera);
        choosenimage=findViewById(R.id.imageview);
        userSharedPrefernce = UserSharedPrefernce.getInstance();
        System.out.println("you have choosen---" + userSharedPrefernce.getNUMBER() + " " + userSharedPrefernce.getCARD());
        type=userSharedPrefernce.getTYPE();
        title=userSharedPrefernce.getTITLE();
        number=userSharedPrefernce.getNUMBER();
        card=userSharedPrefernce.getCARD();
        System.out.println("type=="+type+"   "+"title=="+title+"  "+"number=="+number+"  "+"card=="+card);
        if(!card.equals("null")){

            usercard=""+number+" "+"of"+" "+card+".jpg";
            System.out.println("usercard=="+usercard);

        }
        else
        {
            usercard=""+number+".jpg";
            System.out.println("usercard1=="+usercard);
        }







        gallary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choosenimage.setVisibility(View.VISIBLE);
                matchimage();
                gallary.setVisibility(View.GONE);
                camera.setVisibility(View.GONE);

            }
        });
      /*  choosenimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(viewImageActivity.this, FullScreenimageActivity.class);
                intent.putExtra("image",myBitmap);
                startActivity(intent);
            }
        });*/

    }

    private  void matchimage()
    {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type"+type+"/"+title;
        Log.d("Files", "Path: " + path);
        File f = new File(path);
        File file[] = f.listFiles();
        Log.d("Files", "Size: " + file.length);
        for (int i = 0; i < file.length; i++) {
            //here populate your listview
            Log.d("Files", "FileName:" + file[i].getName());
            if(usercard.equals( file[i].getName())){
                System.out.println("cardmatched=="+ file[i].getName());
                Toast.makeText(this, "found", Toast.LENGTH_LONG).show();
                showimage(path+"/"+file[i].getName());
            }


        }
    }

    private void showimage(String path){

        File imgFile = new File(path);
        if(imgFile.exists())
        {
             myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

            choosenimage.setImageBitmap(myBitmap);
        }

    }


}
