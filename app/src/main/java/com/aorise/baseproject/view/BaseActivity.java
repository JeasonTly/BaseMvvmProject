package com.aorise.baseproject.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.aorise.baseproject.base.ActivityManager;
import com.aorise.baseproject.common.LogT;
import com.hjq.toast.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tuliyuan.
 * Date: 2019/6/28.
 */
public abstract class BaseActivity extends AppCompatActivity {
    // 时间间隔
    private static final long EXIT_INTERVAL = 2000L;
    // 如果要监听双击事件则数组长度为2，如果要监听3次连续点击事件则数组长度为3...
    private long[] mHints = new long[2];
    private String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE};
    List<String> mPermissionList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initAdapter();
        initListener();
    }

    protected abstract void initData();

    protected abstract void initAdapter();

    protected abstract void initListener();

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            // 将mHints数组内的所有元素左移一个位置
            System.arraycopy(mHints, 1, mHints, 0, mHints.length - 1);
            // 获得当前系统已经启动的时间
            mHints[mHints.length - 1] = SystemClock.uptimeMillis();
            if ((SystemClock.uptimeMillis() - mHints[0]) > EXIT_INTERVAL) {
                ToastUtils.show("再按一次返回键退出");
            } else {
                finish();
                ActivityManager.getInstance().appExit(getApplicationContext());
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void requestPermission(ArrayList<String> permissionList) {
        if (Build.VERSION.SDK_INT > 23) {
            mPermissionList.clear();//清空没有通过的权限
            for (int i = 0; i < permissionList.size(); i++) {
                if (ActivityCompat.checkSelfPermission(this, permissionList.get(i)) != PackageManager.PERMISSION_GRANTED) {
                    mPermissionList.add(permissionList.get(i));//添加还未授予的权限
                }
            }

            //申请权限
            if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
                ActivityCompat.requestPermissions(this, (String[]) permissionList.toArray(), 2040);
            } else {
                //说明权限都已经通过，可以做你想做的事情去
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 2040:
                // 电话权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LogT.d("权限申请成功:" + permissions.toString());
                }

        }
    }

}
