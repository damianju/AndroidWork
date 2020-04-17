package com.lec.android.a008_practice;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class InformationAdapter {

    List<Information> information = new ArrayList<Information>();


    static class ViewHoler extends RecyclerView.ViewHolder{
        TextView tvName, tvAge, tvAddress;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAge = itemView.findViewById(R.id.tvAge);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        } // end 생성자

        public void setItem(Information item){
            tvName.setText(item.getName());
            tvAge.setText(item.getAge());
            tvAddress.setText(item.getAddress());
        }

    } // end  ViewHolder

} // end InformationAdapter class
