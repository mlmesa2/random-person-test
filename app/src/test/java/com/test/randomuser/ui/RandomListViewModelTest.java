package com.test.randomuser.ui;

import static org.mockito.Mockito.when;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.test.randomuser.data.model.Info;
import com.test.randomuser.data.model.Root;
import com.test.randomuser.data.repository.RandomPersonRepositoryImpl;
import com.test.randomuser.ui.state.ListState;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import io.reactivex.Observable;

@RunWith(MockitoJUnitRunner.class)
public class RandomListViewModelTest extends TestCase {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private RandomPersonRepositoryImpl repository;

        private RandomListViewModel viewModel;

        @Before
        public void setup() {
            viewModel = new RandomListViewModel(repository);
        }

        @Test
        public void getRandomUsers_success() {
            // Arrange
            Root mockResponse = new Root(Collections.emptyList(), new Info());
            when(repository.getRamdomUserList(1, 10, "seed"))
                    .thenReturn(Observable.just(mockResponse));

            // Act
            viewModel.getRandomUsers(1, 10, "seed");

            // Assert
            ListState listState = viewModel.getListState().getValue();
            assertEquals(mockResponse.getResults(), listState.getResultList());
            assertEquals(mockResponse.getInfo(), listState.getInfo());
            assertEquals(Optional.of(false), listState.getIsloading());
        }

        @Test
        public void getRandomUsers_error() {
            // Arrange
            when(repository.getRamdomUserList(1, 10, "seed"))
                    .thenReturn(Observable.error(new Exception()));

            // Act
            viewModel.getRandomUsers(1, 10, "seed");

            // Assert
            ListState listState = viewModel.getListState().getValue();
            assertEquals(null, listState.getResultList());
            assertEquals(true, listState.getErrorMessage().contains("Error"));
            assertEquals(Optional.of(false), listState.getIsloading());
        }


}