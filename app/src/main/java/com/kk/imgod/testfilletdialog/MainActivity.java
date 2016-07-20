package com.kk.imgod.testfilletdialog;

import android.app.Dialog;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 黑色棱角由来:
 * dialog默认的背景是黑色的，不透明的，当你把背景设置成别的颜色，
 * 并且设置成圆角后，dialog本身所占有的空间，
 * 是包含了圆角的一个正方形，或者长方形,显示出来的圆角，在我看来，就是把最上层显示为圆角
 * 最下面的为黑色的正方形，当设置圆角，多出来的部分显示为黑色，就出现了所谓的黑角
 * 黑色棱角解决:
 * 首先是布局文件。这里，千万不要给最外面的父容器添加背景，如果添加了，那么黑角怎么都隐藏不掉了
 * getWindow().setBackgroundDrawable(new BitmapDrawable());
 * 或者getWindow().setBackgroundDrawableResource(android.R.color.transparent);
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txt_dialog1;
    private TextView txt_dialog2;
    private TextView txt_dialog3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        txt_dialog1.setOnClickListener(this);
        txt_dialog2.setOnClickListener(this);
        txt_dialog3.setOnClickListener(this);
    }

    private void initView() {
        txt_dialog1 = (TextView) findViewById(R.id.txt_dialog1);
        txt_dialog2 = (TextView) findViewById(R.id.txt_dialog2);
        txt_dialog3 = (TextView) findViewById(R.id.txt_dialog3);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_dialog1:
                showDialog1();
                break;
            case R.id.txt_dialog2:
                showDialog2();
                break;
            case R.id.txt_dialog3:
                showDialog3();
                break;
        }
    }

    /**
     * 显示圆角的第一种方式
     */
    private void showDialog1() {
        final Dialog dialog = new Dialog(MainActivity.this, R.style.filletDialog);
        dialog.setContentView(R.layout.dialog);
        dialog.show();

        Window dialogWindow = dialog.getWindow();
        TextView txt_cancel = (TextView) dialogWindow.findViewById(R.id.txt_cancel);
        TextView txt_sure = (TextView) dialogWindow.findViewById(R.id.txt_sure);
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        txt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    /**
     * 显示圆角的第二种方式
     */
    private void showDialog2() {
        final AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.show();
        Window dialogWindow = alertDialog.getWindow();
        dialogWindow.setContentView(R.layout.dialog);
        dialogWindow.setBackgroundDrawable(new BitmapDrawable());
        dialogWindow.setGravity(Gravity.CENTER);
        TextView txt_cancel = (TextView) dialogWindow.findViewById(R.id.txt_cancel);
        TextView txt_sure = (TextView) dialogWindow.findViewById(R.id.txt_sure);
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "取消", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });

        txt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "确定", Toast.LENGTH_SHORT).show();
                alertDialog.dismiss();
            }
        });
    }

    /**
     * 显示圆角的第三种方式
     */
    private void showDialog3() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).show();
        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog);
//        window.setBackgroundDrawableResource(android.R.color.transparent);
        //去掉这行代码之后,就会有黑色棱角出现了
        window.setGravity(Gravity.CENTER);

    }
}
