package com.example.magiccards;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.style.Circle;
import com.github.ybq.android.spinkit.style.Wave;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ApiCalls.CallbackListener<GetImagePojo> {
    Switch one, two, three, four;
    TextView set1, set2, set3, set4;
    Button download, view;
    Boolean flag1=false, flag2=false, flag3=false, flag4=false;
    Boolean Path1=false, Path2=false, Path3=false, Path4=false;
    DownloadManager downloadManager;
    public  static  final int FILE_ATTACHMENT=1;
    int type;
   ImageView done1, done2, done3, done4;
   ProgressBar progressBar;
    UserSharedPrefernce userSharedPrefernce;
    int counter=0;
    long time;
    RelativeLayout parentlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        one=findViewById(R.id.switchone);
        two=findViewById(R.id.switchtwo);
        three=findViewById(R.id.switchthree);
        four=findViewById(R.id.switch4);
        download=findViewById(R.id.downloadbtn);
        view=findViewById(R.id.viewbtn);
        set1=findViewById(R.id.set1);
        set2=findViewById(R.id.set2);
        set3=findViewById(R.id.set3);
        set4=findViewById(R.id.set4);
        done1=findViewById(R.id.done1);
        done2=findViewById(R.id.done2);
        done3=findViewById(R.id.done3);
        done4=findViewById(R.id.done4);
        parentlayout=findViewById(R.id.parentlayout);

        progressBar=findViewById(R.id.spin_kit);
        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        four.setEnabled(true);

        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        hasStoragePermission(FILE_ATTACHMENT);
         userSharedPrefernce=UserSharedPrefernce.getInstance();
         checkpaths();
      // checkfolders();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkflagforpopup();
            }
        });





        one.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
             if(ischecked) {
                 one.setEnabled(false);
             }
          }
     });

     two.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
             if(ischecked)
             {
                 one.setChecked(false);
                 three.setChecked(false);
                 four.setChecked(false);
                 flag2 = true;
             }
             else
             {
                 flag2=false;
             }
         }
     });
     three.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
             if(ischecked)
             {
                 one.setChecked(false);
                 two.setChecked(false);
                 four.setChecked(false);

                     flag3=true; }
             else
             {
                 flag3=false; }

         }
     });
     four.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
             if(ischecked){
                 one.setChecked(false);
                 two.setChecked(false);
                 three.setChecked(false);


                     flag4=true;
             }
         else
             {
                 flag4=false;
             }

         }
     });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                chechswitch();




            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        checkpaths();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        checkpaths();
    }

    private void checkfolders()
    {

        int lengthred2=0, lengthblue2=0;
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type"+2+"/"+"Red2";
            Log.d("Files", "Path: " + path);
            File f = new File(path);

            File file[] = f.listFiles();
            if(file!=null) {
                lengthred2 = file.length;
                System.out.println("sizeblue:" + lengthred2);
            }
            String path1 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type"+2+"/"+"Blue2";
            Log.d("Files", "Path: " + path1);
            File f1 = new File(path);
            File file1[] = f1.listFiles();
            if(file1!=null) {
                lengthblue2 = file1.length;
                System.out.println("sizered:" + lengthblue2);
            }
            if(lengthred2==53&&lengthblue2==53)
            {
                progressBar.setVisibility(View.GONE);
                two.setChecked(false);
               getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }


        int lengthred3=0, lengthblue3=0;
            String path2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type"+3+"/"+"Red3";
            Log.d("Files", "Path: " + path2);
            File f2 = new File(path2);
            File file2[] = f2.listFiles();
            if(file2!=null) {
                lengthred3 = file2.length;
                System.out.println("sizeblue:" + lengthred2);
            }
            String path3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type"+3+"/"+"Blue3";
            Log.d("Files", "Path: " + path3);
            File f3 = new File(path3);
            File file3[] = f3.listFiles();
            if(file3!=null) {
                lengthblue3 = file3.length;
                System.out.println("sizered:" + lengthblue3);
            }
            if(lengthred3==53&&lengthblue3==53)
            {
                progressBar.setVisibility(View.GONE);
                three.setChecked(false);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE); }

        int lengthred4=0, lengthblue4=0;
            String path4 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type"+4+"/"+"Red4";
            Log.d("Files", "Path: " + path4);
            File f4 = new File(path4);
            File file4[] = f4.listFiles();
            if(file4!=null) {
                 lengthred4 = file4.length;
                System.out.println("sizeblue:" + lengthred2);
            }
            String path5 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type"+4+"/"+"Blue4";
            Log.d("Files", "Path: " + path5);
            File f5 = new File(path5);
            File file5[] = f5.listFiles();
            if(file5!=null) {
                lengthblue4 = file5.length;
                System.out.println("sizered:" + lengthblue3);
            }
            if(lengthred4==53&&lengthblue4==53)
            {
                progressBar.setVisibility(View.GONE);
                four.setChecked(false);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }

    }

    private void checkflagforpopup() {
        if (flag2) {
            if(!Path2)
            {
                Toast.makeText(this, "Please download this set first", Toast.LENGTH_SHORT).show();
            }
            else
            {
                one.setChecked(false);
                three.setChecked(false);
                four.setChecked(false);
                createpopupmenu(2);
            }


        }
        if (flag3) {

            if(!Path3)
            {
                Toast.makeText(this, "Please download this set first", Toast.LENGTH_SHORT).show();
            }else {
                one.setChecked(false);
                two.setChecked(false);
                four.setChecked(false);

                createpopupmenu(3);
            }

        }
        if (flag4) {
            if(!Path4){
                Toast.makeText(this, "Please download this set first", Toast.LENGTH_SHORT).show();}
            else{
            one.setChecked(false);
            two.setChecked(false);
            three.setChecked(false);
            createpopupmenu(4);}

        }
        if (!flag2 || !flag3 || !flag4){
            Toast.makeText(this, "Please select at least one set", Toast.LENGTH_SHORT).show();
        }

    }


    private void checkpaths()
    {
        int lengthred2=0, lengthblue2=0,lengthred3=0, lengthblue3=0,lengthred4=0,lengthblue4=0;
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type2"+"/Red2";
        String path1=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type2"+"/Blue2";
        File f = new File(path);
        File file[] = f.listFiles();
        if(file!=null) {
            lengthred2 = file.length;
            System.out.println("sizeblue:" + lengthred2);
        }
        File f1=new File(path1);
        File file1[] = f1.listFiles();
        if(file1!=null) {
            lengthblue2 = file1.length;
            System.out.println("sizered:" + lengthblue2);
        }

        if(lengthred2==53&&lengthblue2==53)
        {
            progressBar.setVisibility(View.GONE);
            two.setChecked(false);
            Path2=true;

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE); }
        //***************************************************************************************************//
        String path2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type3"+"/Red3";
        String path3=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type3"+"/Blue3";
        File f2 = new File(path2);
        File file2[] = f2.listFiles();
        if(file2!=null) {
            lengthred3 = file2.length;
            System.out.println("sizeblue:" + lengthred2);
        }
        File f3=new File(path3);
        File file3[] = f3.listFiles();
        if(file3!=null) {
            lengthblue3 = file3.length;
            System.out.println("sizered:" + lengthblue3);
        }
        if(lengthred3==53&&lengthblue3==53)
        {
            progressBar.setVisibility(View.GONE);
            three.setChecked(false);
            Path3=true;

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE); }
        //********************************************************************************************************//

        String path4= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type4"+"/Red4";
        String path5=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+"/Magiccards"+"/type4"+"/Blue4";
        File f4 = new File(path4);
        File file4[] = f4.listFiles();
        if(file4!=null) {
            lengthred4 = file4.length;
            System.out.println("sizeblue:" + lengthred4);
        }
        File f5=new File(path5);
        File file5[] = f5.listFiles();
        if(file5!=null) {
            lengthblue4 = file5.length;
            System.out.println("sizered:" + lengthblue4);
        }
        if(lengthred4==53&&lengthblue4==53)
        {
            progressBar.setVisibility(View.GONE);
            four.setChecked(false);
            Path4=true;
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }
    private void  chechswitch()
        {
          /* if(flag1)
            {
                Toast.makeText(this, "url not working", Toast.LENGTH_SHORT).show();

            }
            if(flag2)
            {
                ApiCalls.getInstance().login(MainActivity.this, 2);



            }
            if(flag3)
            {
                ApiCalls.getInstance().login(MainActivity.this,3);


            }
            if(flag4)
            {
                ApiCalls.getInstance().login(MainActivity.this,4);


            }
            if(!flag1 &&!flag2 &&!flag3 &&!flag4) {
                Toast.makeText(this, "please select at least one set", Toast.LENGTH_SHORT).show();

            }*/
            if(flag1)
            {

                Toast.makeText(this, "url not working", Toast.LENGTH_SHORT).show();
            }
            else if(flag2)
            {
                ApiCalls.getInstance().login(MainActivity.this, 2);
            }
            else if(flag3)
            {
                ApiCalls.getInstance().login(MainActivity.this,3);
            }
            else if(flag4)
            {
                ApiCalls.getInstance().login(MainActivity.this,4);
            }

            if(!flag1 &&!flag2 &&!flag3 &&!flag4) {
                Toast.makeText(this, "please select at least one set", Toast.LENGTH_SHORT).show();

            }

        }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onSuccess(GetImagePojo model) {

        Toast.makeText(this, "sucess", Toast.LENGTH_SHORT).show();
        System.out.println("data"+model.getData());

        if(flag2)
        {
            if(Path2)
            {

                Toast.makeText(MainActivity.this, "Already downloaded", Toast.LENGTH_SHORT).show();
                two.setChecked(false);

            }
            else {


                Circle circle=new Circle();
     progressBar.setVisibility(View.VISIBLE);
                progressBar.setIndeterminateDrawable(circle);

                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);


                getdata(2, model);
             //   checkstatus(2);
            }


        }
        if(flag3)
        {
            if(Path3)
            {

                Toast.makeText(MainActivity.this, "Already downloaded", Toast.LENGTH_SHORT).show();
                three.setChecked(false);

            }
            else {
                Circle circle=new Circle();
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setIndeterminateDrawable(circle);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                getdata(3, model);
               // checkstatus(3);
            }


        }
        if(flag4)
        {
            if(Path4)
            {

                Toast.makeText(MainActivity.this, "Already downloaded", Toast.LENGTH_SHORT).show();
                four.setChecked(false);

            }
            else {
                Circle circle=new Circle();
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setIndeterminateDrawable(circle);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                getdata(4, model);
               // checkstatus(4);
            }

        }









    }


    @Override
    public void onFailure(String message) {

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getdata(final int t, GetImagePojo model)
    {

        if(hasStoragePermission(FILE_ATTACHMENT))
        {
            for(int i=0;i<=52;i++)
            {
                System.out.println("url " + model.getData().get(0).get(i).getImageUrl());
                String string=model.getData().get(0).get(i).getImageUrl();
                int slashindex=string.lastIndexOf("/");
                int dotindex=string.indexOf(".");



                System.out.println("index "+slashindex);
                String name=string.substring(slashindex+1,dotindex);
                System.out.println("substring   "+name);

                String imgurl = "http://54.183.148.182/casino/public" + model.getData().get(0).get(i).getImageUrl();

                final String newimgurl = imgurl.replaceAll(" ", "%20");

                System.out.println("IMGURL " + newimgurl);
                type=t;


                downloadimage(newimgurl,t, name);


              




            }

            for(int i=0;i<=52;i++){

                System.out.println("url1"+model.getData().get(1).get(i).getImageUrl());
             //   String name1=model.getData().get(0).get(i).getImageUrl();
                String string=model.getData().get(0).get(i).getImageUrl();
                int slashindex1=string.lastIndexOf("/");
                int dotindex1=string.indexOf(".");



                System.out.println("index1 "+slashindex1);
                String name1=string.substring(slashindex1+1,dotindex1);
                System.out.println("substring1   "+name1);
                String imgurl1= "http://54.183.148.182/casino/public" + model.getData().get(1).get(i).getImageUrl();

                final String newimgurl1 = imgurl1.replaceAll(" ", "%20");

                type=t;


                downloadimage1(newimgurl1,t, name1);


            }

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void downloadimage(String url, int type,String name)
    {
        counter++;
        System.out.println("count1--"+counter);
        Uri uri = Uri.parse(url);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("Downloading a file");
        request.setVisibleInDownloadsUi(true);


        request.setDestinationUri(Uri.fromFile(filepath(type, name)));
        ((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
        checkpaths();




    }
    private void downloadimage1(String url1, int type, String name)
    {
        counter++;
        System.out.println("count2--"+counter);
        Uri uri = Uri.parse(url1);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("Downloading a file");
        request.setVisibleInDownloadsUi(true);

        request.setDestinationUri(Uri.fromFile(filepath1(type, name)));
        ((DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
        checkpaths();



    }





    private void checkstatus(final int type)
    {
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
                //    Toast.makeText(getApplicationContext(), "Download completed", Toast.LENGTH_LONG).show();
                    System.out.println("counter "+counter);
                    if(counter==10)
                    {
                        progressBar.setVisibility(View.GONE);
                        counter=0;
                    }





                      switch (type) {
                          case 2:
                              flag2 = false;
                              done2.setVisibility(View.VISIBLE);

                              two.setChecked(false);
                              checkflags();
                              break;
                          case 3:
                              flag3 = false;
                              done3.setVisibility(View.VISIBLE);
                              three.setChecked(false);
                              checkflags();
                              break;
                          case 4:
                              flag4 = false;
                              done4.setVisibility(View.VISIBLE);
                              four.setChecked(false);
                              checkflags();
                              break;

                      }




                }


            }
        };



        registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));

   //     Toast.makeText(this, "final completed", Toast.LENGTH_SHORT).show();



    }

    private void checkflags()
    {
        if(flag1)
        {

            Toast.makeText(this, "url not working", Toast.LENGTH_SHORT).show();
        }
        else if(flag2)
        {
            ApiCalls.getInstance().login(MainActivity.this, 2);
        }
        else if(flag3)
        {
            ApiCalls.getInstance().login(MainActivity.this,3);
        }
        else if(flag4)
        {
            ApiCalls.getInstance().login(MainActivity.this,4);
        }

        if(!flag1 &&!flag2 &&!flag3 &&!flag4) {
           // Toast.makeText(this, "please select at least one set", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == FILE_ATTACHMENT)
            {

            }
               // save(myDrawView.getBitmap());

        }
    }


    private File filepath(int i, String name)
    {

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        System.out.println(root + " Root value in saveImage Function");
        File myDir = new File(root + "/Magiccards"/*+"/type"+i+"/Red"*/);
        File myDir1=new File(myDir,"type"+i);
        File myDir2=new File(myDir1,"Red"+i);



        if (!myDir.exists()) {
            myDir.mkdirs();
        }
        String iname = name+ ".jpg";
        File file = new File(myDir2, iname);
        return  file;
    }
    private File filepath1(int i, String name)
    {

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).toString();
        System.out.println(root + " Root value in saveImage Function");
        File myDir = new File(root + "/Magiccards");
        File myDir1=new File(myDir,"type"+i);
        File myDir2=new File(myDir1,"Blue"+i);



        if (!myDir2.exists()) {
            myDir2.mkdirs();
        }
        String iname = name + ".jpg";
        File file = new File(myDir2, iname);
        return  file;
    }

    private boolean hasStoragePermission(int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, requestCode);
                Toast.makeText(MainActivity.this,"permission not granted",Toast.LENGTH_SHORT).show();
                return false;
            } else {
                return true;
            }
        } else {
            return true;

        }
    }

    private  void createpopupmenu(int type)
    {
        PopupMenu popup = new PopupMenu(MainActivity.this, view);
        popup.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                one.setEnabled(true);
                two.setEnabled(true);
                three.setEnabled(true);
                four.setEnabled(true);

            }
        });
        if(type==2){
        popup.getMenuInflater()
                .inflate(R.menu.menutype2, popup.getMenu());


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId())
                {
                    case  R.id.type21:
                        Toast.makeText(MainActivity.this, "Your selection id"+item.getTitle(), Toast.LENGTH_SHORT).show();
                        passselection(2, "Red2");
                        setenabledtrue(2);
                        break;
                    case R.id.type22:
                        Toast.makeText(MainActivity.this, "Your selection id"+item.getTitle(), Toast.LENGTH_SHORT).show();
                        passselection(2, "Blue2");
                        setenabledtrue(2);

                }
                return true;
            }
        });}
        if(type==3){
            popup.getMenuInflater()
                    .inflate(R.menu.menutype3, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId())
                    {
                        case  R.id.type31:
                            Toast.makeText(MainActivity.this, "Your selection id"+item.getTitle(), Toast.LENGTH_SHORT).show();
                            passselection(3,"Red3");
                            setenabledtrue(3);
                            break;
                        case R.id.type32:
                            Toast.makeText(MainActivity.this, "Your selection id"+item.getTitle(), Toast.LENGTH_SHORT).show();
                            passselection(3, "Blue3");

                            setenabledtrue(3);

                    }
                    return true;
                }
            });}
        if(type==4){
            popup.getMenuInflater()
                    .inflate(R.menu.menutype4, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    switch (item.getItemId())
                    {
                        case  R.id.type41:
                            Toast.makeText(MainActivity.this, "Your selection id"+item.getTitle(), Toast.LENGTH_SHORT).show();
                            passselection(4, "Red4");
                            setenabledtrue(4);
                            break;
                        case R.id.type42:
                            Toast.makeText(MainActivity.this, "Your selection id"+item.getTitle(), Toast.LENGTH_SHORT).show();
                            passselection(4,"Blue4");
                            setenabledtrue(4);

                    }
                    return true;
                }
            });}

        popup.show();

    }

    private void setenabledtrue(int type)
    {

        if(type==2){
            one.setEnabled(true);
            three.setEnabled(true);
            four.setEnabled(true);
            two.setChecked(false);
        }
        if(type==3){
            one.setEnabled(true);
            two.setEnabled(true);
            three.setChecked(false);
            four.setEnabled(true);
        }
        if(type==4){
            one.setEnabled(true);
            two.setEnabled(true);
            three.setChecked(false);
            four.setChecked(false);
        }
    }



    private void passselection(int type, String title)
    {
       userSharedPrefernce.setTITLE(title);
       userSharedPrefernce.setTYPE(type);
        System.out.println("title  "+userSharedPrefernce.getTITLE()+"type  "+userSharedPrefernce.getTYPE());
       Intent intent=new Intent(MainActivity.this, OnetothirteenActivity.class);
       startActivity(intent);

    }

}
