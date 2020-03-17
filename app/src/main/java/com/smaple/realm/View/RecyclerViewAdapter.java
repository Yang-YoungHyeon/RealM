package com.smaple.realm.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smaple.realm.R;
import com.smaple.realm.ViewModel.ListItemViewModel;

import java.util.Collections;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private List<ListItemViewModel> items = Collections.emptyList();

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /* Prepare to use LayoutInflater object */
        Context mContext = parent.getContext() ;
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        /* Insert XML(R.layout....) to LayoutInflater Object */
        View mView = mInflater.inflate(R.layout.list_item, parent, false) ;

        /* Create ViewHolder Object (need View) */
        RecyclerViewHolder mViewHoler = new RecyclerViewHolder(mView);

        return mViewHoler;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        /* UI Update */
        String name = items.get(position).getmName();
        int age = items.get(position).getmAge();
        byte[] image = items.get(position).getmImage();

        holder.tvName.setText(name);
        holder.tvAge.setText(age+"");
        holder.ivImage.setImageBitmap(byteArrayToBitmap(image));

        /* Click Listener */
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = position;
                if (pos != RecyclerView.NO_POSITION) {
                    if (mListener != null) {
                        mListener.onItemClick(v, pos) ;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setData(List<ListItemViewModel> model){
        if(model == null) {
            model = Collections.emptyList();
        }
        this.items = model;
        notifyDataSetChanged();
    }

    public ListItemViewModel getItem(int position) {
        return items.get(position);
    }

    public static Bitmap byteArrayToBitmap(byte[] byteArray) {
        Bitmap bitmap = BitmapFactory.decodeByteArray( byteArray, 0, byteArray.length ) ;
        return bitmap ;
    }


    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }

    private OnItemClickListener mListener = null ;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener ;
    }
}