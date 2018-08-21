package com.example.kproductions.dailypsalm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<ListData>{
    private Context context;
    private int imageid;
    private ArrayList<ListData> items;
    public ListViewAdapter(Context context, int resorceID, ArrayList<ListData> items){
        super(context,resorceID,items);

        this.context = context;
        this.imageid = resorceID;
        this.items = items;

    }

    private class ViewHolder{
        TextView psalmName;
        ImageView imageView;
    }

    public View getView(int position, View view, ViewGroup parent){
        ViewHolder holder = null;

        if(view == null){
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.listview_info,null);

            holder = new ViewHolder();
            holder.psalmName = view.findViewById(R.id.textval);
            holder.imageView = view.findViewById(R.id.icon);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        holder.psalmName.setText(this.items.get(position).getTexts());
        holder.imageView.setImageResource(this.items.get(position).getImgID());
        Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();

        return  view;
    }
}
