package com.superc.lib.ui;

import android.content.Context;

import com.superc.lib.S;
import com.superc.lib.http.HttpManager;
import com.superc.lib.presenter.SPresenter;

/**
 * Created by superchen on 2017/5/10.
 */
public interface SView<P extends SPresenter> extends S {

    void setPresenter(P presenter);

    void showLoadingDialog(String loadingReminderText);

    void hideLoadingDialog();

    void showError();

    void showEmptyView();

    void showToastShort(String msg);

    void showToastLong(String msg);

    void showLog(String msg);

    Context getContext();

    <T> HttpManager<T> getHttpManager();

    void destroyAllNetTask();

}
