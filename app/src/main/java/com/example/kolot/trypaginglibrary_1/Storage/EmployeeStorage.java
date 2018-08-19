package com.example.kolot.trypaginglibrary_1.Storage;

import com.example.kolot.trypaginglibrary_1.Adapter.Employee;

import java.util.ArrayList;

public class EmployeeStorage {
    public ArrayList<Employee> employees;

    public ArrayList<Employee> getData(int start, int range) {

        employees = new ArrayList<Employee>();

        for (int i = 0; i < 100; i++)
            employees.add(i, new Employee(i + 12, "Max" + String.valueOf(i)));

        ArrayList<Employee> result = new ArrayList<>();

        if (start + range > employees.size()) {
            range = employees.size() - start;
        }
        if (start + range <= employees.size()) {
            range += start;
        }
        for (int i = start; i < range; i++) {
            result.add(employees.get(i));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return result;
    }
}
