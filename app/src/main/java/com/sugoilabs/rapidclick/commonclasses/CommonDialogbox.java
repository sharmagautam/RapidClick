package com.sugoilabs.rapidclick.commonclasses;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.CountDownTimer;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
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
import com.sugoilabs.rapidclick.MainActivity;
import com.sugoilabs.rapidclick.R;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by sugoilabs on 19/4/17.
 */
public class CommonDialogbox implements View.OnClickListener
{
    Typeface kbp;
    Dialog dialog,dialogcnfrm,dialogpause,dialogintro,dilaougegameover;
    Context context;
    CountDownTimer countDownTimer;
    SharedPreferences sp;
    SharedPreferences.Editor editor;
    String levels;
    String t;
    public CommonDialogbox()
    {
    }
    public CommonDialogbox(Context context)
    {
        kbp = Typeface.createFromAsset(context.getAssets(), "KBP.ttf");
        this.context=context;

        sp= PreferenceManager.getDefaultSharedPreferences(context);
        editor=sp.edit();
    }

    public void instructionDialog(String str,CountDownTimer countDown)
    {
        countDownTimer=countDown;
        dialogintro=new Dialog(context);
        dialogintro.setContentView(R.layout.instructiondialog);

        dialogintro.getWindow().setBackgroundDrawableResource(android.R.color.white);
        dialogintro.setCanceledOnTouchOutside(false);
        dialogintro.setCancelable(false);

        WindowManager.LayoutParams params = dialogintro.getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;

        TextView text= (TextView) dialogintro.findViewById(R.id.text);
        TextView instruction= (TextView) dialogintro.findViewById(R.id.txtinstruction);
        TextView play= (TextView) dialogintro.findViewById(R.id.play);

        text.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));
        instruction.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));

        text.setText(str);

        text.setTypeface(kbp);
        instruction.setTypeface(kbp);
        play.setOnClickListener(this);

        dialogintro.show();

    }

    public void PauseDialog(CountDownTimer countDownTimer,String level)
    {
        this.countDownTimer=countDownTimer;
        levels=level;

        dialogpause=new Dialog(context);
        dialogpause.setContentView(R.layout.pause_dialog);

        dialogpause.getWindow().setBackgroundDrawableResource(android.R.color.white);
        dialogpause.setCanceledOnTouchOutside(false);
        dialogpause.setCancelable(false);

        WindowManager.LayoutParams params = dialogpause.getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;

        TextView tvcontnue= (TextView) dialogpause.findViewById(R.id.txtcontinue);
        TextView tvreset= (TextView) dialogpause.findViewById(R.id.txtreset);
        TextView tvhome= (TextView) dialogpause.findViewById(R.id.txthome);

        tvcontnue.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));
        tvreset.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));
        tvhome.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));

        tvcontnue.setOnClickListener(this);
        tvreset.setOnClickListener(this);
        tvhome.setOnClickListener(this);

        tvcontnue.setTypeface(kbp);
        tvreset.setTypeface(kbp);
        tvhome.setTypeface(kbp);

        dialogpause.show();
    }

    public void showscoreDialog(String level,int time_insecond,int time)
    {
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.levelcompletion_dialog);

        dialog.getWindow().setBackgroundDrawableResource(android.R.color.white);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;

        levels=level;

        final TextView tvshare= (TextView) dialog.findViewById(R.id.share);
        final TextView tvhighscore= (TextView) dialog.findViewById(R.id.highscore);
        final TextView tvscore= (TextView) dialog.findViewById(R.id.newscore);
        TextView time_second= (TextView) dialog.findViewById(R.id.txt_time_insecond);
        TextView levelcomplete= (TextView) dialog.findViewById(R.id.levelcomplete);
        TextView levelcomplete_text= (TextView) dialog.findViewById(R.id.textlevelcom);

        tvshare.setTypeface(kbp);
        tvhighscore.setTypeface(kbp);
        tvscore.setTypeface(kbp);
        levelcomplete.setTypeface(kbp);
        levelcomplete_text.setTypeface(kbp);


        tvshare.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));
        levelcomplete_text.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));
        tvhighscore.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));
        tvscore.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));
        levelcomplete.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));

        ImageView imgmenu= (ImageView) dialog.findViewById(R.id.imgmenu);
        ImageView imgplayagain= (ImageView) dialog.findViewById(R.id.imgplayagain);
        ImageView imgnext= (ImageView) dialog.findViewById(R.id.imgnext);

        Log.d("levels==",levels);

        if(levels.equals("leveleasy_4") || levels.equals("levelhard_4") )
        {
            imgnext.setVisibility(View.GONE);
        }

        imgmenu.setOnClickListener(this);
        imgplayagain.setOnClickListener(this);
        imgnext.setOnClickListener(this);
        tvshare.setOnClickListener(this);

        tvscore.setText("New Score : "+time_insecond+" sec");

        time_second=(TextView) dialog.findViewById(R.id.txt_time_insecond);
        time_second.setText(""+time_insecond);

        String timetext=time_second.getText().toString();
        int tim1=0,tim2=0;

        if(timetext.equals(""))
        {
            tim1=0;
        }

        else
        {
            tim1= Integer.parseInt(time_second.getText().toString());

        }

        /*for take the value of each level*/
        if(levels.equals("leveleasy_1"))
        {
             t=sp.getString("scoresec","");
        }
        if(levels.equals("leveleasy_2"))
        {
             t=sp.getString("scoresecel2","");
        }
        if(levels.equals("leveleasy_3"))
        {
             t=sp.getString("scoresecel3","");
        }
        if(levels.equals("leveleasy_4"))
        {
             t=sp.getString("scoresecel4","");
        }

        if(levels.equals("levelhard_1"))
        {
             t=sp.getString("scoresechl1","");
        }

        if(levels.equals("levelhard_2"))
        {
             t=sp.getString("scoresechl2","");
        }

        if(levels.equals("levelhard_3"))
        {
             t=sp.getString("scoresechl3","");
        }
        if(levels.equals("levelhard_4"))
        {
             t=sp.getString("scoresechl4","");
        }

        /**/



        int t1=0;

        if(t.equals(""))
        {
            if(levels.equals("leveleasy_1"))
            {
                editor.putString("score",""+time);
                editor.putString("scoresec",time_second.getText().toString());
                editor.commit();

            }
            if(levels.equals("leveleasy_2"))
            {
                editor.putString("scoreel2",""+time);
                editor.putString("scoresecel2",time_second.getText().toString());
                editor.commit();
            }
            if(levels.equals("leveleasy_3"))
            {

                editor.putString("scoreel3",""+time);
                editor.putString("scoresecel3",time_second.getText().toString());
                editor.commit();
            }
            if(levels.equals("leveleasy_4"))
            {
                editor.putString("scoreel4",""+time);
                editor.putString("scoresecel4",time_second.getText().toString());
                editor.commit();

            }

            if(levels.equals("levelhard_1"))
            {
                editor.putString("scorehl1",""+time);
                editor.putString("scoresechl1",time_second.getText().toString());
                editor.commit();
            }

            if(levels.equals("levelhard_2"))
            {
                editor.putString("scorehl2",""+time);
                editor.putString("scoresechl2",time_second.getText().toString());
                editor.commit();
            }

            if(levels.equals("levelhard_3"))
            {
                editor.putString("scorehl3",""+time);
                editor.putString("scoresechl3",time_second.getText().toString());
                editor.commit();
            }
            if(levels.equals("levelhard_4"))
            {
                editor.putString("scorehl4",""+time);
                editor.putString("scoresechl4",time_second.getText().toString());
                editor.commit();
            }

        }
        else
        {
            t1= Integer.parseInt(t);
        }

        Log.d("check_max_time=","tim1==="+tim1+"t1=="+t1);

        if( tim1<t1)
        {
            Log.d("check_max_time=","inside if");

            if(levels.equals("leveleasy_1"))
            {
                editor.putString("score",""+time);
                editor.putString("scoresec",time_second.getText().toString());
                editor.commit();

            }
            if(levels.equals("leveleasy_2"))
            {
                editor.putString("scoreel2",""+time);
                editor.putString("scoresecel2",time_second.getText().toString());
                editor.commit();
            }
            if(levels.equals("leveleasy_3"))
            {

                editor.putString("scoreel3",""+time);
                editor.putString("scoresecel3",time_second.getText().toString());
                editor.commit();
            }
            if(levels.equals("leveleasy_4"))
            {
                editor.putString("scoreel4",""+time);
                editor.putString("scoresecel4",time_second.getText().toString());
                editor.commit();

            }

            if(levels.equals("levelhard_1"))
            {
                editor.putString("scorehl1",""+time);
                editor.putString("scoresechl1",time_second.getText().toString());
                editor.commit();
            }

            if(levels.equals("levelhard_2"))
            {
                editor.putString("scorehl2",""+time);
                editor.putString("scoresechl2",time_second.getText().toString());
                editor.commit();
            }

            if(levels.equals("levelhard_3"))
            {
                editor.putString("scorehl3",""+time);
                editor.putString("scoresechl3",time_second.getText().toString());
                editor.commit();
            }
            if(levels.equals("levelhard_4"))
            {
                editor.putString("scorehl4",""+time);
                editor.putString("scoresechl4",time_second.getText().toString());
                editor.commit();
            }
        }


        if(levels.equals("leveleasy_1"))
        {
            tvhighscore.setText("High Score : "+sp.getString("scoresec","0")+" sec");

        }
        if(levels.equals("leveleasy_2"))
        {
            tvhighscore.setText("High Score : "+sp.getString("scoresecel2","0")+" sec");
        }
        if(levels.equals("leveleasy_3"))
        {
            tvhighscore.setText("High Score : "+sp.getString("scoresecel3","0")+" sec");

        }
        if(levels.equals("leveleasy_4"))
        {
            tvhighscore.setText("High Score : "+sp.getString("scoresecel4","0")+" sec");

        }

        if(levels.equals("levelhard_1"))
        {
            tvhighscore.setText("High Score : "+sp.getString("scoresechl1","0")+" sec");
        }

        if(levels.equals("levelhard_2"))
        {
            tvhighscore.setText("High Score : "+sp.getString("scoresechl2","0")+" sec");
        }

        if(levels.equals("levelhard_3"))
        {
            tvhighscore.setText("High Score : "+sp.getString("scoresechl3","0")+" sec");
        }
        if(levels.equals("levelhard_4"))
        {
            tvhighscore.setText("High Score : "+sp.getString("scoresechl4","0")+" sec");

        }

        dialog.show();
    }

    public void showconfirmationDialog()
    {
        dialogcnfrm = new Dialog(context);
        dialogcnfrm.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogcnfrm.setContentView(R.layout.showconfirmationdialog);

        dialogcnfrm.getWindow().setBackgroundDrawableResource(android.R.color.white);
        dialogcnfrm.setCanceledOnTouchOutside(false);
        dialogcnfrm.setCancelable(false);

        WindowManager.LayoutParams params = dialogcnfrm.getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;

        final TextView tvtext= (TextView) dialogcnfrm.findViewById(R.id.confrmtext);
        tvtext.setTypeface(kbp);
        tvtext.setShadowLayer(20,5,5,context.getResources().getColor(R.color.black));

        ImageView imgok= (ImageView) dialogcnfrm.findViewById(R.id.imgok);
        ImageView imgcancel= (ImageView) dialogcnfrm.findViewById(R.id.imgcancel);


        imgcancel.setOnClickListener(this);
        imgok.setOnClickListener(this);

        dialogcnfrm.show();
    }

    public void showGameOverDialog(String level)
    {

        levels=level;

        dilaougegameover=new Dialog(context);
        dilaougegameover.setContentView(R.layout.gameoverdialog);

        dilaougegameover.getWindow().setBackgroundDrawableResource(android.R.color.white);
        dilaougegameover.setCanceledOnTouchOutside(false);
        dilaougegameover.setCancelable(false);

        WindowManager.LayoutParams params = dilaougegameover.getWindow().getAttributes();

        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.CENTER;

        TextView tvgameover= (TextView) dilaougegameover.findViewById(R.id.gameover);
        ImageView imgplayagain= (ImageView) dilaougegameover.findViewById(R.id.imgplayagain_over);
        ImageView imgmenu= (ImageView) dilaougegameover.findViewById(R.id.imgmenu_over);

        imgplayagain.setOnClickListener(this);
        imgmenu.setOnClickListener(this);

        tvgameover.setTypeface(kbp);


        dilaougegameover.show();
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.play:

                dialogintro.dismiss();
                countDownTimer.start();

                break;
            case R.id.txtcontinue:
                countDownTimer.start();
                dialogpause.dismiss();

                break;
            case R.id.txtreset:
                showconfirmationDialog();
                break;

            case R.id.imgplayagain_over:
                dilaougegameover.dismiss();

                if(levels.equals("levelhard_1"))
                {
                    Intent intent6=new Intent(context, Ascending_using_Gridview.class);
                    context.startActivity(intent6);
                    ((Activity) context).finish();
                }

                if(levels.equals("levelhard_2"))
                {
                    Intent intent4=new Intent(context, DescendingOrder.class);
                    context.startActivity(intent4);
                    ((Activity) context).finish();
                }

                if(levels.equals("levelhard_3"))
                {
                    Intent intent5=new Intent(context, Ascending_order_alphabates.class);
                    context.startActivity(intent5);
                    ((Activity) context).finish();
                }
                if(levels.equals("levelhard_4"))
                {
                    Intent intent6=new Intent(context, Descending_order_Alphabates.class);
                    context.startActivity(intent6);
                    ((Activity) context).finish();
                }
                break;

                case R.id.imgmenu_over:

                dilaougegameover.dismiss();

                Intent intentmenu1=new Intent(context, MainActivity.class);
                intentmenu1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  Intent.FLAG_ACTIVITY_CLEAR_TASK);

                context.startActivity(intentmenu1);
                ((Activity) context).finish();

                break;
            case R.id.imgok:
                dialogpause.dismiss();

                if(levels.equals("leveleasy_1"))
                {
                    Intent intent3=new Intent(context, Easymode_Ascending.class);
                    context.startActivity(intent3);
                    ((Activity) context).finish();

                }
                if(levels.equals("leveleasy_2"))
                {
                    Intent intent1=new Intent(context, Easymode_descending.class);
                    context.startActivity(intent1);
                    ((Activity) context).finish();

                }
                if(levels.equals("leveleasy_3"))
                {
                    Intent intent2=new Intent(context, Easymode_Alphabates_Ascending_order.class);
                    context.startActivity(intent2);
                    ((Activity) context).finish();

                }
                if(levels.equals("leveleasy_4"))
                {
                    Intent intent3=new Intent(context, Easymode_alphabates_descending_order.class);
                    context.startActivity(intent3);
                    ((Activity) context).finish();

                }

                if(levels.equals("levelhard_1"))
                {
                    Intent intent6=new Intent(context, Ascending_using_Gridview.class);
                    context.startActivity(intent6);
                    ((Activity) context).finish();
                }

                if(levels.equals("levelhard_2"))
                {
                    Intent intent4=new Intent(context, DescendingOrder.class);
                    context.startActivity(intent4);
                    ((Activity) context).finish();
                }

                if(levels.equals("levelhard_3"))
                {
                    Intent intent5=new Intent(context, Ascending_order_alphabates.class);
                    context.startActivity(intent5);
                    ((Activity) context).finish();
                }
                if(levels.equals("levelhard_4"))
                {
                    Intent intent6=new Intent(context, Descending_order_Alphabates.class);
                    context.startActivity(intent6);
                    ((Activity) context).finish();
                }


                break;
            case R.id.imgcancel:

                dialogcnfrm.dismiss();

                break;

            case R.id.txthome:

                dialogpause.dismiss();
                Intent intentmenu=new Intent(context, MainActivity.class);
                intentmenu.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intentmenu);
                ((Activity) context).finish();

                break;

            case R.id.imgplayagain:
                dialog.dismiss();

                if(levels.equals("leveleasy_1"))
                {
                    Intent intent3=new Intent(context, Easymode_Ascending.class);
                    context.startActivity(intent3);
                    ((Activity) context).finish();

                }
                if(levels.equals("leveleasy_2"))
                {
                    Intent intent1=new Intent(context, Easymode_descending.class);
                    context.startActivity(intent1);
                    ((Activity) context).finish();

                }
                if(levels.equals("leveleasy_3"))
                {
                    Intent intent2=new Intent(context, Easymode_Alphabates_Ascending_order.class);
                    context.startActivity(intent2);
                    ((Activity) context).finish();

                }
                if(levels.equals("leveleasy_4"))
                {
                    Intent intent3=new Intent(context, Easymode_alphabates_descending_order.class);
                    context.startActivity(intent3);
                    ((Activity) context).finish();

                }

                if(levels.equals("levelhard_1"))
                {
                    Intent intent6=new Intent(context, Ascending_using_Gridview.class);
                    context.startActivity(intent6);
                    ((Activity) context).finish();
                }

                if(levels.equals("levelhard_2"))
                {
                    Intent intent4=new Intent(context, DescendingOrder.class);
                    context.startActivity(intent4);
                    ((Activity) context).finish();
                }

                if(levels.equals("levelhard_3"))
                {
                    Intent intent5=new Intent(context, Ascending_order_alphabates.class);
                    context.startActivity(intent5);
                    ((Activity) context).finish();
                }
                if(levels.equals("levelhard_4"))
                {
                    Intent intent6=new Intent(context, Descending_order_Alphabates.class);
                    context.startActivity(intent6);
                    ((Activity) context).finish();
                }

                break;

            case R.id.imgmenu:

                dialog.dismiss();
                Intent intentmenu3=new Intent(context, MainActivity.class);
                intentmenu3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intentmenu3);
                ((Activity) context).finish();

                break;

            case R.id.imgnext:
                dialog.dismiss();

                if(levels.equals("leveleasy_1"))
                {

                    Intent intent1=new Intent(context, Easymode_descending.class);
                    context.startActivity(intent1);
                    ((Activity) context).finish();
                }
                if(levels.equals("leveleasy_2"))
                {
                    Intent intent2=new Intent(context, Easymode_Alphabates_Ascending_order.class);
                    context.startActivity(intent2);
                    ((Activity) context).finish();
                }
                if(levels.equals("leveleasy_3"))
                {
                    Intent intent3=new Intent(context, Easymode_alphabates_descending_order.class);
                    context.startActivity(intent3);
                    ((Activity) context).finish();

                }
                if(levels.equals("levelhard_1"))
                {
                    Intent intent4=new Intent(context, DescendingOrder.class);
                    context.startActivity(intent4);
                    ((Activity) context).finish();
                }

                if(levels.equals("levelhard_2"))
                {
                    Intent intent5=new Intent(context, Ascending_order_alphabates.class);
                    context.startActivity(intent5);
                    ((Activity) context).finish();
                }
                if(levels.equals("levelhard_3"))
                {
                    Intent intent6=new Intent(context, Descending_order_Alphabates.class);
                    context.startActivity(intent6);
                    ((Activity) context).finish();
                }

                break;

            case R.id.share:

                takeScreenshot();
                break;

        }
    }

    private void takeScreenshot()
    {
        //  Toast.makeText(MainActivity.this,"click_take screen1",Toast.LENGTH_LONG).show();
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try
        {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
            Log.d("mPath==", mPath.toString());
            // create bitmap screen capture
            View v1 = dialog.getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);
            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            // openScreenshot(imageFile);
            shareImage(imageFile);

        } catch (Throwable e)
        {
            // Several error may come out with file handling or OOM
            Log.d("excep==", e.toString());
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
    /*share*/
    private void shareImage(File file)
    {
        Uri uri = Uri.fromFile(file);

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");

        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try
        {
            context.startActivity(Intent.createChooser(intent, "Share Screenshot"));
        }

        catch (ActivityNotFoundException e)
        {
            Toast.makeText(context, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }
}
