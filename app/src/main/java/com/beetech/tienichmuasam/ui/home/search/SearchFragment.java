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
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.beetech.tienichmuasam.R;
import com.beetech.tienichmuasam.adapter.ListWaveMusicAdapter;
import com.beetech.tienichmuasam.base.BaseFragment;
import com.beetech.tienichmuasam.databinding.FragmentSearchBinding;
import com.beetech.tienichmuasam.utils.UIUtil;

import java.util.ArrayList;
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
        if (getActivity() != null) {
            getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }
    }

    @Override
    public void initData() {
        initWaveMusic();
        initSpeechRecognizer();
    }

    @Override
    public void initListener() {
        super.initListener();
        binding.searchView.setOnClickRecordListener(v -> {
            //fixme check permission record
            startRecord();
        });

        binding.btnStopRecord.setOnClickListener(view -> {
            stopRecord();
        });

        binding.searchView.setOnSearchKeyBoardListener((textView, actionId, keyEvent) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                Toast.makeText(getContext(),  binding.searchView.getSearchText(), Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });

        binding.searchView.setOnClickSearchListener(view -> {
            Toast.makeText(getContext(),  binding.searchView.getSearchText(), Toast.LENGTH_SHORT).show();
        });
    }

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


    public void startRecord() {
        speech.startListening(recognizerIntent);
        binding.layoutRecord.setVisibility(View.VISIBLE);
        binding.layoutRecordBackground.setVisibility(View.VISIBLE);
        binding.layoutRecord.animate().translationY(binding.layoutRecord.getHeight()).setDuration(0).start();
        binding.layoutRecord.animate().translationY(0).setDuration(300).start();
    }

    public void stopRecord() {
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
}
