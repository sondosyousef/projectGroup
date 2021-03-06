package com.example.projectgroup;

import android.content.Context;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
;


import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    Context context;

    List<GetDataAdapter> getDataAdapter;

    ImageLoader imageLoader1;
    GetDataAdapter getDataAdapter1;

    public RecycleViewAdapter(List<GetDataAdapter> getDataAdapter, Context context){

        super();
        this.getDataAdapter = getDataAdapter;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            GetDataAdapter getDataAdapter1 =  getDataAdapter.get(position);

            imageLoader1 = ServerImageParseAdapter.getInstance(context).getImageLoader();


            imageLoader1.get(getDataAdapter1.getImageServerUrl(),
                    ImageLoader.getImageListener(
                            holder.networkImageView,//Server Image
                            R.mipmap.ic_launcher,//Before loading server image the default showing image.
                            android.R.drawable.ic_dialog_alert //Error image if requested image dose not found on server.
                    )
            );

            holder.networkImageView.setImageUrl(getDataAdapter1.getImageServerUrl(), imageLoader1);

            holder.ImageTitleNameView.setText(getDataAdapter1.getImageTitleName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent i = new Intent(v.getContext(),description.class);
                    i.putExtra("image_name", getDataAdapter1.ImageTitleName);
                    i.putExtra("image_Url", getDataAdapter1.ImageServerUrl);
                    i.putExtra("image_dec", getDataAdapter1.Description);
                    i.putExtra("image1", getDataAdapter1.img1);
                    i.putExtra("image2", getDataAdapter1.img2);
                    i.putExtra("text1", getDataAdapter1.text1);
                    i.putExtra("text2", getDataAdapter1.text2);
                    v.getContext().startActivity(i);

                }
            });



        }



    @Override
    public int getItemCount() {

        return getDataAdapter.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ImageTitleNameView;
        public NetworkImageView networkImageView ;


        public TextView Desc;

        public ViewHolder(View itemView) {

            super(itemView);


            ImageTitleNameView = (TextView) itemView.findViewById(R.id.textView_item) ;
            networkImageView = (NetworkImageView) itemView.findViewById(R.id.VollyNetworkImageView1) ;



        }
    }
}