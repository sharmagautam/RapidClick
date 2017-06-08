/*
package com.sugoilabs.rapidclick;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IntroductionActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView tvplay,instruction,besttime,level1,level2,level3,level1txt,level2txt,level3txt;
    Typeface digital,black;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    Dialog dialog1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        black = Typeface.createFromAsset(getAssets(), "monstblack.ttf");

        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=sp.edit();

        tvplay= (TextView) findViewById(R.id.playgame);

        level1= (TextView) findViewById(R.id.level1);
        level2= (TextView) findViewById(R.id.level2);
        level3= (TextView) findViewById(R.id.level3);
        level1txt= (TextView) findViewById(R.id.level1_text);
        level2txt= (TextView) findViewById(R.id.level2_text);
        level3txt= (TextView) findViewById(R.id.level3_text);

        instruction= (TextView) findViewById(R.id.instruction);
        besttime= (TextView) findViewById(R.id.besttime);

        besttime.setText("Best time - "+sp.getString("score","0")+" min");

        tvplay.setTypeface(black);
        instruction.setTypeface(black);
        besttime.setTypeface(black);

        level1.setTypeface(black);
        level2.setTypeface(black);
        level3.setTypeface(black);
        level1txt.setTypeface(black);
        level2txt.setTypeface(black);
        level3txt.setTypeface(black);

        tvplay.setOnClickListener(this);
        instruction.setOnClickListener(this);

        level1.setOnClickListener(this);
        level2.setOnClickListener(this);
        level3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        if(view.getId()==R.id.playgame)
        {
            if(sp.getString("level","").equals("1"))
            {
                Intent intent=new Intent(IntroductionActivity.this,Level_two.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
            }
            else if (sp.getString("level","").equals("2"))
            {
                Intent intent=new Intent(IntroductionActivity.this,Level_three.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
            }
            else if (sp.getString("level","").equals("3"))
            {
                Intent intent=new Intent(IntroductionActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
            }
            else
            {
                Intent intent=new Intent(IntroductionActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                finish();
            }

        }
        if(view.getId()==R.id.instruction)
        {
            showscoreDialog();
        }

        if(view.getId()==R.id.level1)
        {
            Intent intent=new Intent(IntroductionActivity.this,MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish();
        }
        if(view.getId()==R.id.level2)
        {
            Intent intent=new Intent(IntroductionActivity.this,Level_two.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish();
        }
        if(view.getId()==R.id.level3)
        {
            Intent intent=new Intent(IntroductionActivity.this,Level_three.class);
            startActivity(intent);
            overridePendingTransition(R.anim.right_in, R.anim.left_out);
            finish();
        }
    }

    public void showscoreDialog()
    {
          dialog1 = new Dialog(IntroductionActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setContentView(R.layout.instructiondialog);

        dialog1.getWindow().setBackgroundDrawableResource(android.R.color.white);
        dialog1.setCanceledOnTouchOutside(true);
        dialog1.setCancelable(true);

        WindowManager.LayoutParams params = dialog1.getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;


        final TextView tvinstruction= (TextView) dialog1.findViewById(R.id.txtinstruction);
        final TextView text= (TextView) dialog1.findViewById(R.id.text);
        final TextView backtext= (TextView) dialog1.findViewById(R.id.share);
        final LinearLayout linearLayout= (LinearLayout) dialog1.findViewById(R.id.txtback);


        tvinstruction.setTypeface(black);
        text.setTypeface(black);
        backtext.setTypeface(black);

        linearLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                dialog1.dismiss();
            }
        });

        dialog1.show();
    }
}
*/
