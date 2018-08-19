package com.example.kolot.trypaginglibrary_1.Adapter;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kolot.trypaginglibrary_1.R;

public class EmployeeAdapter extends PagedListAdapter<Employee, EmployeeAdapter.EmployeeViewHolder> {

    public class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView tv1,tv2;

        public EmployeeViewHolder(View itemView) {
            super(itemView);

            tv1=(TextView) itemView.findViewById(R.id.textView);
            tv2=(TextView) itemView.findViewById(R.id.textView2);

        }

        public void bind(Employee position){
            tv1.setText(position.getName());
            tv2.setText(String.valueOf(position.getAge()));
        }
    }

    public EmployeeAdapter(@NonNull DiffUtil.ItemCallback<Employee> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmployeeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_element,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}
