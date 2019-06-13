package com.example.uilibrary.self;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uilibrary.R;
import com.example.uilibrary.main.MainActivity;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.qcloud.uikit.TUIKit;
import com.tencent.qcloud.uikit.business.mine.view.SelfInfoPanel;
import com.tencent.qcloud.uikit.common.BaseFragment;
import com.tencent.qcloud.uikit.common.utils.UIUtils;


public class SelfInfoFragment extends BaseFragment {

    private View mBaseView;
    private SelfInfoPanel infoPanel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mBaseView = inflater.inflate(R.layout.info_fragment_self, container, false);
        initView();
        return mBaseView;
    }

    private void initView() {
        infoPanel = mBaseView.findViewById(R.id.self_info_panel);
        infoPanel.mItemsGroup.setVisibility(View.GONE);
        mBaseView.findViewById(R.id.logout_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TIMManager.getInstance().logout(new TIMCallBack() {
                    @Override
                    public void onError(int code, String desc) {
                        UIUtils.toastLongMessage("logout fail: " + code + "=" + desc);
                    }

                    @Override
                    public void onSuccess() {
                        MainActivity.login(false);
                        TUIKit.unInit();
                        getActivity().finish();
                    }
                });
            }
        });

    }
}
