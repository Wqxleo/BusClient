package com.wqxiu.wangq.busclient.Activity;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.wqxiu.wangq.busclient.Entity.Bus;
import com.wqxiu.wangq.busclient.R;
import com.wqxiu.wangq.busclient.Service.HttpCallbackListener;
import com.wqxiu.wangq.busclient.Service.HttpUtil;
import com.wqxiu.wangq.busclient.Service.SocketClient;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private Button up; //上升按钮
    private Button down;
    private TextView peoplenum;
    private TextView tem;
    private TextView hum;
    private TextView light;
    private TextView cardis1;
    private TextView cardis2;
    private TextView shake;
    private TextView smoke;
    private Handler handler = new Handler();


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity);
        up = (Button)findViewById(R.id.buttonup);
        down = (Button)findViewById(R.id.buttondown);
        tem = (TextView)findViewById(R.id.tem);
        hum = (TextView)findViewById(R.id.hum);
        light = (TextView)findViewById(R.id.light);
        cardis1 = (TextView)findViewById(R.id.cardis1);
        cardis2 = (TextView)findViewById(R.id.cardis2);
        peoplenum = (TextView)findViewById(R.id.peoplenum);
        shake = (TextView) findViewById(R.id.shake);
        smoke = (TextView) findViewById(R.id.smoke);
        new Thread(){
            @Override
            public void run() {
                try {
                    //System.out.println("连接");
                    SocketClient.SocketClient();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();


        // 初始化定时器



        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("多次连接socket。。。。");
                try {
                    SocketClient.PostMsg();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {//使用Handler更新UI；当然这里也可以使用sendMessage();handMessage()来进行操作；
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void run() {

                        //System.out.println("温湿度信息： " + Bus.tem + " " + "......");
                        tem.setText(Bus.tem);
                        hum.setText(Bus.hum);
                        light.setText(Bus.light);
                        cardis1.setText(Bus.cardis1);
                        cardis2.setText(Bus.cardis2);
                        peoplenum.setText(Bus.people);
                        if(Bus.shake.equals("1")) {

                            shake.setText("有震动");
                            shake.setTextColor(R.color.colorRed);
                        }
                        else {

                            shake.setText("无震动");
                            shake.setTextColor(R.color.colorWhite);
                        }
                        if(Bus.smoke.equals("1")){
                            smoke.setText("有烟雾");
                            smoke.setTextColor(R.color.colorRed);
                        }
                        else{
                            smoke.setText("无烟雾");
                            smoke.setTextColor(R.color.colorWhite);
                        }
                    }
                });
            }
        }, 200,1000);



        up.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("窗帘上升！！");
                        String address1 = "http://192.168.43.143:8096/businfo/curtainup";
                        //构造HashMap
                        try {
                            //构造完整URL
                            //String compeletedURL = HttpUtil.getURLWithParams(address, params);
                            //发送请求

                            HttpUtil.RegistByPost(address1, new HttpCallbackListener() {
                                @Override
                                public void onFinish(String response) {
                        /*Message message = new Message();
                        message.obj = response;
                        mHandler.sendMessage(message);*/
                                }

                                @Override
                                public void onError(Exception e) {
                        /*Message message = new Message();
                        message.obj = e.toString();
                        mHandler.sendMessage(message);*/
                                }


                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("停下！！");
                        String address2 = "http://192.168.43.143:8096/businfo/curtainstop";
                        //构造HashMap
                        try {
                            //构造完整URL
                            //String compeletedURL = HttpUtil.getURLWithParams(address, params);
                            //发送请求

                            HttpUtil.RegistByPost(address2, new HttpCallbackListener() {
                                @Override
                                public void onFinish(String response) {
                        /*Message message = new Message();
                        message.obj = response;
                        mHandler.sendMessage(message);*/
                                }

                                @Override
                                public void onError(Exception e) {
                        /*Message message = new Message();
                        message.obj = e.toString();
                        mHandler.sendMessage(message);*/
                                }


                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                }
                    return true;
            }
        });
        down.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        String address1 = "http://192.168.43.143:8096/businfo/curtaindown";
                        //构造HashMap
                        try {
                            //构造完整URL
                            //String compeletedURL = HttpUtil.getURLWithParams(address, params);
                            //发送请求

                            HttpUtil.RegistByPost(address1, new HttpCallbackListener() {
                                @Override
                                public void onFinish(String response) {
                        /*Message message = new Message();
                        message.obj = response;
                        mHandler.sendMessage(message);*/
                                }

                                @Override
                                public void onError(Exception e) {
                        /*Message message = new Message();
                        message.obj = e.toString();
                        mHandler.sendMessage(message);*/
                                }


                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        String address2 = "http://192.168.43.143:8096/businfo/curtainstop";
                        //构造HashMap
                        try {
                            //构造完整URL
                            //String compeletedURL = HttpUtil.getURLWithParams(address, params);
                            //发送请求

                            HttpUtil.RegistByPost(address2, new HttpCallbackListener() {
                                @Override
                                public void onFinish(String response) {
                        /*Message message = new Message();
                        message.obj = response;
                        mHandler.sendMessage(message);*/
                                }

                                @Override
                                public void onError(Exception e) {
                        /*Message message = new Message();
                        message.obj = e.toString();
                        mHandler.sendMessage(message);*/
                                }


                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                }
                return true;
            }
        });

    }
}
