package com.example.kolot.trypaginglibrary_1.DataSource;

import android.arch.paging.PositionalDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.kolot.trypaginglibrary_1.Adapter.Employee;
import com.example.kolot.trypaginglibrary_1.Storage.EmployeeStorage;

import java.util.List;

public class MyPositionalDataSource extends PositionalDataSource<Employee> {

    private final EmployeeStorage employeeStorage;

    public MyPositionalDataSource(EmployeeStorage employeeStorage) {
        this.employeeStorage = employeeStorage;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<Employee> callback) {

        Log.e("init", String.valueOf(params.requestedStartPosition)+" "+ String.valueOf( params.requestedLoadSize));

        List<Employee> result = employeeStorage.getData(params.requestedStartPosition, params.requestedLoadSize);
        callback.onResult(result, 0);
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<Employee> callback) {

        Log.e("load", String.valueOf(params.startPosition)+" "+ String.valueOf( params.loadSize));

        List<Employee> result = employeeStorage.getData(params.startPosition, params.loadSize);
        callback.onResult(result);
    }
}
