package com.test.randomuser.ui.adapter;

import com.test.randomuser.data.model.RandomPerson;

public interface RecyclerItemClickListener {
    void onItemClick (int position, RandomPerson result);
}