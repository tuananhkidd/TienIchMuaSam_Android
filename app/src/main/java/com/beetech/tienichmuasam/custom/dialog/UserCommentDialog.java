//package com.beetech.tienichmuasam.custom.dialog;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.databinding.DataBindingUtil;
//import androidx.recyclerview.widget.LinearLayoutManager;
//
//import com.beetech.tienichmuasam.R;
//import com.beetech.tienichmuasam.adapter.CommentAdapter;
//import com.beetech.tienichmuasam.databinding.LayoutDialogRatingBinding;
//import com.beetech.tienichmuasam.ui.product.DetaillProductViewModel;
//import com.beetech.tienichmuasam.utils.Define;
//import com.beetech.tienichmuasam.utils.DialogUtil;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//
//import java.util.List;
//
//public class UserCommentDialog extends BottomSheetDialogFragment {
//    private LayoutDialogRatingBinding binding;
//    private DetaillProductViewModel detaillProductViewModel;
//    private CommentAdapter commentAdapter;
//
//    public void setDetaillProductViewModel(DetaillProductViewModel detaillProductViewModel) {
//        this.detaillProductViewModel = detaillProductViewModel;
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.layout_dialog_rating, container, false);
//        return binding.getRoot();
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        initAdapter();
//        init();
//    }
//
//    //    public UserCommentDialog(@NonNull Context context, ==) {
////        this.activity = new WeakReference<>(activity);
////        requestWindowFeature(Window.FEATURE_NO_TITLE);
////        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_select_product, null, false);
////        setContentView(binding.getRoot());
////        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
////    }
//
//    private void init() {
//        detaillProductViewModel.getComments().observe(this
//                , commentResponse -> {
//                    switch (commentResponse.getType()) {
//                        case Define.ResponseStatus.LOADING:
//                            DialogUtil.getInstance(getContext()).show();
//                            break;
//                        case Define.ResponseStatus.SUCCESS:
//                            getListResponse(commentResponse.getData(), commentResponse.isRefresh(), commentResponse.isCanLoadmore());
//                            DialogUtil.getInstance(getContext()).hidden();
//                            break;
//                        case Define.ResponseStatus.ERROR:
//                            DialogUtil.getInstance(getContext()).hidden();
//                    }
//                });
//
//        detaillProductViewModel.getCommentsAboutProduct(true);
//    }
//
//    private void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
//        binding.rcvCmt.enableLoadmore(canLoadmore);
//        if (isRefresh) {
//            binding.rcvCmt.refresh(data);
//        } else {
//            binding.rcvCmt.addItem(data);
//        }
//    }
//
//    private void initAdapter() {
//        commentAdapter = new CommentAdapter(getContext());
//        commentAdapter.setLoadingMoreListener(() -> {
//            detaillProductViewModel.getCommentsAboutProduct(false);
//        });
//        binding.rcvCmt.setListLayoutManager(LinearLayoutManager.VERTICAL);
//        binding.rcvCmt.setAdapter(commentAdapter);
//    }
//}
