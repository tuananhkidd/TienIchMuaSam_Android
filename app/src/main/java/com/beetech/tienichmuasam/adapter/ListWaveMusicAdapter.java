package com.beetech.tienichmuasam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;

import java.util.List;

public class ListWaveMusicAdapter extends RecyclerView.Adapter<ListWaveMusicAdapter.WaveMusicHolder> {

    private List<Double> mListData;
    private Context mContext;
    private int heightMax = 0;

    public ListWaveMusicAdapter(List<Double> mListData, Context mContext) {
        this.mListData = mListData;
        this.mContext = mContext;
        heightMax = mContext.getResources().getDimensionPixelSize(R.dimen.margin_40dp);
    }

    @NonNull
    @Override
    public WaveMusicHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_wave_music, viewGroup, false);
        return new WaveMusicHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaveMusicHolder waveMusicHolder, int i) {
        double db = mListData.get(i);

        //min = 0db
        //max = 130db;
        if (db < 0.0 || Double.isNaN(db)) {
            db = 20.0;
        }
        if (db > 130.0) {
            db = 130.0;
        }
        int height = (int) (heightMax * db / 130.0);

        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) waveMusicHolder.viewWaveMusic.getLayoutParams();
        layoutParams.height = height;

    }

    @Override
    public int getItemCount() {
        if (mListData == null) {
            return 0;
        }
        return mListData.size();
    }

    class WaveMusicHolder extends RecyclerView.ViewHolder {

        View viewWaveMusic;

        public WaveMusicHolder(@NonNull View itemView) {
            super(itemView);
            viewWaveMusic = itemView.findViewById(R.id.view_wave);
        }
    }
}
