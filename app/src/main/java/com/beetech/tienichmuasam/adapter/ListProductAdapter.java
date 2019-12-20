package com.beetech.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.tienichmuasam.databinding.ItemListProductBinding;
import com.beetech.tienichmuasam.entity.ListProductResponse;

public class ListProductAdapter extends EndlessLoadingRecyclerViewAdapter<ItemListProductBinding> {

    public ListProductAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemListProductBinding binding, ViewGroup parent) {
        return new ListProductViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        ListProductViewHolder searchViewHolder = (ListProductViewHolder) holder;
        searchViewHolder.bind(getItem(position, ListProductResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_search;
    }

    public class ListProductViewHolder extends NormalViewHolder<ListProductResponse> {
        private ItemListProductBinding binding;

        ListProductViewHolder(ItemListProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(ListProductResponse data) {
            binding.setProduct(data);
        }
    }
}