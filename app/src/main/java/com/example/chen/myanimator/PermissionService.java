package com.example.chen.myanimator;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;
import com.ninetripods.aopermission.permissionlib.annotation.PermissionCanceled;
import com.ninetripods.aopermission.permissionlib.annotation.PermissionDenied;
import com.ninetripods.aopermission.permissionlib.bean.CancelBean;
import com.ninetripods.aopermission.permissionlib.bean.DenyBean;
import com.ninetripods.aopermission.permissionlib.util.PermissionUtil;
import com.ninetripods.aopermission.permissionlib.util.SettingUtil;

/**
 * Created by chen on 2018/4/18.
 */

public class PermissionService extends Service {

    public PermissionService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return (IBinder) new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                requestCamera();
            }
        },500);
        return super.onStartCommand(intent, flags, startId);
    }

    @NeedPermission(value = Manifest.permission.CAMERA,requestCode = 1)
    public void requestCamera(){
        Toast.makeText(PermissionService.this,"相机权限请求成功",Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied
    public void  refusedCallBack(DenyBean denyBean){
        Toast.makeText(PermissionService.this,"相机权限已被禁止",Toast.LENGTH_SHORT).show();
        SettingUtil.go2Setting(PermissionService.this);
    }

    @PermissionCanceled
    public void cancleCallBack(CancelBean cancelBean){
        Toast.makeText(PermissionService.this,"相机权限已被取消",Toast.LENGTH_SHORT).show();
    }
}
