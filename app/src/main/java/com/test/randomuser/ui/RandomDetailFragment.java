package com.test.randomuser.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.test.randomuser.data.model.RandomPerson;
import com.test.randomuser.databinding.FragmentRandomDetailBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class RandomDetailFragment extends BottomSheetDialogFragment {

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
        RandomPerson user = null;
        if (getArguments() != null) {
            String name = getArguments().getString("name");
            String email = getArguments().getString("email");
            String cell = getArguments().getString("cell");
            String location = getArguments().getString("location");
            String picture = getArguments().getString("picture");
            String date = getArguments().getString("date");

            updateUi(name, email, cell, location, picture, date);
        }
        return binding.getRoot();
    }

    private void updateUi(String name, String email, String cell, String location, String picture, String date) {
        binding.nameProfile.setText(name);
        binding.emailProfile.setText(email);
        binding.phoneText.setText(cell);
        binding.locationText.setText(location);
        Glide.with(binding.getRoot()).load(picture).into(binding.imageProfile);
        binding.dateText.setText(getFormatDate(date));
    }

    private String getFormatDate(String day) {
        String dateReturn = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
            Date date = formatter.parse(day);
            SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            assert date != null;
            dateReturn = formatter2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateReturn;
    }

}