package com.test.randomuser.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.randomuser.R;
import com.test.randomuser.databinding.FragmentRandomDetailBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RandomDetailFragment extends Fragment {

    private FragmentRandomDetailBinding binding;

    public RandomDetailFragment() {
        // Required empty public constructor
    }


    public static RandomDetailFragment newInstance(String param1, String param2) {
        RandomDetailFragment fragment = new RandomDetailFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRandomDetailBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}