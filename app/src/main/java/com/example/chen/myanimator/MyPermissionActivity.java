package com.example.chen.myanimator;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.chen.myanimator.fragment.PermissionFragment;
import com.ninetripods.aopermission.permissionlib.PermissionRequestActivity;
import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;
import com.ninetripods.aopermission.permissionlib.annotation.PermissionCanceled;
import com.ninetripods.aopermission.permissionlib.annotation.PermissionDenied;
import com.ninetripods.aopermission.permissionlib.bean.CancelBean;
import com.ninetripods.aopermission.permissionlib.bean.DenyBean;
import com.ninetripods.aopermission.permissionlib.util.SettingUtil;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chen on 2018/4/18.
 */

public class MyPermissionActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_click1,btn_more;
    FrameLayout fl_callpermission;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_permission);
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorAccent));
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            ViewGroup systemContent = findViewById(android.R.id.content);

            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 20);
            statusBarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));

            systemContent.getChildAt(0).setFitsSystemWindows(true);

            systemContent.addView(statusBarView, 0, lp);

        }

       btn_click1 = findViewById(R.id.btn_click1);
       btn_more = findViewById(R.id.btn_click);
       fl_callpermission = findViewById(R.id.fl_container);

       PermissionFragment permissionFragment = new PermissionFragment();
       FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
       transaction.add(R.id.fl_container,permissionFragment);
       transaction.commitAllowingStateLoss();

       btn_click1.setOnClickListener(this);
       btn_more.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_click1://申请单个权限
                callMap();
                break;

            case R.id.btn_click:
                callPhone();
                break;
        }
    }

    public void startPermissionService(View view){
        Intent intent = new Intent(this,PermissionService.class);
        startService(intent);
    }

    @NeedPermission(value = {Manifest.permission.ACCESS_FINE_LOCATION},requestCode = 0)
    public void callMap(){
        Toast.makeText(this,"定位权限申请成功",Toast.LENGTH_SHORT).show();
    }

    @NeedPermission(value = {Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE} ,requestCode = 10)
    public void callPhone(){
        Toast.makeText(this,"相机、电话权限申请成功",Toast.LENGTH_SHORT).show();
    }

    /***
     * 权限被拒绝
     * @param bean
     */
    @PermissionDenied
    public void dealPermission(DenyBean bean){
        if (bean == null) return;
        Toast.makeText(this,"requestCode:"+bean.getRequestCode()+",permission:"+
                Arrays.toString(bean.getDenyList().toArray()),Toast.LENGTH_SHORT).show();
        List<String>denyList = bean.getDenyList();
        switch (bean.getRequestCode()){
            case 10:
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0;i < denyList.size();i++){
                    if (Manifest.permission.CALL_PHONE.equals(denyList.get(i))){
                        stringBuilder.append("电话");
                    }else if (Manifest.permission.CAMERA.equals(denyList.get(i))){
                        stringBuilder.append("相机");
                    }
                }
                stringBuilder.append("权限被禁止，需要手动打开");

                new AlertDialog.Builder(MyPermissionActivity.this)
                        .setTitle("Permission")
                        .setMessage(stringBuilder)
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                SettingUtil.go2Setting(MyPermissionActivity.this);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create().show();
                break;
            case 0:
               new AlertDialog.Builder(MyPermissionActivity.this)
                       .setTitle("Permission")
                       .setMessage("定位权限被禁止，需要手动打开")
                       .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               dialogInterface.dismiss();
                               SettingUtil.go2Setting(MyPermissionActivity.this);
                           }
                       })
                       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               dialogInterface.dismiss();
                           }
                       }).create().show();
                break;
        }
    }

    /***
     * 权限被取消
     * @param cancelBean
     */
    @PermissionCanceled
    public void dealCanclePermission(CancelBean cancelBean){
        Toast.makeText(MyPermissionActivity.this,"requestCode:"+cancelBean.getRequestCode(),Toast.LENGTH_SHORT).show();
    }

}
