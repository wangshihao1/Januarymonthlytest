package com.bawei.wangshihao0120.di.model;

import com.bawei.wangshihao0120.data.api.Apis;
import com.bawei.wangshihao0120.di.contract.IContract;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

public class IShowModelImp implements IContract.IShowModel {

    @Override
    public void requestData(final OnCallBack callBack) {
        OkGo.<String>post(Apis.SHOW_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String result = response.body().toString();
                  callBack.onCallBakc(result);
            }
        });
    }
}
