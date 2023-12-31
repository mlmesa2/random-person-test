package com.test.randomuser.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.test.randomuser.data.model.RandomPerson;
import com.test.randomuser.databinding.RandomUserItemBinding;
import java.util.List;

public class RandomListAdpater extends RecyclerView.Adapter<RandomListAdpater.MyViewHolder> {

    private List<RandomPerson> mUsers;
    private RecyclerItemClickListener mListener;

    public RandomListAdpater(List<RandomPerson> users) {
        mUsers = users;
    }

    public void setOnItemClickListener(RecyclerItemClickListener mListener){
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RandomUserItemBinding randomUserItemBinding = RandomUserItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new MyViewHolder(randomUserItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RandomPerson result = mUsers.get(position);
        holder.render(result, position);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void addUserList(List<RandomPerson> list){
        mUsers.addAll(list);
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RandomUserItemBinding mRandomUserItemBinding;

        public MyViewHolder(@NonNull RandomUserItemBinding randomUserItemBinding) {
            super(randomUserItemBinding.getRoot());
            mRandomUserItemBinding = randomUserItemBinding;
        }

        public void render(RandomPerson result, Integer position) {
            mRandomUserItemBinding.email.setText(result.getEmail());
            mRandomUserItemBinding.name.setText(result.getFullName());
            Glide.with(mRandomUserItemBinding.getRoot())
                    .load(result.getPicture().getThumbnail())
                    .into(mRandomUserItemBinding.imageView);
            mRandomUserItemBinding.getRoot().setOnClickListener(v -> {
                mListener.onItemClick(position, result);
            });
        }
    }
}
