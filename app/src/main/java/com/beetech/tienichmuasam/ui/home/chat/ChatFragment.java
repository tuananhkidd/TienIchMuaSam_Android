package com.beetech.tienichmuasam.ui.home.chat;

import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.ChatAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.base.EndlessLoadingRecyclerViewAdapter;
import com.beetech.tienichmuasam.databinding.ChatFragmentBinding;
import com.beetech.tienichmuasam.entity.chat.BaseMessage;
import com.beetech.tienichmuasam.entity.chat.UserInfo;
import com.beetech.tienichmuasam.utils.Constant;
import com.beetech.tienichmuasam.utils.DeviceUtil;
import com.google.firebase.FirebaseApp;

public class ChatFragment extends BaseFragment<ChatFragmentBinding> implements EndlessLoadingRecyclerViewAdapter.OnLoadingMoreListener {

    private ChatViewModel mViewModel;
    private ChatAdapter chatAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.chat_fragment;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        mViewModel.unregisterFriendTypingListener();
        mViewModel.unregisterOnMessageChangedListener();
        return false;
    }

    @Override
    public void initView() {
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel.class);
        mViewModel.getRoomID();
        if (getActivity() != null) {
            binding.llChat.setPadding(0, DeviceUtil.getStatusBarHeight(getActivity()), 0, 0);
        }
    }

    @Override
    public void initData() {
        FirebaseApp.initializeApp(getContext());
        initListener();
        initAdapter();
        initChat();
    }

    private void initAdapter() {
        //fixme
        UserInfo userInfo = new UserInfo("cd6fc031-4a31-433d-a0b4-a5fc3b2fb528", "kidd@gmail.com", "Tuấn Anh", "Kidd", "", true);
        UserInfo adminInfo = new UserInfo("dff7b0cc-e602-4e13-8002-f61bb7f2dfdb", "admin@gmail.com", "Tiện ích", "mua sắm", "", true);
        chatAdapter = new ChatAdapter(getContext(), userInfo, adminInfo);
        chatAdapter.setLoadingMoreListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, true);
        binding.rcMessages.setLayoutManager(linearLayoutManager);
        binding.rcMessages.setAdapter(chatAdapter);

//        mViewModel.getMessages(true, 10);
    }

    private void initListener() {
        mViewModel.registerFriendTypingListener("cd6fc031-4a31-433d-a0b4-a5fc3b2fb528");
        mViewModel.registerOnMessageChangedListener("", 14);

        binding.btnSend.setOnClickListener(v -> {
            mViewModel.sendTextMessage(binding.edtMessage.getText());
        });

        binding.edtMessage.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if ((start + count) == 0) {
                    mViewModel.updateUserTypingState("", "cd6fc031-4a31-433d-a0b4-a5fc3b2fb528", false);
                } else if (before == 0) {
                    mViewModel.updateUserTypingState("", "cd6fc031-4a31-433d-a0b4-a5fc3b2fb528", true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initChat() {
        mViewModel.getListMessage().observe(getViewLifecycleOwner(), baseMessages -> {
            chatAdapter.hideLoadingItem();
            chatAdapter.addMessages(baseMessages.getData());
        });

        mViewModel.getMessageListener().observe(getViewLifecycleOwner(), data -> {
            binding.edtMessage.setText("");
            boolean add = (boolean) data.get(Constant.ADD);
            if (add) {
                chatAdapter.addTopMessage((BaseMessage) data.get(Constant.DATA));
            } else {
                chatAdapter.updateMessage((BaseMessage) data.get(Constant.DATA), (int) data.get(Constant.POSITION));
            }
        });

        mViewModel.getTypingListener().observe(getViewLifecycleOwner(), typing -> {
            if (typing) {
                chatAdapter.showFriendTypingMessage("ADMIN");
            } else {
                chatAdapter.hideFriendTypingMessage("ADMIN");
            }
        });
    }

    @Override
    public void onLoadMore() {
        chatAdapter.showLoadingItem(true);
        mViewModel.getMessages(false, 10);
    }
}
