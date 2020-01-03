package com.beetech.tienichmuasam.ui.home.search;


import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.ListProductAdapter;
import com.beetech.tienichmuasam.adapter.ListWaveMusicAdapter;
import com.beetech.tienichmuasam.adapter.SuggestAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.custom.SpacesItemDecoration;
import com.beetech.tienichmuasam.databinding.FragmentSearchBinding;
import com.beetech.tienichmuasam.entity.response.ListProductResponse;
import com.beetech.tienichmuasam.ui.product.DetaillProductFragment;
import com.beetech.tienichmuasam.utils.Constant;
import com.beetech.tienichmuasam.utils.UIUtil;
import com.beloo.widget.chipslayoutmanager.ChipsLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends BaseFragment<FragmentSearchBinding> {
    private SearchViewModel searchViewModel;
    private ListWaveMusicAdapter musicAdapter;
    private List<Double> listDb = new ArrayList<>();
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    private ListProductAdapter listProductAdapter;

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
        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
    }

    @Override
    public void initData() {
        initWaveMusic();
        initSpeechRecognizer();
        initSuggest();
        initSearch();
    }

    @Override
    public void initListener() {
        super.initListener();

        binding.btnStopRecord.setOnClickListener(view -> {
            stopRecord();
        });

        binding.searchView.setOnSearchKeyBoardListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchViewModel.search(binding.searchView.getSearchText(),true);
                return true;
            }
            return false;
        }).setOnClickSearchListener(view -> {
            searchViewModel.search(binding.searchView.getSearchText(),true);
        }).setOnClickRecordListener(v -> {
            //fixme check permission record
            startRecord();
        });

    }

    //region record
    private void initWaveMusic() {
        if (getContext() == null) {
            return;
        }
        binding.waveView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        musicAdapter = new ListWaveMusicAdapter(listDb, getContext());
        binding.waveView.setAdapter(musicAdapter);
        int sizeListDb = 0;
        int widthItem = getResources().getDimensionPixelSize(R.dimen.margin_10dp);
        int width = UIUtil.widthScreenPixel(getContext()) - 2 * widthItem - getResources().getDimensionPixelSize(R.dimen._dp107);
        if (widthItem != 0) {
            sizeListDb = width / widthItem;
            listDb.clear();
            for (int i = 0; i < sizeListDb; i++) {
                listDb.add(0.0);
            }
            musicAdapter.notifyDataSetChanged();
        }
    }

    private void initSpeechRecognizer() {
        speech = SpeechRecognizer.createSpeechRecognizer(getActivity());
        speech.setRecognitionListener(recognitionListener);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,
                "vi");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                "vi-VN");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 6);
    }

    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onBufferReceived(byte[] buffer) {
        }

        @Override
        public void onEndOfSpeech() {
            stopRecord();
        }

        @Override
        public void onError(int errorCode) {

        }

        @Override
        public void onEvent(int arg0, Bundle arg1) {
        }

        @Override
        public void onPartialResults(Bundle arg0) {
        }

        @Override
        public void onReadyForSpeech(Bundle arg0) {
        }

        @Override
        public void onResults(Bundle results) {
            ArrayList<String> matches = results
                    .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            if (matches != null && !matches.isEmpty()) {
                binding.searchView.setEdtSearch(matches.get(0));
            }

        }

        @Override
        public void onRmsChanged(float rmsdB) {
            double db = (double) rmsdB;
            db = 80 * Math.log10(db);
            if (musicAdapter == null) {
                return;
            }
            if (listDb.isEmpty()) {
                listDb.add(db);
            } else {
                listDb.add(listDb.size(), db);
                listDb.remove(0);
            }
            musicAdapter.notifyDataSetChanged();
        }


    };


    private void startRecord() {
        speech.startListening(recognizerIntent);
        binding.layoutRecord.setVisibility(View.VISIBLE);
        binding.layoutRecordBackground.setVisibility(View.VISIBLE);
        binding.layoutRecord.animate().translationY(binding.layoutRecord.getHeight()).setDuration(0).start();
        binding.layoutRecord.animate().translationY(0).setDuration(300).start();
    }

    private void stopRecord() {
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
        binding.layoutRecord.animate().translationY(binding.layoutRecord.getHeight()).setDuration(300).start();
        new Handler().postDelayed(() -> {
            if (getContext() != null) {
                binding.layoutRecord.setVisibility(View.GONE);
                binding.layoutRecordBackground.setVisibility(View.GONE);
            }
        }, 300);
        speech.stopListening();
    }

    //endregion

    private void initSuggest() {
        SuggestAdapter suggestAdapter = new SuggestAdapter(suggest -> {
            binding.searchView.setEdtSearch(suggest);
        });
        ChipsLayoutManager chipsLayoutManager = ChipsLayoutManager.newBuilder(getContext()).build();
        binding.rcvSuggest.setLayoutManager(chipsLayoutManager);
        binding.rcvSuggest.addItemDecoration(new SpacesItemDecoration(getContext().getResources().getDimensionPixelSize(R.dimen.margin_6dp)));
        binding.rcvSuggest.setAdapter(suggestAdapter);
    }

    private void initSearch(){
        listProductAdapter = new ListProductAdapter(getContext());
        binding.rcvSearch.setListLayoutManager(LinearLayoutManager.VERTICAL);
        binding.rcvSearch.setAdapter(listProductAdapter);
        binding.rcvSearch.setOnLoadingMoreListener(() -> searchViewModel.search(binding.searchView.getSearchText(),false));

        binding.rcvSearch.setOnItemClickListener((adapter, viewHolder, viewType, position) -> {
            ListProductResponse searchResponse = listProductAdapter.getItem(position, ListProductResponse.class);
            HashMap<String,String> data = new HashMap<>();
            data.put(Constant.PRODUCT_ID,searchResponse.getId());
            getViewController().addFragment(DetaillProductFragment.class,data);
        });
        searchViewModel.getSearch().observe(getViewLifecycleOwner(),
                searchResponseListResponse -> handleLoadMoreResponse(searchResponseListResponse, searchResponseListResponse.isRefresh(), searchResponseListResponse.isCanLoadmore()));
    }

    @Override
    protected void getListResponse(List<?> data, boolean isRefresh, boolean canLoadmore) {
        binding.rcvSearch.enableLoadmore(canLoadmore);
        if (isRefresh) {
            binding.rcvSearch.refresh(data);
        } else {
            binding.rcvSearch.addItem(data);
        }
    }
}
