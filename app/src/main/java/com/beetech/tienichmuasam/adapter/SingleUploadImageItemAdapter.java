package com.beetech.tienichmuasam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.databinding.ItemImageUploadingBinding;
import com.beetech.tienichmuasam.entity.chat.ImageItem;
import com.beetech.tienichmuasam.ui.GlideApp;

import java.util.HashMap;
import java.util.Map;

public class SingleUploadImageItemAdapter extends RecyclerView.Adapter<SingleUploadImageItemAdapter.ImageItemViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private Map<String, ImageItem> mapImageItems;
    private int currentImageItemPosition = -1;
    private boolean isError = false;
    private OnItemClickListener onItemClickListener;

    public SingleUploadImageItemAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.mapImageItems = new HashMap<>(1);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ImageItem getItem(int position) {
        return mapImageItems.get(position + "");
    }

    public void refreshImageItems(Map<String, ImageItem> mapImageItems) {
        this.mapImageItems.clear();
        this.mapImageItems.putAll(mapImageItems);
        notifyDataSetChanged();
    }

    public void updateImageItem(int position, String url) {
        if (url != null) {
            ImageItem imageItem = mapImageItems.get(position + "");
            if (imageItem != null) {
                imageItem.setUrl(url);
                notifyItemChanged(position);
            }
        }
    }

    public void showError() {
        this.isError = true;
        notifyItemChanged(currentImageItemPosition);
    }

    public void uploadNextImage() {
        int currentPosition = currentImageItemPosition;
        currentImageItemPosition++;
        notifyItemRangeChanged(currentPosition, 2);
    }

    @Override
    public ImageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_image_uploading, parent, false);
        ItemImageUploadingBinding binding = DataBindingUtil.bind(itemView);
        ImageItemViewHolder imageItemViewHolder = new ImageItemViewHolder(binding);
        return imageItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ImageItemViewHolder holder, int position) {
        ImageItem imageItem = mapImageItems.get(position + "");
        if (imageItem != null) {
            holder.bindData(imageItem);
        }
    }

    @Override
    public int getItemCount() {
        return mapImageItems.size();
    }

    class ImageItemViewHolder extends RecyclerView.ViewHolder {
        private ItemImageUploadingBinding bind;

        ImageItemViewHolder(ItemImageUploadingBinding bind) {
            super(bind.getRoot());
            this.bind = bind;
            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(SingleUploadImageItemAdapter.this, getAdapterPosition());
                }
            });
        }

        public void bindData(ImageItem imageItem) {
            if (imageItem.getUrl() == null) {//image not uploaded
                GlideApp.with(context)
                        .load(imageItem.getUri())
                        .placeholder(R.drawable.image_placeholder)
                        .into(bind.imgView);

                if (currentImageItemPosition == getAdapterPosition()) {
                    if (isError) {
                        bind.progressBar.setVisibility(View.GONE);
                        bind.imgError.setVisibility(View.VISIBLE);
                    } else {
                        bind.progressBar.setVisibility(View.VISIBLE);
                        bind.imgError.setVisibility(View.GONE);
                    }
                }
                bind.imgView.setColorFilter(context.getResources().getColor(R.color.transparency_black));
            } else {//image uploaded
                if (imageItem.getUri() != null) {
                    GlideApp.with(context)
                            .load(imageItem.getUri())
                            .placeholder(R.drawable.image_placeholder)
                            .into(bind.imgView);
                } else {
                    GlideApp.with(context)
                            .load(imageItem.getUrl())
                            .placeholder(R.drawable.image_placeholder)
                            .into(bind.imgView);
                }
                bind.progressBar.setVisibility(View.GONE);
                bind.imgError.setVisibility(View.GONE);
                bind.imgView.clearColorFilter();
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(SingleUploadImageItemAdapter adapter, int position);
    }
}
