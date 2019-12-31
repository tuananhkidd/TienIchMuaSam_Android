package com.beetech.tienichmuasam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.databinding.ItemHomeCategoryBinding;
import com.beetech.tienichmuasam.entity.response.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryHomeAdapter extends RecyclerView.Adapter<CategoryHomeAdapter.CategoryHomeViewHolder> {
    private List<CategoryResponse> categoryResponses = new ArrayList<>();
    private OnCategoryClickListener listener;

    public void setListener(OnCategoryClickListener listener) {
        this.listener = listener;
    }

    public void setData(List<CategoryResponse> categoryResponses) {
        this.categoryResponses.clear();
        this.categoryResponses.addAll(categoryResponses);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryHomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHomeCategoryBinding binding = DataBindingUtil.bind(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_category, parent, false));
        return new CategoryHomeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHomeViewHolder holder, int position) {
        holder.bind(categoryResponses.get(position));
    }

    @Override
    public int getItemCount() {
        return categoryResponses.size();
    }

    public class CategoryHomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemHomeCategoryBinding binding;

        public CategoryHomeViewHolder(ItemHomeCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        public void bind(CategoryResponse categoryResponse) {
            binding.setCategory(categoryResponse);
        }

        @Override
        public void onClick(View view) {
            listener.onClickCategory(getAdapterPosition());
        }
    }

    public interface OnCategoryClickListener{
        void onClickCategory(int position);
    }
}
