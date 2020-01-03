package com.beetech.tienichmuasam.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.databinding.ItemSuggestBinding;

import java.util.ArrayList;
import java.util.List;

public class SuggestAdapter extends RecyclerView.Adapter<SuggestAdapter.SuggestViewHolder> {

    private List<String> suggests = new ArrayList<>();

    private OnSuggestClickListener listener;

    public SuggestAdapter(OnSuggestClickListener listener) {
        this.suggests = new ArrayList<>();
        suggests.add("Giày Box And Cox");
        suggests.add("Rượu vang Pháp");
        suggests.add("Đồ lót chính hiệu");
        suggests.add("Thực phẩm tươi, rẻ");
        suggests.add("Dép thời trang");

        this.listener = listener;
    }

    @NonNull
    @Override
    public SuggestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSuggestBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_suggest, parent, false);
        return new SuggestViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestViewHolder holder, int position) {
        holder.bind(suggests.get(position));
    }

    @Override
    public int getItemCount() {
        return suggests.size();
    }

    public class SuggestViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemSuggestBinding binding;

        public SuggestViewHolder(ItemSuggestBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        public void bind(String suggest) {
            binding.tvSuggest.setText(suggest);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onClickSuggest(suggests.get(getAdapterPosition()));
            }
        }
    }

    public interface OnSuggestClickListener {
        void onClickSuggest(String suggest);
    }
}
