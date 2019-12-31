package com.beetech.tienichmuasam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.BuildConfig;
import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.databinding.ItemColorBinding;
import com.beetech.tienichmuasam.entity.response.ColorResponse;
import com.beetech.tienichmuasam.ui.GlideApp;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorViewHolder> {
    private Context context;
    private List<ColorResponse> colorResponses;
    private OnClickColorListener listener;

    public void setListener(OnClickColorListener listener) {
        this.listener = listener;
    }

    public ColorAdapter(Context context, List<ColorResponse> colorResponses) {
        this.context = context;
        this.colorResponses = (colorResponses);
    }

    @NonNull
    @Override
    public ColorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemColorBinding itemSizeBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_color, parent, false);
        return new ColorViewHolder(itemSizeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ColorViewHolder holder, int position) {
        holder.bindData(colorResponses.get(position));
    }

    @Override
    public int getItemCount() {
        return colorResponses.size();
    }

    public class ColorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemColorBinding binding;

        public ColorViewHolder(ItemColorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        public void bindData(ColorResponse sizeResponse) {
            GlideApp.with(context)
                    .load(BuildConfig.IMAGE_URL + sizeResponse.getImagePath())
                    .into(binding.ivLogo);
            if (sizeResponse.getIsSelected()) {
                binding.container.setBackgroundResource(R.drawable.bg_color_selected);
            } else {
                binding.container.setBackgroundResource(R.drawable.bg_color_unselecter);
            }
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClickColor(getAdapterPosition());
            }
        }
    }

    public interface OnClickColorListener {
        void onClickColor(int position);
    }
}