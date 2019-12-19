package com.beetech.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.tienichmuasam.databinding.ItemSearchBinding;
import com.beetech.tienichmuasam.entity.SearchResponse;

public class SearchAdapter extends EndlessLoadingRecyclerViewAdapter<ItemSearchBinding> {

    public SearchAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemSearchBinding binding, ViewGroup parent) {
        return new SearchViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        SearchViewHolder searchViewHolder = (SearchViewHolder) holder;
        searchViewHolder.bind(getItem(position, SearchResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_search;
    }

    public class SearchViewHolder extends NormalViewHolder<SearchResponse> {
        private ItemSearchBinding binding;

        SearchViewHolder(ItemSearchBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @Override
        public void bind(SearchResponse data) {
            binding.setSearch(data);
        }
    }
}
