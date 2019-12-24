package com.beetech.tienichmuasam.ui.home.search;


import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.FragmentSearchBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment<FragmentSearchBinding> implements View.OnTouchListener{
    private SearchViewModel searchViewModel;
    private int _xDelta;
    private int _yDelta;
    public SearchFragment() {
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void backFromAddFragment() {

    }

    @Override
    public boolean backPressed() {
        return false;
    }

    @Override
    public void initView() {

        binding.btnShortcut.setOnTouchListener(this);
        binding.btnShortcut.setOnClickListener(v->{
            Toast.makeText(getContext(), "ahuhu", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void initData() {

    }



    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - lParams.leftMargin;
                _yDelta = Y - lParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view
                        .getLayoutParams();
                layoutParams.leftMargin = X - _xDelta;
                layoutParams.topMargin = Y - _yDelta;
                layoutParams.rightMargin = -250;
                layoutParams.bottomMargin = -250;
                view.setLayoutParams(layoutParams);
                break;
        }
        binding.mRrootLayout.invalidate();
        return true;
    }
}
