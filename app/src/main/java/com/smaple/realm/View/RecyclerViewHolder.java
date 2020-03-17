package com.smaple.realm.View;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smaple.realm.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView tvName;
    TextView tvAge;
    ImageView ivImage;
    Button btnDetail;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tvName);
        tvAge = itemView.findViewById(R.id.tvAge);
        ivImage = itemView.findViewById(R.id.ivImage);
        btnDetail = itemView.findViewById(R.id.btnDetails);
    }
}
