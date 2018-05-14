package com.example.chen.myanimator.fragment;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.chen.myanimator.R;
import com.ninetripods.aopermission.permissionlib.annotation.NeedPermission;
import com.ninetripods.aopermission.permissionlib.annotation.PermissionCanceled;
import com.ninetripods.aopermission.permissionlib.annotation.PermissionDenied;
import com.ninetripods.aopermission.permissionlib.bean.CancelBean;
import com.ninetripods.aopermission.permissionlib.bean.DenyBean;

/**
 * Created by chen on 2018/4/18.
 * 添加的fragment
 */

public class PermissionFragment extends Fragment {
    private View view;
    private Button btn_permission;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_permission,container,false);
        btn_permission = view.findViewById(R.id.btn_frag_permission);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_permission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callPhone();
            }
        });
    }

    @NeedPermission(value = {Manifest.permission.CALL_PHONE},requestCode = 2)
    public void callPhone(){
        Toast.makeText(getActivity(),"电话权限请求成功",Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied
    public void callRefused(DenyBean denyBean){
        Toast.makeText(getActivity(),"电话权限请求被禁止",Toast.LENGTH_SHORT).show();
    }

    @PermissionCanceled
    public void callCancel(CancelBean cancelBean){
        Toast.makeText(getActivity(),"电话权限请求被取消",Toast.LENGTH_SHORT).show();
    }
}
