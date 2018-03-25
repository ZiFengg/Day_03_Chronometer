package com.example.zifeng.day_03_chronometer;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ZiFeng on 2018/3/25.
 */
public class MainActivity extends Activity {
    // 声明控件
    Chronometer cm;
    Button start,jishu,stop;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 实例化控件
        cm = findViewById(R.id.test);
        layout = findViewById(R.id.layout);
        start = findViewById(R.id.srart);
        jishu = findViewById(R.id.jishu);
        stop = findViewById(R.id.stop);
        // 创建监听器
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // siwch分支语句，break为跳出循环语句
                switch (v.getId()){
                    case R.id.srart:
                        // 设置计时器开始时间，括号内的内容为当前系统时间
                        cm.setBase(SystemClock.elapsedRealtime());
                        // 开启计时器
                        cm.start();
                        // 禁用和启用按钮
                        start.setEnabled(false);
                        jishu.setEnabled(true);
                        stop.setEnabled(true);
                        break;
                    case R.id.jishu:
                        // 创建一个textview
                        // 计数（系统时间 - 计时器开始时间） / 1000 转换为秒
                        // 再通过String.valueOF再转换为字符串
                        TextView tv  = new TextView(MainActivity.this);
                        tv.setText(String.valueOf(
                                (SystemClock.elapsedRealtime() - cm.getBase()) / 1000) + "秒");
                        // 添加tv控件在layout布局中
                        layout.addView(tv);
                        break;
                    case R.id.stop:
                        // 停止计数
                        cm.stop();
                        // 启动和禁用按钮
                        start.setEnabled(true);
                        jishu.setEnabled(false);
                        stop.setEnabled(false);
                        break;
                }
            }
        };
        // 绑定监听器
        start.setOnClickListener(listener);
        jishu.setOnClickListener(listener);
        stop.setOnClickListener(listener);
        // 为计时器绑定监听器，该监听器在计时器时间发生改变是启用
        cm.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                // 判断系统时间是否大于计时器开始时间3分钟
                if (SystemClock.elapsedRealtime() - chronometer.getBase() > 180 *1000){
                    // 停止计时
                    cm.stop();
                    //启用和禁用按钮
                    stop.setEnabled(false);
                    start.setEnabled(true);
                    jishu.setEnabled(false);
                }
            }
        });
    }
}
