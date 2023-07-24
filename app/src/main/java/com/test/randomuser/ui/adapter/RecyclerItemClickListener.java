package com.test.randomuser.ui.adapter;

import com.test.randomuser.data.model.Result;

import java.util.List;

public interface RecyclerItemClickListener {
    void onItemClick (int position, Result result);
}