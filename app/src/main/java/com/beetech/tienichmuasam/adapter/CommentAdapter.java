package com.beetech.tienichmuasam.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.tienichmuasam.databinding.ItemUserCommentBinding;
import com.beetech.tienichmuasam.entity.response.CommentResponse;
import com.beetech.tienichmuasam.ui.GlideApp;
import com.bumptech.glide.Glide;

public class CommentAdapter extends EndlessLoadingRecyclerViewAdapter<ItemUserCommentBinding> {


    public CommentAdapter(Context context) {
        super(context, false);
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ItemUserCommentBinding binding, ViewGroup parent) {
        return new CommentViewHolder(binding);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {
        CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
        commentViewHolder.bind(getItem(position, CommentResponse.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_user_comment;
    }

    public class CommentViewHolder extends NormalViewHolder<CommentResponse> {
        private ItemUserCommentBinding itemUserCommentBinding;

        public CommentViewHolder(ItemUserCommentBinding itemUserCommentBinding) {
            super(itemUserCommentBinding.getRoot());
            this.itemUserCommentBinding = itemUserCommentBinding;
        }

        @Override
        public void bind(CommentResponse data) {
            itemUserCommentBinding.setComment(data);
            GlideApp.with(getContext())
                    .load(data.getUserAvatar())
                    .placeholder(R.drawable.image_placeholder)
                    .error(R.drawable.image_placeholder)
                    .circleCrop()
                    .into(itemUserCommentBinding.imgAvatar);
        }
    }
}
