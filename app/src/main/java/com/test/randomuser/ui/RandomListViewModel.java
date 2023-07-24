package com.test.randomuser.ui;

import static com.test.randomuser.Util.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.test.randomuser.data.model.NetwokResult;
import com.test.randomuser.data.repository.RandomPersonRepositoryImpl;
import com.test.randomuser.ui.state.ListState;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RandomListViewModel extends ViewModel {
    RandomPersonRepositoryImpl mRepository;


    MutableLiveData<NetwokResult> listMutableLiveData;

    private final MutableLiveData<ListState>  listState = new MutableLiveData<ListState>(
            new ListState( null, null, true, null)
            );

    public LiveData<ListState> getListState() {
        return listState;
    }

    /*@Inject
    RandomPersonRepositoryImpl repository;
*/
    @Inject
    public RandomListViewModel(RandomPersonRepositoryImpl repository) {
        //listMutableLiveData = new MutableLiveData<>();
        mRepository = repository;
    }

    public void getAllRandomUsers(Integer page, Integer size, String seed) {
        listState.setValue(new ListState(
                        null,
                        "",
                        true,
                        null
                )
        );
        NetwokResult result = mRepository.getRamdomUserList(page, size, seed);
        if (result.errorMessage == null) {
            //Log.d(TAG, "getAllRandomUsers: THIS IS THE RESULT:" + result.data.result);
            listState.setValue(new ListState(
                            result.data.result,
                            "",
                            false,
                            result.data.info
                    )
            );
            //listMutableLiveData.postValue(new NetwokResult(result.data));
        } else {
            listState.setValue(new ListState(
                    null,
                    result.errorMessage,
                    false,
                    null
            ));
            listMutableLiveData.postValue(new NetwokResult(result.errorMessage));
        }
    }
}
