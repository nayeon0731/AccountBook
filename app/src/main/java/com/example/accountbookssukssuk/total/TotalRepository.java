package com.example.accountbookssukssuk.total;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TotalRepository {
    private TotalDao mTotalDAO;
    int subId;
    private LiveData<List<TotalData>> mAllTotal;

    public TotalRepository(Application application){
        TotalDB db = TotalDB.getInstance(application);
        mTotalDAO = db.totalDao();

        mAllTotal = mTotalDAO.getAll();
    }

    public LiveData<List<TotalData>> getmAllTotal(){
        return mAllTotal;
    }

    public void insert(TotalData totalData){
        new insertAsyncTask1(mTotalDAO).execute(totalData);
    }

    private static class insertAsyncTask1 extends AsyncTask<TotalData, Void, Void> {
        private TotalDao mAsyncTaskDAO;

        insertAsyncTask1(TotalDao dao) {
            mAsyncTaskDAO = dao;
        }

        @Override
        protected Void doInBackground(final TotalData... params) {
            mAsyncTaskDAO.insert(params[0]);
            return null;
        }
    }
}
