package com.bawei.wangshihao0120.di.contract;

public interface IContract {

    public interface IShowView{

        void showData(String datas);
    }

    public interface IPresenter<IShowView>{

        void atteachView(IShowView showView);
        void deteachView(IShowView showView);

        void responseData();
    }

    public interface IShowModel{

        public interface OnCallBack{

            void onCallBakc(String data);
        }

        void requestData(OnCallBack callBack);
    }
}
