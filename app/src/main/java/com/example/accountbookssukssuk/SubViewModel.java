package com.example.accountbookssukssuk;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.accountbookssukssuk.total.TotalData;
import com.example.accountbookssukssuk.total.TotalRepository;

import java.util.List;

public class SubViewModel extends AndroidViewModel {
    private TotalRepository mRepository;
    private LiveData<List<TotalData>> mAllTotal;

    public SubViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TotalRepository(application);
        mAllTotal = mRepository.getmAllTotal();
    }

    public LiveData<List<TotalData>> getAllTotal() {
        return mAllTotal;
    }

    public void insert(TotalData totalData) {
        mRepository.insert(totalData);
    }
}
