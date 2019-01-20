package com.bawei.wangshihao0120.di.presenter;

import com.bawei.wangshihao0120.di.contract.IContract;
import com.bawei.wangshihao0120.di.model.IShowModelImp;

import java.lang.ref.SoftReference;

public class IShowPresenterImp implements IContract.IPresenter<IContract.IShowView> {

     private IContract.IShowModel showModel;
     private IContract.IShowView showView;
     SoftReference<IContract.IShowView> reference;

    @Override
    public void atteachView(IContract.IShowView iShowView) {
           this.showView = iShowView;
           reference = new SoftReference<>(showView);
           showModel = new IShowModelImp();
    }

    @Override
    public void deteachView(IContract.IShowView iShowView) {
           reference.clear();
    }

    @Override
    public void responseData() {
          showModel.requestData(new IContract.IShowModel.OnCallBack() {
              @Override
              public void onCallBakc(String data) {
                   showView.showData(data);
              }
          });
    }
}
