package com.test.randomuser.ui;

import static com.test.randomuser.Util.RESULTS;
import static com.test.randomuser.Util.SEED;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.randomuser.R;
import com.test.randomuser.data.model.RandomPerson;
import com.test.randomuser.databinding.FragmentRandomListBinding;
import com.test.randomuser.ui.adapter.PaginationScrollListener;
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRandomListBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        viewModel = new ViewModelProvider(this).get(RandomListViewModel.class);

        initListener();
        initObservers();
        return binding.getRoot();
    }

    private void initListener() {
        binding.getButton.setOnClickListener(v -> {
            viewModel.getRandomUsers(page, RESULTS, SEED);
            binding.getButton.setEnabled(false);
        });
    }

    private void initObservers() {
        viewModel.getListState().observe(this, uiState -> {
            binding.progress.setVisibility(uiState.getIsloading() ? View.VISIBLE : View.GONE);
            if (uiState.getResultList() != null) {
                if (uiState.getInfo().getPage() > 1){
                    binding.recycler.setVisibility(View.VISIBLE);
                    adpater.addUserList(uiState.getResultList());
                    page = uiState.getInfo().getPage();
                }else {
                    setupRecyclerView(uiState.getResultList());
                    page = uiState.getInfo().page;
                }
            }
            if ((!Objects.equals(uiState.getErrorMessage(), "")) && (uiState.getErrorMessage() != null)) {
                Toast.makeText(requireContext(), uiState.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setupRecyclerView(List<RandomPerson> list) {
        binding.recycler.setVisibility(View.VISIBLE);
        mLayoutManager = new LinearLayoutManager(requireContext());
        binding.recycler.setLayoutManager(mLayoutManager);
        adpater = new RandomListAdpater(list);
        adpater.setOnItemClickListener(this);
        binding.recycler.setAdapter(adpater);
        binding.recycler.addOnScrollListener(new PaginationScrollListener(mLayoutManager) {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }

            @Override
            protected void loadMoreItems() {
                updateRecycler();
            }

            @Override
            public boolean isLastPage() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }
        });
    }

    private void updateRecycler(){
        page+=1;
        viewModel.getRandomUsers(page, RESULTS, SEED);

    }

    @Override
    public void onItemClick(int position, RandomPerson randomPerson) {
        Bundle args = new Bundle();
        args.putString("name", randomPerson.getFullName());
        args.putString("email", randomPerson.getEmail());
        args.putString("cell", randomPerson.getCell());
        args.putString("location", randomPerson.getLocation().getCity());
        args.putString("picture", randomPerson.getPicture().getMedium());
        args.putString("date", randomPerson.getRegistered().getDate().toString());

        NavHostFragment.findNavController(this).navigate(R.id.randomDetailFragment, args);
    }
}