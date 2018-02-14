package com.existentialponytomas.getappprospero.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.existentialponytomas.getappprospero.R;
import com.existentialponytomas.getappprospero.model.User;

import java.util.List;

/**
 * Created by Nessa on 01.04.2017.
 */

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.MyViewHolder> {

    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, sum;
        public ImageView avatar;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            sum = (TextView) view.findViewById(R.id.sum);
            avatar = (ImageView) view.findViewById(R.id.avatar);
        }



    }



    public RatingAdapter(List<User> userList) {
        //Заранее отсортированы?
        this.userList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rating_list_row, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        User user = userList.get(position);

         holder.name.setText(user.getName());
         holder.sum.setText(String.valueOf(user.getPoints()));
         holder.avatar.setImageResource(user.getAvatar());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
