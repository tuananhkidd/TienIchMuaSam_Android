package com.beetech.tienichmuasam.adapter;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.tienichmuasam.custom.behavior.SpaceItemDecoration;
import com.beetech.tienichmuasam.databinding.ItemFriendImageMessageBinding;
import com.beetech.tienichmuasam.databinding.ItemOppositeTextMessageBinding;
import com.beetech.tienichmuasam.databinding.ItemOppositeTypingBinding;
import com.beetech.tienichmuasam.databinding.ItemOwnedImageMessageBinding;
import com.beetech.tienichmuasam.databinding.ItemOwnedTextMessageBinding;
import com.beetech.tienichmuasam.entity.chat.BaseMessage;
import com.beetech.tienichmuasam.entity.chat.ImageItem;
import com.beetech.tienichmuasam.entity.chat.ImageMessage;
import com.beetech.tienichmuasam.entity.chat.MessageWrapper;
import com.beetech.tienichmuasam.entity.chat.TextMessage;
import com.beetech.tienichmuasam.entity.chat.UserInfo;
import com.beetech.tienichmuasam.ui.GlideApp;
import com.beetech.tienichmuasam.utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class ChatAdapter extends EndlessLoadingRecyclerViewAdapter {
    public static final int VIEW_TYPE_FRIEND_TYPING = -2;

    public static final int VIEW_TYPE_OWNED_TEXT_MESSAGE = 1;
    public static final int VIEW_TYPE_OWNED_IMAGE_MESSAGE = 3;

    public static final int VIEW_TYPE_FRIEND_TEXT_MESSAGE = 2;
    public static final int VIEW_TYPE_FRIEND_IMAGE_MESSAGE = 4;

    private UserInfo ownerInfo;
    private UserInfo adminInfo;
    private boolean isOppositeTypingMessageShowing;
    private RecyclerView.RecycledViewPool viewPool;

    public ChatAdapter(Context context, UserInfo ownerInfo, UserInfo adminInfo) {
        super(context, false);
        this.ownerInfo = ownerInfo;
        this.adminInfo = adminInfo;
        this.viewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    protected RecyclerView.ViewHolder solvedOnCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder result = null;
        switch (viewType) {
            case VIEW_TYPE_FRIEND_TYPING: {
                View itemView = getInflater().inflate(R.layout.item_opposite_typing, parent, false);
                ItemOppositeTypingBinding binding = DataBindingUtil.bind(itemView);
                result = new OppositeTypingViewHolder(binding);
            }
            break;

            //------------------------------------------------------------------------------------//

            case VIEW_TYPE_OWNED_TEXT_MESSAGE: {
                View itemView = getInflater().inflate(R.layout.item_owned_text_message, parent, false);
                OwnedTextMessageViewHolder ownedTextMessageViewHolder = new OwnedTextMessageViewHolder(itemView);
                ownedTextMessageViewHolder.setBinding(DataBindingUtil.bind(itemView));
                result = ownedTextMessageViewHolder;
            }
            break;

            case VIEW_TYPE_OWNED_IMAGE_MESSAGE: {
                View itemView = getInflater().inflate(R.layout.item_owned_image_message, parent, false);
                OwnedImageMessageViewHolder ownedImageMessageViewHolder = new OwnedImageMessageViewHolder(itemView, viewPool, true);
                ownedImageMessageViewHolder.setItemOwnedImageMessageBinding(DataBindingUtil.bind(itemView));
                result = ownedImageMessageViewHolder;
            }
            break;
            //------------------------------------------------------------------------------------//

            case VIEW_TYPE_FRIEND_IMAGE_MESSAGE: {
                View itemView = getInflater().inflate(R.layout.item_friend_image_message, parent, false);
                result = new OppositeImageMessageViewHolder(itemView, DataBindingUtil.bind(itemView), viewPool);
            }
            break;

            case VIEW_TYPE_FRIEND_TEXT_MESSAGE: {
                View itemView = getInflater().inflate(R.layout.item_opposite_text_message, parent, false);
                result = new OppositeTextMessageViewHolder(itemView, DataBindingUtil.bind(itemView));
            }
            break;
            default: {
                result = null;
            }
            break;
        }
        return result;
    }

    public void showFriendTypingMessage(String friendID) {
        if (isOppositeTypingMessageShowing) {
            updateModel(0, friendID, false);
        } else {
            addModel(0, friendID, VIEW_TYPE_FRIEND_TYPING, true);
            isOppositeTypingMessageShowing = true;
        }
    }

    public void hideFriendTypingMessage(String friendID) {
        if (isOppositeTypingMessageShowing) {
            String typingFriendID = (String) getItem(0, String.class);
            if (typingFriendID.equals(friendID)) {
                removeModel(0);
                isOppositeTypingMessageShowing = false;
            }
        }
    }


    @Override
    protected void solvedOnBindViewHolder(RecyclerView.ViewHolder viewHolder, int viewType, int position) {
        switch (viewType) {
            case VIEW_TYPE_FRIEND_TYPING: {
                bindFriendTypingViewHolder((OppositeTypingViewHolder) viewHolder, position);
            }
            break;

            //------------------------------------------------------------------------------------//

            case VIEW_TYPE_OWNED_TEXT_MESSAGE: {
                bindTextMessageViewHolder((OwnedTextMessageViewHolder) viewHolder, position);
            }
            break;

            case VIEW_TYPE_OWNED_IMAGE_MESSAGE: {
                bindImageMessageViewHolder((OwnedImageMessageViewHolder) viewHolder, position);
            }
            break;
            //------------------------------------------------------------------------------------//

            case VIEW_TYPE_FRIEND_TEXT_MESSAGE: {
                bindFriendTextMessageViewHolder((OppositeTextMessageViewHolder) viewHolder, position);
            }
            break;

            case VIEW_TYPE_FRIEND_IMAGE_MESSAGE: {
                bindFriendImageMessageViewHolder((OppositeImageMessageViewHolder) viewHolder, position);
            }
            break;

            default: {
                break;
            }
        }
    }


    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewDataBinding binding, ViewGroup parent) {
        return null;
    }

    @Override
    protected RecyclerView.ViewHolder initNormalViewHolder(ViewGroup parent) {
        return null;
    }

    private MessageWrapper bindMessageViewHolder(BaseMessageViewHolder holder, int position) {
        MessageWrapper messageWrapper = (MessageWrapper) getItem(position, MessageWrapper.class);

        if (messageWrapper.isSeenExpanded()) {
            holder.expandSeenView(messageWrapper);
        } else {
            holder.collapseSeenView(messageWrapper);
        }

        if (messageWrapper.isDateExpanded()) {
            holder.expandDateView(messageWrapper);
        } else {
            holder.collapseDateView(messageWrapper);
        }

        return messageWrapper;
    }

    private MessageWrapper bindTextMessageViewHolder(OwnedTextMessageViewHolder holder, int position) {
        MessageWrapper messageWrapper = bindMessageViewHolder(holder, position);
        TextMessage textMessage = (TextMessage) messageWrapper.getMessage();

        holder.getBinding().txtMessage.setText(textMessage.getMessage());

        return messageWrapper;
    }

    private MessageWrapper bindImageMessageViewHolder(OwnedImageMessageViewHolder holder, int position) {
        MessageWrapper messageWrapper = bindMessageViewHolder(holder, position);
        ImageMessage imageMessage = (ImageMessage) messageWrapper.getMessage();

        holder.imageItemAdapter.refreshImageItems(imageMessage.getImages());

        return messageWrapper;
    }


    private void bindFriendTextMessageViewHolder(OppositeTextMessageViewHolder holder, int position) {
        MessageWrapper messageWrapper = bindMessageViewHolder(holder,position);
        TextMessage textMessage = (TextMessage) messageWrapper.getMessage();
        holder.getItemOppositeTextMessageBinding().txtMessage.setText(textMessage.getMessage());

        GlideApp.with(getContext())
                .load(adminInfo.getAvatarUrl())
                .placeholder(R.drawable.img_no_image)
                .into(holder.getItemOppositeTextMessageBinding().imgAvatar);
    }

    private void bindFriendImageMessageViewHolder(OppositeImageMessageViewHolder holder, int position) {
        MessageWrapper messageWrapper = bindImageMessageViewHolder(holder, position);

        GlideApp.with(getContext())
                .load(adminInfo.getAvatarUrl())
                .placeholder(R.drawable.img_no_image)
                .into(holder.getItemOwnedImageMessageBinding().imgAvatar);
    }


    private void bindFriendTypingViewHolder(OppositeTypingViewHolder holder, int position) {
        String userTypingID = (String) getItem(position, String.class);
//        UserInfo userInfo = adminInfo.get(userTypingID);

        GlideApp.with(getContext())
                .load(R.drawable.img_no_image)
                .into(holder.getBinding().imgAvatar);
    }

    private String generateSeenState(Map<String, Boolean> seenBy) {
        if (seenBy != null && !seenBy.isEmpty()) {
            int size = seenBy.size();
            if (size == 1 && seenBy.containsKey(ownerInfo.getId())) {
                return getContext().getString(R.string.seen);
            }
            StringBuilder result = new StringBuilder(getContext().getString(R.string.seen_by));
//            for (Map.Entry<String, UserInfo> entry : adminInfo.entrySet()) {
//                if (seenBy.containsKey(entry.getKey())) {
//                    result.append(" ").append(entry.getValue().getFirstName());
//                }
//            }
            result.append(" ").append(adminInfo.getFirstName());
            return result.toString();
        }
        return getContext().getString(R.string.sent);
    }

    @Override
    protected void bindNormalViewHolder(NormalViewHolder holder, int position) {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    private boolean isOwnerMessage(BaseMessage baseMessage) {
        return baseMessage.getOwnerID().equals(ownerInfo.getId());
    }

    public void addTopMessage(BaseMessage baseMessage) {
        if (isOwnerMessage(baseMessage)) {
            switch (baseMessage.getType()) {
                case BaseMessage.TEXT_MESSAGE: {
                    addModel(isOppositeTypingMessageShowing ? 1 : 0, new MessageWrapper(baseMessage), VIEW_TYPE_OWNED_TEXT_MESSAGE, true);
                }
                break;

                case BaseMessage.IMAGE_MESSAGE: {
                    addModel(isOppositeTypingMessageShowing ? 1 : 0, new MessageWrapper(baseMessage), VIEW_TYPE_OWNED_IMAGE_MESSAGE, true);
                }
                break;

                default: {
                    break;
                }
            }
        } else {
            switch (baseMessage.getType()) {
                case BaseMessage.TEXT_MESSAGE: {
                    addModel(isOppositeTypingMessageShowing ? 1 : 0, new MessageWrapper(baseMessage), VIEW_TYPE_FRIEND_TEXT_MESSAGE, true);
                }
                break;

                case BaseMessage.IMAGE_MESSAGE: {
                    addModel(isOppositeTypingMessageShowing ? 1 : 0, new MessageWrapper(baseMessage), VIEW_TYPE_FRIEND_IMAGE_MESSAGE, true);
                }
                break;

                default: {
                    break;
                }
            }
        }
    }

    public void addMessages(List<BaseMessage> baseMessages) {
        if (baseMessages == null || baseMessages.isEmpty()) {
            return;
        }
        for (BaseMessage baseMessage : baseMessages) {
            if (isOwnerMessage(baseMessage)) {
                switch (baseMessage.getType()) {
                    case BaseMessage.TEXT_MESSAGE: {
                        addModel(new MessageWrapper(baseMessage), VIEW_TYPE_OWNED_TEXT_MESSAGE, false);
                    }
                    break;

                    case BaseMessage.IMAGE_MESSAGE: {
                        addModel(new MessageWrapper(baseMessage), VIEW_TYPE_OWNED_IMAGE_MESSAGE, false);
                    }
                    break;

                    default: {
                        break;
                    }

                }
            } else {
                switch (baseMessage.getType()) {
                    case BaseMessage.TEXT_MESSAGE: {
                        addModel(new MessageWrapper(baseMessage), VIEW_TYPE_FRIEND_TEXT_MESSAGE, false);
                    }
                    break;

                    case BaseMessage.IMAGE_MESSAGE: {
                        addModel(new MessageWrapper(baseMessage), VIEW_TYPE_FRIEND_IMAGE_MESSAGE, false);
                    }
                    break;

                    default: {
                        break;
                    }

                }
            }
        }
    }

    public void updateMessage(BaseMessage baseMessage, int position) {
        MessageWrapper messageWrapper = (MessageWrapper) getItem(isOppositeTypingMessageShowing ? position + 1 : position, MessageWrapper.class);
        messageWrapper.getMessage().update(baseMessage);
        notifyItemChanged(position);
    }

    public void showOwnerImageMessageUploaded(String url, int imagePosition) {
        int position = isOppositeTypingMessageShowing ? 1 : 0;
        RecyclerView recyclerView = getRecyclerView();
        View view = recyclerView.getChildAt(position);
        if (view != null) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder instanceof OwnedImageMessageViewHolder) {
                ((OwnedImageMessageViewHolder) viewHolder).imageItemAdapter.updateImageItem(imagePosition, url);
            }
        }
    }

    public void showOwnerImageMessageError() {
        int position = isOppositeTypingMessageShowing ? 1 : 0;
        RecyclerView recyclerView = getRecyclerView();
        View view = recyclerView.getChildAt(position);
        if (view != null) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder instanceof OwnedImageMessageViewHolder) {
                ((OwnedImageMessageViewHolder) viewHolder).imageItemAdapter.showError();
            }
        }
    }

    public void showUploadingNextImageMessage() {
        int position = isOppositeTypingMessageShowing ? 1 : 0;
        RecyclerView recyclerView = getRecyclerView();
        View view = recyclerView.getChildAt(position);
        if (view != null) {
            RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
            if (viewHolder instanceof OwnedImageMessageViewHolder) {
                ((OwnedImageMessageViewHolder) viewHolder).imageItemAdapter.uploadNextImage();
            }
        }
    }

    abstract class BaseMessageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtSeen;
        TextView txtTime;

        BaseMessageViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            txtSeen = itemView.findViewById(R.id.txt_seen);
            txtTime = itemView.findViewById(R.id.txt_time);
        }

        @Override
        public void onClick(View view) {
            MessageWrapper messageWrapper = (MessageWrapper) getItem(getAdapterPosition(), MessageWrapper.class);

            if (messageWrapper.isExpanded()) {
                collapseSeenView(messageWrapper);
                collapseDateView(messageWrapper);
                messageWrapper.setExpanded(false);
            } else {
                TransitionManager.beginDelayedTransition((ViewGroup) itemView);
                expandSeenView(messageWrapper);
                expandDateView(messageWrapper);
                messageWrapper.setExpanded(true);
            }
        }

        void expandSeenView(MessageWrapper messageWrapper) {
            BaseMessage baseMessage = messageWrapper.getMessage();
            if (baseMessage.getCreatedDate() == null) {
                return;
            }
            txtSeen.setText(generateSeenState(baseMessage.getSeenBy()));
            txtSeen.setVisibility(View.VISIBLE);
            messageWrapper.setSeenExpanded(true);
        }

        void collapseSeenView(MessageWrapper messageWrapper) {
            txtSeen.setVisibility(View.GONE);
            messageWrapper.setSeenExpanded(false);
        }

        void expandDateView(MessageWrapper messageWrapper) {
            Date createdDate = messageWrapper.getMessage().getCreatedDate();
            if (createdDate == null) {
                return;
            }
            txtTime.setText(Utils.formatDateTime(getContext(), createdDate));
            txtTime.setVisibility(View.VISIBLE);
            messageWrapper.setDateExpanded(true);
        }

        void collapseDateView(MessageWrapper messageWrapper) {
            txtTime.setVisibility(View.GONE);
            messageWrapper.setDateExpanded(false);
        }
    }


    class OwnedTextMessageViewHolder extends BaseMessageViewHolder {
        private ItemOwnedTextMessageBinding binding;

        OwnedTextMessageViewHolder(View itemView) {
            super(itemView);
        }

        public void setBinding(ItemOwnedTextMessageBinding binding) {
            this.binding = binding;
        }

        public ItemOwnedTextMessageBinding getBinding() {
            return binding;
        }
    }

    class OwnedImageMessageViewHolder extends BaseMessageViewHolder {
        SingleUploadImageItemAdapter imageItemAdapter;
        ItemOwnedImageMessageBinding itemOwnedImageMessageBinding;

        public void setItemOwnedImageMessageBinding(ItemOwnedImageMessageBinding itemOwnedImageMessageBinding) {
            this.itemOwnedImageMessageBinding = itemOwnedImageMessageBinding;
        }

        OwnedImageMessageViewHolder(View itemView, RecyclerView.RecycledViewPool viewPool, boolean isRTL) {
            super(itemView);
            Context context = getContext();
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3,
                    GridLayoutManager.VERTICAL, true) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }

                @Override
                protected boolean isLayoutRTL() {
                    return isRTL;
                }
            };
            imageItemAdapter = new SingleUploadImageItemAdapter(context);
            imageItemAdapter.setOnItemClickListener((adapter, position) -> {
                ImageItem imageItem = adapter.getItem(position);
//                Intent intent = new Intent(context, ImageDetailActivity.class);
//                if (imageItem.getUrl() == null) {
//                    intent.putExtra(Constants.KEY_IMAGE_URI, imageItem.getUri().toString());
//                } else {
//                    intent.putExtra(Constants.KEY_IMAGE_URL, imageItem.getUrl());
//                }
//                context.startActivity(intent);
            });
            int spacingInPixels = context.getResources().getDimensionPixelSize(R.dimen.small_padding);
            itemOwnedImageMessageBinding.rcImages.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
            itemOwnedImageMessageBinding.rcImages.setRecycledViewPool(viewPool);
            itemOwnedImageMessageBinding.rcImages.setLayoutManager(gridLayoutManager);
            itemOwnedImageMessageBinding.rcImages.setAdapter(imageItemAdapter);
        }
    }


    class OppositeTextMessageViewHolder extends BaseMessageViewHolder {
        private ItemOppositeTextMessageBinding itemOppositeTextMessageBinding;

        OppositeTextMessageViewHolder(View itemView, ItemOppositeTextMessageBinding itemOppositeTextMessageBinding) {
            super(itemOppositeTextMessageBinding.getRoot());
            this.itemOppositeTextMessageBinding = itemOppositeTextMessageBinding;
        }

        public ItemOppositeTextMessageBinding getItemOppositeTextMessageBinding() {
            return itemOppositeTextMessageBinding;
        }
    }

    class OppositeImageMessageViewHolder extends OwnedImageMessageViewHolder {
        private ItemFriendImageMessageBinding itemFriendImageMessageBinding;

        OppositeImageMessageViewHolder(View itemView, ItemFriendImageMessageBinding itemFriendImageMessageBinding, RecyclerView.RecycledViewPool viewPool) {
            super(itemView, viewPool, false);
            this.itemFriendImageMessageBinding = itemFriendImageMessageBinding;
        }

        public ItemFriendImageMessageBinding getItemOwnedImageMessageBinding() {
            return itemFriendImageMessageBinding;
        }
    }

    class OppositeTypingViewHolder extends RecyclerView.ViewHolder {
        private ItemOppositeTypingBinding binding;

        OppositeTypingViewHolder(ItemOppositeTypingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ItemOppositeTypingBinding getBinding() {
            return binding;
        }
    }
}
