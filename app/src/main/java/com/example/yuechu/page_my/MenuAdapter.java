package com.example.yuechu.page_my;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuechu.R;

import java.util.List;

public class MenuAdapter extends ArrayAdapter<Material> {
    private int resourceId; // 添加一个成员
    public MenuAdapter(Context context, int resource, List<Material> objects) {
        super(context, resource, objects);  //3 个参数: 上下文、ListView 子项布局id 、数据
        resourceId=resource; // 添加语句
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Material material=getItem(position);; // 获取当前选中的Fruit实例，将其内容填充到各个位置
        //填充ListView 布局
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView food_name = (TextView) view.findViewById(R.id.food_name);
        food_name.setText(material.getM_name());
        ImageView food_image = (ImageView) view.findViewById(R.id.food_image);
        food_image.setImageURI(Uri.parse(material.getM_num()));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(view, material, position);
                }
            }
        });

        return view;
    }




    private OnItemClickListener onItemClickListener = null;
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public interface OnItemClickListener {
        public void onItemClick(View view, Material material, int position);
    }
}
