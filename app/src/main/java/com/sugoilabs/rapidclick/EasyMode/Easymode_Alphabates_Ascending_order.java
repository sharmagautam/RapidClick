package com.sugoilabs.rapidclick.EasyMode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
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

public class Easymode_Alphabates_Ascending_order extends AppCompatActivity implements View.OnClickListener
{

    GridView gridview;
    ArrayList<String> arr,arr_copy;
    int textone,texttwo,count=0,increase_value=26;
    ArrayList<String> integerArrayList;
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
        CommonDialogbox dialogbox;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easymode__alphabates__ascending_order);

      //  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        gridview = (GridView) findViewById(R.id.gridview);
        txttime= (TextView) findViewById(R.id.txttime);


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
        dialogbox=new CommonDialogbox(Easymode_Alphabates_Ascending_order.this);
        dialogbox.instructionDialog("Select alphabets (A to Z) displayed on the screen in ascending order with minimum number clicks and as fast as possible.",countDownTimer);


        kbp = Typeface.createFromAsset(getAssets(), "KBP.ttf");

      /*  RotateAnimation rotate= (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotateanimation);
        gridview.startAnimation(rotate);
*/
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
        arr_copy=new ArrayList<>();

        integerArrayList=new ArrayList<>();

            integerArrayList.add("A");integerArrayList.add("B");integerArrayList.add("C");integerArrayList.add("D");integerArrayList.add("E");
            integerArrayList.add("F");integerArrayList.add("G");integerArrayList.add("H");integerArrayList.add("I");integerArrayList.add("J");
            integerArrayList.add("K");integerArrayList.add("L");integerArrayList.add("M");integerArrayList.add("N");integerArrayList.add("O");
            integerArrayList.add("P");integerArrayList.add("Q");integerArrayList.add("R");integerArrayList.add("S");integerArrayList.add("T");
            integerArrayList.add("U");integerArrayList.add("V");integerArrayList.add("W");integerArrayList.add("X");integerArrayList.add("Y");
            integerArrayList.add("Z");

            arr.add("A");
            arr.add("B");
            arr.add("C");
            arr.add("D"); arr.add("E");arr.add("F");arr.add("G");arr.add("H");arr.add("I");arr.add("J");
            arr.add("K"); arr.add("L");arr.add("M");arr.add("N");arr.add("O");arr.add("P");arr.add("Q");
            arr.add("R");
            arr.add("S");
            arr.add("T");
            arr.add("U");
            arr.add("V");
            arr.add("W");
            arr.add("X");
            arr.add("Y");

          Collections.shuffle(arr);

        adapter=new ImageAdapter(getApplicationContext());
        gridview.setAdapter(adapter);

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Log.d("value_bva","count="+count+integerArrayList.get(count)+"==="+gridview.getAdapter().getItem(i));

                TextView textView= (TextView) view;

                if(integerArrayList.get(count)==gridview.getAdapter().getItem(i))
                {
                    if(count==0)
                    {
                        arr.set(i,"Z");
                        adapter.notifyDataSetChanged();
                    }
                    else
                    {
                        RotateAnimation rotate= (RotateAnimation) AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotateanimation);
                        textView.setAnimation(rotate);
                        textView.setText("");
                        textView.setBackground(getResources().getDrawable(R.drawable.box));
                    }

                }

                else
                {
                    count=count-1;
                }

                count=count+1;

                if(count==26)
                {
                   // Toast.makeText(Easymode_Alphabates_Ascending_order.this,"26", Toast.LENGTH_LONG).show();
                    editor.putString("leveleasy3","l3_complete");
                    editor.commit();

                    CommonDialogbox  dialogbox=new CommonDialogbox(Easymode_Alphabates_Ascending_order.this);
                    dialogbox.showscoreDialog("leveleasy_3",time_insecond,countte);

                }
            }
        });

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imginstruction:

                countDownTimer.cancel();
                dialogbox=new CommonDialogbox(Easymode_Alphabates_Ascending_order.this);
                dialogbox.instructionDialog("Select alphabets (A to Z) displayed on the screen in ascending order with minimum number clicks and as fast as possible.",countDownTimer);

                break;
            case R.id.imgpause:

                countDownTimer.cancel();
                dialogbox=new CommonDialogbox(Easymode_Alphabates_Ascending_order.this);
                dialogbox.PauseDialog(countDownTimer,"leveleasy_3");

                countDownTimer.cancel();

                break;
            case R.id.txtreset:

                /*Intent intent=getIntent();
                finish();
                overridePendingTransition(0,0); //for start without restart
                startActivity(intent);
                overridePendingTransition(0,0);*/
                break;
        }
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
            return 0;
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
                textView.setTextColor(getResources().getColor(R.color.white));
                textView.setBackground(getResources().getDrawable(R.drawable.box));
                textView.setTextSize(30f);
                textView.setTypeface(kbp);
                textView.setShadowLayer(20,5,5,getResources().getColor(R.color.black));
                //textView.setTextAppearance(R.style.shadoweffect);
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




}
