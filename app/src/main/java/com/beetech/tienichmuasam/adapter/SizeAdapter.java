package com.beetech.tienichmuasam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.databinding.ItemSizeBinding;
import com.beetech.tienichmuasam.entity.response.SizeResponse;

import java.util.ArrayList;
import java.util.List;


public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.SizeViewHolder> {
    private Context context;
    private List<SizeResponse> sizeResponses;
    private OnClickSizeListener listener;

    public void setListener(OnClickSizeListener listener) {
        this.listener = listener;
    }

    public SizeAdapter(Context context, List<SizeResponse> sizeResponses) {
        this.context = context;
        this.sizeResponses=(sizeResponses);
    }

    @NonNull
    @Override
    public SizeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSizeBinding itemSizeBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.item_size, parent, false);
        return new SizeViewHolder(itemSizeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull SizeViewHolder holder, int position) {
        holder.bindData(sizeResponses.get(position));
    }

    @Override
    public int getItemCount() {
        return sizeResponses.size();
    }

    public class SizeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemSizeBinding binding;

        public SizeViewHolder(ItemSizeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        public void bindData(SizeResponse sizeResponse) {
            binding.setSize(sizeResponse);
            if (sizeResponse.isSelected()) {
                binding.tvSize.setBackgroundResource(R.drawable.bg_size_selected);
                binding.tvSize.setTextColor(ContextCompat.getColor(context, R.color.white));
            } else {
                binding.tvSize.setBackgroundResource(R.drawable.bg_size_unselected);
                binding.tvSize.setTextColor(ContextCompat.getColor(context, R.color.black));
            }
        }

        @Override
        public void onClick(View view) {
            if(listener!=null){
                listener.onClickSize(getAdapterPosition());
            }
        }
    }

    public interface OnClickSizeListener {
        void onClickSize(int position);
    }
}
