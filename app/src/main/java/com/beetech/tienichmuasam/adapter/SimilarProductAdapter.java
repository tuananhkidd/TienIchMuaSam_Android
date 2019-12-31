package com.beetech.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.tienichmuasam.databinding.ItemSimilarProductBinding;
import com.beetech.tienichmuasam.entity.response.ListProductResponse;

public class SimilarProductAdapter extends EndlessLoadingRecyclerViewAdapter<ItemSimilarProductBinding> {

    public SimilarProductAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemSimilarProductBinding binding, ViewGroup parent) {
        return new SimilarProductAdapter.ListProductViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        ListProductViewHolder searchViewHolder = (ListProductViewHolder) holder;
        searchViewHolder.bind(getItem(position, ListProductResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_similar_product;
    }

    public class ListProductViewHolder extends NormalViewHolder<ListProductResponse> {
        private ItemSimilarProductBinding binding;

        ListProductViewHolder(ItemSimilarProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(ListProductResponse data) {
            binding.setProduct(data);
        }
    }
}
