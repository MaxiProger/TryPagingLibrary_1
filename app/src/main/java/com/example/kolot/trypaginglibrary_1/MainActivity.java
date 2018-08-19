package com.example.kolot.trypaginglibrary_1;

import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.kolot.trypaginglibrary_1.Adapter.Employee;
import com.example.kolot.trypaginglibrary_1.Adapter.EmployeeAdapter;
import com.example.kolot.trypaginglibrary_1.DataSource.MyPositionalDataSource;
import com.example.kolot.trypaginglibrary_1.Executors.MainThreadExecutor;
import com.example.kolot.trypaginglibrary_1.Storage.EmployeeStorage;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyPositionalDataSource positionalDataSource;
    private EmployeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        positionalDataSource = new MyPositionalDataSource(new EmployeeStorage());

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(10)
                .build();

        PagedList<Employee> employees = new PagedList.Builder<>(positionalDataSource, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .setNotifyExecutor(new MainThreadExecutor())
                .build();

        adapter = new EmployeeAdapter(new DiffUtil.ItemCallback<Employee>() {
            @Override
            public boolean areItemsTheSame(Employee oldItem, Employee newItem) {
                return oldItem.getName().contains(newItem.getName());
            }

            @Override
            public boolean areContentsTheSame(Employee oldItem, Employee newItem) {
                return oldItem.getAge() == newItem.getAge();
            }
        });
        adapter.submitList(employees);
        recyclerView.setAdapter(adapter);
    }
}
