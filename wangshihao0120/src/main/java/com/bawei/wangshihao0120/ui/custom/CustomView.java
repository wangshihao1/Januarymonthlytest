package com.bawei.wangshihao0120.ui.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.bawei.wangshihao0120.R;

public class CustomView extends LinearLayout implements View.OnClickListener {
    private Button add;
    private Button del;
    private EditText number;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View rootView = LayoutInflater.from(context).inflate(R.layout.custom_view,this);
         add = rootView.findViewById(R.id.btn_add);
         del = rootView.findViewById(R.id.btn_del);
         number = rootView.findViewById(R.id.et_number);
         add.setOnClickListener(this);
         del.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         String numstr = number.getText().toString();
         int num = Integer.parseInt(numstr);
         switch (v.getId()){
             case R.id.btn_del:
                num = num -1;
                 if (num<=0){
                     num = 0;
                     number.setText(String.valueOf(num));
                 }
                 customViewListener.del(num);
                 break;
             case R.id.btn_add:
                 num = num + 1;
                 number.setText(String.valueOf(num));
                 customViewListener.add(num);
                 break;
         }
    }

    private OnCustomViewListener customViewListener;
    public interface OnCustomViewListener{
        void del(int num);
        void add(int num);
    }

    public void setCustomViewListener(OnCustomViewListener customViewListener) {
        this.customViewListener = customViewListener;
    }
}
