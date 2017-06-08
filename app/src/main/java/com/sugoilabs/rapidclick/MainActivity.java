package com.sugoilabs.rapidclick;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sugoilabs.rapidclick.EasyMode.Easymode_Alphabates_Ascending_order;
import com.sugoilabs.rapidclick.EasyMode.Easymode_Ascending;
import com.sugoilabs.rapidclick.EasyMode.Easymode_alphabates_descending_order;
import com.sugoilabs.rapidclick.EasyMode.Easymode_descending;
import com.sugoilabs.rapidclick.HardMode.Ascending_order_alphabates;
import com.sugoilabs.rapidclick.HardMode.Ascending_using_Gridview;
import com.sugoilabs.rapidclick.HardMode.DescendingOrder;
import com.sugoilabs.rapidclick.HardMode.Descending_order_Alphabates;
import com.sugoilabs.rapidclick.commonclasses.CommonDialogbox;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView tveasy,tvhard,rapidclick;
    Dialog dialog1;
    String level,mode;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

     TextView tvlevel1,selectlevel;
     TextView tvlevel2;
     TextView tvlevel3;
     TextView tvlevel4;
     Typeface kbp;
    boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kbp = Typeface.createFromAsset(getAssets(), "KBP.ttf");

        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=sp.edit();

        tveasy= (TextView) findViewById(R.id.txtesy);
        tvhard= (TextView) findViewById(R.id.txthard);
        rapidclick= (TextView) findViewById(R.id.rapidclick);

        tveasy.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
        tvhard.setShadowLayer(20,5,5,getResources().getColor(R.color.black));

        tveasy.setTypeface(kbp);
        tvhard.setTypeface(kbp);
        rapidclick.setTypeface(kbp);

        tveasy.setOnClickListener(this);
        tvhard.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.txtesy:
                mode="easy";
                showEasyDialog();
                break;

            case R.id.txthard:
                mode="hard";
                showHardDialog();
                break;

            case R.id.level1:
                level="level1";

                if(mode.equals("easy"))
                {
                    dialog1.dismiss();
                    Intent intent=new Intent(MainActivity.this, Easymode_Ascending.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }

                else
                {
                    dialog1.dismiss();
                    Intent intent=new Intent(MainActivity.this, Ascending_using_Gridview.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
                break;

                case R.id.level2:
                level="level2";

                if(mode.equals("easy") && sp.getString("leveleasy1","").equals("l1_complete") )
                {
                    dialog1.dismiss();
                    Intent intent=new Intent(MainActivity.this, Easymode_descending.class);

                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
                if(mode.equals("hard") && sp.getString("levelhard1","").equals("l1_complete"))
                {
                    dialog1.dismiss();
                    Intent intent=new Intent(MainActivity.this, DescendingOrder.class);

                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }

                break;
            case R.id.level3:
                level="level3";

                if(mode.equals("easy") && sp.getString("leveleasy2","").equals("l2_complete") )
                {
                    dialog1.dismiss();
                    Intent intent=new Intent(MainActivity.this, Easymode_Alphabates_Ascending_order.class);

                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
                if(mode.equals("hard") && sp.getString("levelhard2","").equals("l2_complete"))
                {
                    dialog1.dismiss();
                    Intent intent=new Intent(MainActivity.this, Ascending_order_alphabates.class);

                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
                break;

            case R.id.level4:
                level="level4";
                if(mode.equals("easy") && sp.getString("leveleasy3","").equals("l3_complete") )
                {
                    dialog1.dismiss();
                    Intent intent=new Intent(MainActivity.this, Easymode_alphabates_descending_order.class);

                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
                if(mode.equals("hard") && sp.getString("levelhard3","").equals("l3_complete"))
                {
                    dialog1.dismiss();
                    Intent intent=new Intent(MainActivity.this, Descending_order_Alphabates.class);

                    startActivity(intent);
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
                break;
        }
    }

    public void showEasyDialog()
    {
        dialog1 = new Dialog(MainActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.select_level_dialog);

        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.white);
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.setCancelable(true);

        WindowManager.LayoutParams params = dialog1.getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;

         tvlevel1= (TextView) dialog1.findViewById(R.id.level1);
         selectlevel= (TextView) dialog1.findViewById(R.id.selectleveltext);
         tvlevel2= (TextView) dialog1.findViewById(R.id.level2);
         tvlevel3= (TextView) dialog1.findViewById(R.id.level3);
         tvlevel4= (TextView) dialog1.findViewById(R.id.level4);

        tvlevel1.setTypeface(kbp);
        tvlevel2.setTypeface(kbp);
        tvlevel3.setTypeface(kbp);
        tvlevel4.setTypeface(kbp);

        selectlevel.setTypeface(kbp);
        selectlevel.setShadowLayer(20,5,5,getResources().getColor(R.color.black));

        tvlevel1.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
        tvlevel2.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
        tvlevel3.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
        tvlevel4.setShadowLayer(20,5,5,getResources().getColor(R.color.black));

        Log.d("leveleasy_selected",sp.getString("leveleasy",""));

        if(sp.getString("leveleasy1","").equals("l1_complete"))
        {
            tvlevel2.setBackground(getResources().getDrawable(R.drawable.box_unlock));
        }

        if(sp.getString("leveleasy2","").equals("l2_complete"))
        {
            tvlevel3.setBackground(getResources().getDrawable(R.drawable.box_unlock));
        }

        if(sp.getString("leveleasy3","").equals("l3_complete"))
        {
            tvlevel4.setBackground(getResources().getDrawable(R.drawable.box_unlock));
        }
        if(sp.getString("leveleasy4","").equals("l4_complete"))
        {
            tvlevel4.setBackground(getResources().getDrawable(R.drawable.box_unlock));
        }

        tvlevel1.setOnClickListener(this);
        tvlevel2.setOnClickListener(this);
        tvlevel3.setOnClickListener(this);
        tvlevel4.setOnClickListener(this);

        dialog1.show();
    }

    public void showHardDialog()
    {
        dialog1 = new Dialog(MainActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.select_level_dialog);

        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.white);
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.setCancelable(true);

        WindowManager.LayoutParams params = dialog1.getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;

        tvlevel1= (TextView) dialog1.findViewById(R.id.level1);
        tvlevel2= (TextView) dialog1.findViewById(R.id.level2);
        tvlevel3= (TextView) dialog1.findViewById(R.id.level3);
        tvlevel4= (TextView) dialog1.findViewById(R.id.level4);
        selectlevel= (TextView) dialog1.findViewById(R.id.selectleveltext);

        tvlevel1.setTypeface(kbp);
        tvlevel2.setTypeface(kbp);
        tvlevel3.setTypeface(kbp);
        tvlevel4.setTypeface(kbp);

        selectlevel.setTypeface(kbp);
        selectlevel.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
        tvlevel1.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
        tvlevel2.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
        tvlevel3.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
        tvlevel4.setShadowLayer(20,5,5,getResources().getColor(R.color.black));

        if(sp.getString("levelhard1","").equals("l1_complete"))
        {
            tvlevel2.setBackground(getResources().getDrawable(R.drawable.box_unlock));
        }

        if(sp.getString("levelhard2","").equals("l2_complete"))
        {
            tvlevel3.setBackground(getResources().getDrawable(R.drawable.box_unlock));
        }

        if(sp.getString("levelhard3","").equals("l3_complete"))
        {
            tvlevel4.setBackground(getResources().getDrawable(R.drawable.box_unlock));
        }
        if(sp.getString("levelhard4","").equals("l4_complete"))
        {
            tvlevel4.setBackground(getResources().getDrawable(R.drawable.box_unlock));
        }

        tvlevel1.setOnClickListener(this);
        tvlevel2.setOnClickListener(this);
        tvlevel3.setOnClickListener(this);
        tvlevel4.setOnClickListener(this);

        dialog1.show();
    }

}
