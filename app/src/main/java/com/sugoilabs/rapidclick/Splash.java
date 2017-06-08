package com.sugoilabs.rapidclick;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    MediaPlayer mp;
    Typeface kbp;
    TextView rapidclick;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        kbp = Typeface.createFromAsset(getAssets(), "KBP.ttf");

        rapidclick= (TextView) findViewById(R.id.rapidclick);
        rapidclick.setTypeface(kbp);

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        if(isReadStorageAllowed())
        {
            //If permission is already having then showing the toast
            netValidator();
            //  return;
        }
        else
            //If the app has not the permission then asking for the permission
            requestStoragePermission();
    }

    public void netValidator()
    {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                forNext();
            }
        }, 800);
    }
    private boolean isReadStorageAllowed()
    {
        //Getting the permission status

        int resultread = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int resultwrite = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

       //If permission is granted returning true
        if (resultread == PackageManager.PERMISSION_GRANTED && resultwrite == PackageManager.PERMISSION_GRANTED )
        {
            return true;
        }

        else
        {
            //If permission is not granted returning false
            return false;
        }
    }
    private void requestStoragePermission()
    {

        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE))
        {

            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission

            //  Toast.makeText(Splash.this, "For Marsmallow,we do need this permission", Toast.LENGTH_SHORT).show();
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == 1)
        {

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {

                netValidator();
                //Displaying a toast
                // Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }
            else
            {
                // finish();
                //Displaying another toast if permission is not granted
                // Toast.makeText(this,"You have just denied the permission",Toast.LENGTH_LONG).show();

                final AlertDialog.Builder alertDialog;
                DialogInterface.OnClickListener diloagclicklistener=new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        switch (i){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                requestStoragePermission();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicke
                                dialogInterface.dismiss();
                                finish();
                                break;
                        }
                    }
                };
                alertDialog=new AlertDialog.Builder(Splash.this);
                alertDialog.setMessage("In order to play this game you need to give permission ").setPositiveButton("Ok",diloagclicklistener).
                        setNegativeButton("",diloagclicklistener).show();
            }
        }
    }

    private void forNext()
    {
            Intent mainIntent = new Intent(Splash.this, MainActivity.class);
            startActivity(mainIntent);
            finish();

    }


}
