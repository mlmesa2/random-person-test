package com.test.randomuser.ui;

import static com.test.randomuser.Util.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.randomuser.data.model.Info;
import com.test.randomuser.data.model.NetwokResult;
import com.test.randomuser.data.model.Root;
import com.test.randomuser.data.repository.RandomPersonRepositoryImpl;
import com.test.randomuser.ui.state.ListState;

import java.util.Collections;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

@HiltViewModel
public class RandomListViewModel extends ViewModel {
    private RandomPersonRepositoryImpl mRepository;
    private CompositeDisposable compositeDisposable;

    private final MutableLiveData<ListState>  listState = new MutableLiveData<ListState>(
            new ListState( null, null, false, null)
            );

    public LiveData<ListState> getListState() {
        return listState;
    }

    @Inject
    public RandomListViewModel(RandomPersonRepositoryImpl repository) {
        mRepository = repository;
        compositeDisposable = new CompositeDisposable();
    }

    public void getRandomUsers(int page, int pageSize, String seed) {
        listState.setValue(new ListState(
                        null,
                        "",
                        true,
                        null
                )
        );
        NetwokResult result = new NetwokResult(
                new NetwokResult.Data(Collections.emptyList(), new Info("",0,0,"")),
                ""
        );

        mRepository.getRamdomUserList(page, pageSize, seed)
                .subscribe(new Observer<Root>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: " + d);
                    }

                    @Override
                    public void onNext(Root root) {
                        if (root.getResults() != null) {

                            result.getData().setListResult(root.getResults());
                            result.getData().setInfo(root.getInfo());
                            result.setErrorMessage("");

                            listState.setValue(new ListState(
                                    result.getData().getListResult(),
                                    "",
                                    false,
                                    result.getData().getInfo()
                            ));
                        } else {
                            result.setErrorMessage("Error leyendo los datos");
                            listState.setValue(new ListState(
                                    null,
                                    result.errorMessage,
                                    false,
                                    null
                            ));
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: " + e);
                        listState.setValue(new ListState(
                                null,
                                "Error leyendo los datos:" + e.getMessage(),
                                false,
                                null
                        ));
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Complete DATA LOAD");
                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
