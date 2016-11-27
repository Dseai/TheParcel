package com.syn.theparcel.listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.syn.theparcel.R;
import com.syn.theparcel.enty.Express;

import java.util.ArrayList;

/**
 * Created by 孙亚楠 on 2016/11/23.
 */

public class HistoryAdapter extends BaseAdapter {
    LayoutInflater layoutInflater;
    private ArrayList<Express>  arrayList;

    public HistoryAdapter(LayoutInflater layoutInflater, ArrayList<Express> arrayList) {
        this.arrayList=arrayList;
        this.layoutInflater=layoutInflater;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=new ViewHolder();
        if(view==null){
            view=layoutInflater.inflate(R.layout.history_listitem,null);
            viewHolder.expressName=(TextView)view.findViewById(R.id.expressName);
            viewHolder.expressNumber=(TextView)view.findViewById(R.id.expressNumber);
            view.setTag(viewHolder);
        }
        viewHolder=(ViewHolder)view.getTag();
        viewHolder.expressName.setText(arrayList.get(i).getExpressName());
        viewHolder.expressNumber.setText(arrayList.get(i).getExpressNumber());
        return view;
    }
}
class ViewHolder{
    TextView expressName;
    TextView expressNumber;
}
