package com.sugoilabs.rapidclick.EasyMode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.sugoilabs.rapidclick.R;
import com.sugoilabs.rapidclick.commonclasses.CommonDialogbox;

import java.util.ArrayList;
import java.util.Collections;

public class Easymode_descending extends AppCompatActivity implements View.OnClickListener
{

    GridView gridview;
    ArrayList<Integer> arr;
    int textone,texttwo,count=0,descrease_value=25;
    ArrayList<Integer> integerArrayList;
    ArrayList<Integer> integerArrayList_afterfifty;
    ImageAdapter  adapter;
    Typeface kbp;
    TextView txttime,reset;
    ImageView imginstruction,imgpause;

    /*for  time*/
    CountDownTimer countDownTimer;
    int countte=0,time_insecond=0;
    long minut=0;
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    /**/
    CommonDialogbox commn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easymode_descending);
        kbp = Typeface.createFromAsset(getAssets(), "KBP.ttf");
        gridview = (GridView) findViewById(R.id.gridview);
        /*for time*/
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=sp.edit();


        countDownTimer= new CountDownTimer(1000000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {

                countte=countte+1;
                time_insecond=time_insecond+1;
                Log.d("checktime=",""+time_insecond);

                if(String.valueOf(minut).length()==1)
                {
                    txttime.setText("0"+minut+" : "+countte);

                }
                else
                    txttime.setText(minut+" : "+countte);

                long elepsedtime=1000-(millisUntilFinished/1000);

                if(elepsedtime%60==0 && countte==60)
                {
                    countte=0;
                    minut=minut+1;
                    if(String.valueOf(minut).length()==1)
                    {
                        txttime.setText("0"+minut+" : "+countte);

                    }
                    else
                        txttime.setText(minut+" : "+countte);
                }

            }

            @Override
            public void onFinish()
            {


            }
        };
        /**/
        commn=new CommonDialogbox(Easymode_descending.this);
        commn.instructionDialog("Select numbers (50 to 1) displayed on the screen in descending order with minimum number clicks and as fast as possible.",countDownTimer);

       /* RotateAnimation rotate= (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotateanimation);
        gridview.startAnimation(rotate);
*/
        txttime= (TextView) findViewById(R.id.txttime);
        reset= (TextView) findViewById(R.id.txtreset);
        reset.setTypeface(kbp);
        txttime.setTypeface(kbp);
        txttime.setShadowLayer(20,5,5,getResources().getColor(R.color.black));

        imginstruction= (ImageView) findViewById(R.id.imginstruction);
        imgpause= (ImageView) findViewById(R.id.imgpause);

        imgpause.setOnClickListener(this);
        imginstruction.setOnClickListener(this);
        reset.setOnClickListener(this);


        arr=new ArrayList<>();
        integerArrayList=new ArrayList<>();

        for(int i=50;i >= 1;i--)
        {
            integerArrayList.add(i);
        }

        for (int i = 50; i > 25; i--)
        {
            arr.add(i);
        }

        Collections.shuffle(arr);

        adapter=new ImageAdapter(getApplicationContext());
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Log.d("value_bva",""+integerArrayList.get(count)+"==="+gridview.getAdapter().getItem(i));

                TextView textView= (TextView) view;

                if(integerArrayList.get(count)==gridview.getAdapter().getItem(i))
                {
                    if(descrease_value <=0)
                    {
                        RotateAnimation rotate= (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotateanimation);
                        textView.setAnimation(rotate);
                        textView.setText("");
                        textView.setBackground(getResources().getDrawable(R.drawable.box));

                    }

                    else
                    {
                        arr.set(i,descrease_value);
                        adapter.notifyDataSetChanged();
                    }
                }

                else
                {
                    /*Intent intent=getIntent();
                    finish();
                    startActivity(intent);*/
                    count=count-1;
                    descrease_value=descrease_value+1;
                }

                count=count+1;
                descrease_value=descrease_value-1;

                if(count==50)
                {
                    //Toast.makeText(Easymode_descending.this,"50", Toast.LENGTH_LONG).show();4

                    editor.putString("leveleasy2","l2_complete");
                    editor.commit();

                    CommonDialogbox  dialogbox=new CommonDialogbox(Easymode_descending.this);
                    dialogbox.showscoreDialog("leveleasy_2",time_insecond,countte);


                }

            }
        });
    }

    class ImageAdapter extends BaseAdapter
    {
        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount()
        {
            Log.d("array_size==",""+arr.size());
            return arr.size();
        }

        public Object getItem(int position) {
            return arr.get(position);
        }

        public long getItemId(int position)
        {
            return arr.get(position);
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent)
        {
            TextView textView;
            if (convertView == null)
            {
                // if it's not recycled, initialize some attributes
                textView = new TextView(mContext);
                textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                textView.setTextSize(30f);
                textView.setTypeface(kbp);
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setBackground(getResources().getDrawable(R.drawable.box));
                textView.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
                textView.setGravity(Gravity.CENTER);
            }

            else
            {
                textView = (TextView) convertView;
            }

            Log.d("textt=",""+arr.get(position));
            textView.setText(""+arr.get(position));
            return textView;
        }


    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imginstruction:

                countDownTimer.cancel();
                commn=new CommonDialogbox(Easymode_descending.this);
                commn.instructionDialog("Select numbers (50 to 1) displayed on the screen in descending order with minimum number clicks and as fast as possible.",countDownTimer);

                break;
            case R.id.imgpause:

                countDownTimer.cancel();
                commn=new CommonDialogbox(Easymode_descending.this);
                commn.PauseDialog(countDownTimer,"leveleasy_2");
                break;
            case R.id.txtreset:

                Intent intent=getIntent();
                finish();
                overridePendingTransition(0,0); //for start without restart
                startActivity(intent);
                overridePendingTransition(0,0);
                break;
        }
    }
}
