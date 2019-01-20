package com.bawei.wangshihao0120.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.bawei.wangshihao0120.R;
import com.bawei.wangshihao0120.data.bean.ShoppingBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ShowDataAdapter extends BaseQuickAdapter<ShoppingBean.DataBean,BaseViewHolder> {


    public ShowDataAdapter(int layoutResId, @Nullable List<ShoppingBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingBean.DataBean item) {
             helper.setText(R.id.tv_text,item.getSellerName());
             RecyclerView rootView = helper.getView(R.id.goods_view);
             final CheckBox goods_check = helper.getView(R.id.ck_check);
             goods_check.setOnCheckedChangeListener(null);
             goods_check.setChecked(item.getBigCheck());
             final List<ShoppingBean.DataBean.ListBean> beans = item.getList();
             final GoodsAdapter goodsAdapter = new GoodsAdapter(R.layout.item_good,beans);
        final LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
             rootView.setLayoutManager(manager);
             rootView.setAdapter(goodsAdapter);
             goodsAdapter.setGoodsAdapterListener(new GoodsAdapter.OnGoodsAdapterListener() {
                 @Override
                 public void onCallBack() {
                     boolean result = true;
                     for (int i = 0; i <beans.size() ; i++) {
                         boolean goodsCheck = item.getList().get(i).getGoodsCheck();
                         result = result&goodsCheck;
                     }
                     item.setBigCheck(result);
                     goodsAdapter.notifyDataSetChanged();
                 }
             });
             goods_check.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     for (int i = 0; i <item.getList().size() ; i++) {
                         item.getList().get(i).setGoodsCheck(goods_check.isChecked());
                     }
                     item.setBigCheck(goods_check.isChecked());
                     notifyDataSetChanged();
                     showDataListener.onCallBack();
                 }
             });

    }

    private OnShowDataListener showDataListener;

    public interface OnShowDataListener{
          void onCallBack();
    }

    public void setShowDataListener(OnShowDataListener showDataListener) {
        this.showDataListener = showDataListener;
    }
}
