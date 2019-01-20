package com.bawei.wangshihao0120.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.bawei.wangshihao0120.R;
import com.bawei.wangshihao0120.data.bean.ShoppingBean;
import com.bawei.wangshihao0120.di.contract.IContract;
import com.bawei.wangshihao0120.di.presenter.IShowPresenterImp;
import com.bawei.wangshihao0120.ui.adapter.ShowDataAdapter;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentHome extends Fragment implements IContract.IShowView,View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.cycle)
    RecyclerView cycle;
    @BindView(R.id.ck_box)
    CheckBox ckBox;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.btn_js)
    Button btnJs;
    Unbinder unbinder;
    private IShowPresenterImp presenterImp;
    private ShowDataAdapter adapter;
    private List<ShoppingBean.DataBean> beanList;
    private Context mContext;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenterImp = new IShowPresenterImp();
        presenterImp.atteachView(this);
        presenterImp.responseData();
        mContext = getActivity();
        return view;
    }

    @Override
    public void showData(String datas) {
        Gson gson = new Gson();
        ShoppingBean shoppingBean = gson.fromJson(datas, ShoppingBean.class);
        beanList = shoppingBean.getData();
        adapter = new ShowDataAdapter(R.layout.item_title,beanList);
        final LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        cycle.setLayoutManager(manager);
        cycle.setAdapter(adapter);
        ckBox.setOnCheckedChangeListener(null);
        ckBox.setOnClickListener(this);
        adapter.setShowDataListener(new ShowDataAdapter.OnShowDataListener() {
            @Override
            public void onCallBack() {
                boolean result = true;
                for (int i = 0; i <beanList.size() ; i++) {
                     boolean bigCheck = beanList.get(i).getBigCheck();
                     result = result&bigCheck;
                    for (int j = 0; j <beanList.get(i).getList().size(); j++) {
                         boolean goodsCheck = beanList.get(i).getList().get(j).getGoodsCheck();
                         result = result&goodsCheck;
                    }
                }
                ckBox.setChecked(result);
                totalCountPrice();
            }
        });
    }

    private void totalCountPrice() {
        double totalPrice = 0;
        for (int i = 0; i <beanList.size() ; i++) {
            for (int j = 0; j <beanList.get(i).getList().size() ; j++) {
                 int number = beanList.get(i).getList().get(j).getInitNumber();
                 double price =beanList.get(i).getList().get(j).getPrice();
                 totalPrice = totalPrice+(number*price);
            }
        }
        tvPrice.setText("总价："+totalPrice);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenterImp.deteachView(this);
    }

    @Override
    public void onClick(View v) {
        for (int i = 0; i <beanList.size() ; i++) {
            for (int j = 0; j <beanList.get(i).getList().size(); j++) {
                   beanList.get(i).getList().get(j).setGoodsCheck(ckBox.isChecked());
            }
        }
        adapter.notifyDataSetChanged();
        totalCountPrice();
    }
}
