package com.example.uilibrary.main;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.imcorelibrary.BaseConstants;
import com.example.uilibrary.R;
import com.example.uilibrary.login.loginmvp.LoginActivity;
import com.example.uilibrary.self.SelfInfoFragment;
import com.example.uilibrary.session.SessionFragment;
import com.tencent.imsdk.TIMManager;
import com.tencent.qcloud.uikit.business.session.model.SessionManager;
import com.tencent.qcloud.uikit.common.utils.FileUtil;


public class MainActivity extends Activity implements SessionManager.MessageUnreadWatcher {

    private SessionFragment sessionFragment;
    private SelfInfoFragment selfInfoFragment;
    private TextView sessionBtn, contactBtn, mySelfBtn, mMsgUnread;
    private TextView lastButton;
    private static MainActivity instance;
    public static boolean init = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init = false;
        instance = this;
        if (TextUtils.isEmpty(TIMManager.getInstance().getLoginUser())) {
            MainActivity.login(true);
            return;
        }
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        initView();
    }

    private void initView() {
        if (init)
            return;
        init = true;
        setContentView(R.layout.activity_main);
        sessionBtn = findViewById(R.id.session);
        contactBtn = findViewById(R.id.contact);
        mySelfBtn = findViewById(R.id.mine);
        mMsgUnread = findViewById(R.id.msg_total_unread);
        sessionFragment = new SessionFragment();
        getFragmentManager().beginTransaction().replace(R.id.empty_view, sessionFragment).commitAllowingStateLoss();
        SessionManager.getInstance().addUnreadWatcher(this);
        FileUtil.initPath(); // 从application移入到这里，原因在于首次装上app，需要获取一系列权限，如创建文件夹，图片下载需要指定创建好的文件目录，否则会下载本地失败，聊天页面从而获取不到图片、表情
        lastButton = sessionBtn;
    }


    public void tabClick(View view) {
        Fragment current = null;
        changeMenuState();
        int i = view.getId();
        if (i == R.id.session_btn_group) {
            if (sessionFragment == null)
                sessionFragment = new SessionFragment();
            current = sessionFragment;
            sessionBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.session_selected), null, null);
            lastButton = sessionBtn;

        } else if (i == R.id.myself_btn_group) {
            if (selfInfoFragment == null)
                selfInfoFragment = new SelfInfoFragment();
            current = selfInfoFragment;
            mySelfBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.myself_selected), null, null);
            lastButton = mySelfBtn;

        }
        if (current != null)
            getFragmentManager().beginTransaction().replace(R.id.empty_view, current).commitAllowingStateLoss();


    }

    private void changeMenuState() {
        if (lastButton == null)
            return;
        int i = lastButton.getId();
        if (i == R.id.session) {
            sessionBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.session_normal), null, null);

        } else if (i == R.id.contact) {
            contactBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.contact_normal), null, null);

        } else if (i == R.id.mine) {
            mySelfBtn.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.myself_normal), null, null);

        }
    }


    @Override
    public void updateUnread(int count) {
        if (count > 0)
            mMsgUnread.setVisibility(View.VISIBLE);
        else
            mMsgUnread.setVisibility(View.GONE);
        String unreadStr = "" + count;
        if (count > 100)
            unreadStr = "99+";
        mMsgUnread.setText(unreadStr);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;

    }

    @Override
    public void finish() {
        super.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        instance = null;
    }

    public static void exitApp() {
        if (instance != null)
            instance.finish();
    }

    public static void login(boolean autoLogin) {
        if (instance != null) {
            Intent intent = new Intent(instance, LoginActivity.class);
            intent.putExtra(BaseConstants.AUTO_LOGIN, autoLogin);
            instance.startActivity(intent);
        }
    }
}
