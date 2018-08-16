package com.flytoyou.baseapplication.Tools;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 吐丝工具
 * @author flytoyou
 * @version 1.0.0
 */
public class Hint {
    private double time;
    private static Handler handler;
    private Timer showTimer;
    private Timer cancelTimer;
    private Toast toast;

    public static void showMsg(Context context, int resId){
        Toast.makeText(context,resId, Toast.LENGTH_SHORT).show();
    }

    public static void showMsg(Context context, String text){
        Toast.makeText(context,text, Toast.LENGTH_SHORT).show();
    }
    public static void showMsg(Context context, int text, double time){
        makeText(context, text, time).show();
    }

    public static void showMsg(Context context, String text, double time){
        makeText(context, text, time).show();
    }

    private Hint(){
        showTimer = new Timer();
        cancelTimer = new Timer();
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setToast(Toast toast){
        this.toast = toast;
    }

    public static Hint makeText(Context context, int text, double time){
        Hint toast1= new Hint();
        toast1.setTime(time);
        toast1.setToast(Toast.makeText(context, text, Toast.LENGTH_SHORT));
        handler = new Handler(context.getMainLooper());
        return toast1;
    }

    public static Hint makeText(Context context, String text, double time){
        Hint toast1= new Hint();
        toast1.setTime(time);
        toast1.setToast(Toast.makeText(context, text, Toast.LENGTH_SHORT));
        handler = new Handler(context.getMainLooper());
        return toast1;
    }

    public void show(){
        toast.show();
        if(time > 2){
            showTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new ShowRunnable());
                }
            }, 0, 1900);
        }
        cancelTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new CancelRunnable());
            }
        }, (long)(time * 500));
    }

    private class CancelRunnable implements Runnable {
        @Override
        public void run() {
            showTimer.cancel();
            toast.cancel();
        }
    }

    private class ShowRunnable implements Runnable {
        @Override
        public void run() {
            toast.show();
        }
    }
}
