package com.example.ricky.geoquiz.activities.materialtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ricky.geoquiz.R;

import java.util.Collections;
import java.util.List;

/** pussy!
 * Created by  on 31/12/2014.
 */
public class VivzAdapter extends RecyclerView.Adapter <VivzAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();
    private Context context;

    public VivzAdapter(Context context, List<Information> data){
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_drawer_custom_cards, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            //title.setOnClickListener(this);
        }


    }


}
