package com.test.randomuser.ui;

import static com.test.randomuser.Util.RESULTS;
import static com.test.randomuser.Util.SEED;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.test.randomuser.R;
import com.test.randomuser.Util;
import com.test.randomuser.data.model.Result;
import com.test.randomuser.databinding.FragmentRandomListBinding;
import com.test.randomuser.ui.adapter.RandomListAdpater;
import com.test.randomuser.ui.adapter.RecyclerItemClickListener;

import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RandomListFragment extends Fragment implements RecyclerItemClickListener {

    private RandomListViewModel viewModel;
    private FragmentRandomListBinding binding;
    private RandomListAdpater adpater;
    private LinearLayoutManager mLayoutManager;

    private int page = 1;

    public RandomListFragment() {
        // Required empty public constructor
    }


    public static RandomListFragment newInstance(String param1, String param2) {
        RandomListFragment fragment = new RandomListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRandomListBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(RandomListViewModel.class);

        //setupRecyclerView();
        initListener();
        initObservers();
        return binding.getRoot();
    }

    private void initListener() {
        binding.getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getAllRandomUsers(page, RESULTS, SEED);
            }
        });
    }

    private void initObservers() {
        viewModel.getListState().observe(this, uiState -> {
            binding.progress.setEnabled(uiState.getIsloading());
            if (uiState.getResultList() != null) {
                setupRecyclerView(uiState.getResultList());
                page = uiState.getInfo().page;
            }
            if (!Objects.equals(uiState.getErrorMessage(), "")) {
                Toast.makeText(requireContext(), uiState.getErrorMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setupRecyclerView(List<Result> list) {
        mLayoutManager = new LinearLayoutManager(requireContext());
        binding.recycler.setLayoutManager(mLayoutManager);
        adpater = new RandomListAdpater(list);
        adpater.setOnItemClickListener(this);
        binding.recycler.setAdapter(adpater);
    }

    private void updateRecycler(){
        page+=1;
        viewModel.getAllRandomUsers(page, RESULTS, SEED);

    }


    @Override
    public void onItemClick(int position, Result result) {
        NavHostFragment.findNavController(this).navigate(R.id.randomDetailFragment, new Bundle(

        ));
    }
}