package com.superchen.demo.fragment.libTest1;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.superc.lib.ui.fragment.SFragment;
import com.superc.lib.widget.recyclerview.RRecyclerView;
import com.superchen.demo.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.OnClick;

public class LibTestFragment1 extends SFragment<ILibContract1.ILibContractPresenter1> implements ILibContract1.ILibContractView1 {

    @BindViews({R.id.lib_test_et_name, R.id.lib_test_et_pd})
    List<TextInputEditText> etLists;
    @BindView(R.id.test_r_recyclerview)
    RRecyclerView recyclerView;

    public LibTestFragment1() {
    }

    public static LibTestFragment1 newInstance() {
        LibTestFragment1 fragment = new LibTestFragment1();
        return fragment;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_lib_test_fragment1;
    }

    @Override
    public void popOnResume(View view) {
        Log.i("msgg", "LibTestFragment1 popOnResume");
    }

    @Override
    public void popOnPause(View view) {
        Log.i("msgg", "LibTestFragment1 popOnPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("msgg", "LibTestFragment1 onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("msgg", "LibTestFragment1 onPause");
    }

    @Override
    protected void initViews(View view) {
        createPresenter(new LibPresenter1(this));
//        recyclerView.setPullRefreshEnable(false);
//        recyclerView.setLoadMoreEnable(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        String[] d = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        List<String> mDatas = new ArrayList<>();
        List<Integer> mImgDatas = new ArrayList<>();
        for (int i = 0; i < d.length; i++) {
            if (i % 2 == 0) {
                mDatas.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502082443672&di=5550eaf3f60e9f9203e9c8fea2db12e7&imgtype=0&src=http%3A%2F%2Fimg17.3lian.com%2Fd%2Ffile%2F201702%2F22%2F1005a2e0825ffe290b3f697404ee8038.jpg");
            } else {
                mDatas.add(d[i]);
            }
        }
        recyclerView.setAdapter(new TestRRecyclerViewAdapter1(getContext(), mDatas));
        recyclerView.setNoMoreData(true);
    }

    @Override
    public void setPresenter(ILibContract1.ILibContractPresenter1 presenter) {

    }

    @Override
    public void showLoadingDialog(String message) {
        super.showLoadingDialog(message);
    }

    @Override
    public void hideLoadingDialog() {
        super.hideLoadingDialog();
    }

    @Override
    public void showError() {

    }

    @Override
    public void showEmptyView() {
    }

    @Override
    public void showToastShort(String msg) {
        super.showToastShort(msg);
    }

    @Override
    public void showToastLong(String msg) {
        super.showToastLong(msg);
    }

    @Override
    public void showLog(String msg) {
        super.showLog(msg);
    }

    @Override
    public void success() {
    }

    @Override
    public Context getContext() {
        return super.getContext();
    }

    @OnClick({R.id.lib_test_btn_login})
    void onClick(View v) {
        switch (v.getId()) {
            case R.id.lib_test_btn_login:
                presenter.getPublicKey();
                break;
        }
    }

}
