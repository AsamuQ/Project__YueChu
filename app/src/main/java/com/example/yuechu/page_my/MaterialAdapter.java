package com.example.yuechu.page_my;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yuechu.R;

import java.util.List;

public class MaterialAdapter extends ArrayAdapter<Material> {
    private int resourceId; // 添加一个成员
    public MaterialAdapter(Context context, int resource, List<Material> objects) {
        super(context, resource, objects);  //3 个参数: 上下文、ListView 子项布局id 、数据
        resourceId=resource; // 添加语句
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        Material material = getItem(position); // 获取当前选中的Fruit实例，将其内容填充到各个位置
        //填充ListView 布局
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
        TextView mname = (TextView) view.findViewById(R.id.name_item);
        TextView mnum = (TextView) view.findViewById(R.id.num_item);
        mname.setText(material.getM_name());
        mnum.setText(material.getM_num());
        return view;
    }
}
