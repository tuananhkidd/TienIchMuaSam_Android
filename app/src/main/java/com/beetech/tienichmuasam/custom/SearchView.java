package com.beetech.tienichmuasam.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;

import com.beetech.tienichmuasam.R;

public class SearchView extends CustomViewConstraintLayout {
    private ClearableEditText edtSearch;
    private ImageView btnRecord;
    private ImageView btnSearch;

    public SearchView(Context context) {
        super(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    int getLayoutRes() {
        return R.layout.layout_search_view;
    }

    @Override
    int[] getStyableRes() {
        return R.styleable.SearchView;
    }

    @Override
    void initView() {
        edtSearch = findViewById(R.id.edt_search);
        btnRecord = findViewById(R.id.btn_record);
        btnSearch = findViewById(R.id.btn_search);

        edtSearch.getEditText().setImeOptions(EditorInfo.IME_ACTION_SEARCH);
    }

    @Override
    void initListener() {

    }

    @Override
    void initData() {

    }

    @Override
    void initDataFromStyable(TypedArray mTypedArray) {
        boolean isShowRecord = mTypedArray.getBoolean(R.styleable.SearchView_sv_show_record, true);
        btnRecord.setVisibility(isShowRecord ? VISIBLE : GONE);
        String hint = mTypedArray.getString(R.styleable.SearchView_sv_hint);
        edtSearch.setHint(hint);
    }

    public SearchView setOnClickSearchListener(OnClickListener listener) {
        btnSearch.setOnClickListener(listener);
        return this;
    }


    public SearchView setOnClickRecordListener(OnClickListener listener) {
        btnRecord.setOnClickListener(listener);
        return this;
    }

    public SearchView setOnSearchKeyBoardListener(TextView.OnEditorActionListener listener){
        edtSearch.getEditText().setOnEditorActionListener(listener);
        return this;
    }

    public String getSearchText() {
        return edtSearch.getText().toString();
    }

    public void setEdtSearch(String text) {
        edtSearch.setText(text);
    }


}
