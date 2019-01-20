package com.bawei.wangshihao0120.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.bawei.wangshihao0120.R;
import com.bawei.wangshihao0120.data.bean.ShoppingBean;
import com.bawei.wangshihao0120.ui.custom.CustomView;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class GoodsAdapter extends BaseQuickAdapter<ShoppingBean.DataBean.ListBean,BaseViewHolder> {


    public GoodsAdapter(int layoutResId, @Nullable List<ShoppingBean.DataBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoppingBean.DataBean.ListBean item) {
                  helper.setText(R.id.tv_title,item.getTitle());
                  final CheckBox sml_check = helper.getView(R.id.sml_check);
                  ImageView img = helper.getView(R.id.iv_icon);
                  helper.setText(R.id.tv_price,"￥"+item.getPrice());
                  String imagestr = item.getImages();
                  String[] sp = imagestr.split("\\|");
                  Glide.with(mContext).load(sp[0]).into(img);
                  sml_check.setOnCheckedChangeListener(null);
                  sml_check.setChecked(item.getGoodsCheck());
                  sml_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                      @Override
                      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            item.setGoodsCheck(isChecked);
                            goodsAdapterListener.onCallBack();

                      }
                  });
              CustomView customView = helper.getView(R.id.custom_view);
              customView.setCustomViewListener(new CustomView.OnCustomViewListener() {
                  @Override
                  public void del(int num) {
                      item.setInitNumber(num);
                      goodsAdapterListener.onCallBack();
                  }

                  @Override
                  public void add(int num) {
                      item.setInitNumber(num);
                      goodsAdapterListener.onCallBack();
                  }
              });
    }

    private OnGoodsAdapterListener goodsAdapterListener;
    public interface OnGoodsAdapterListener{

        void onCallBack();
    }

    public void setGoodsAdapterListener(OnGoodsAdapterListener goodsAdapterListener) {
        this.goodsAdapterListener = goodsAdapterListener;
    }
}
