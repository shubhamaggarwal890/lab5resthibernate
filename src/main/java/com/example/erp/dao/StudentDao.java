package com.example.erp.dao;

import com.example.erp.bean.Students;

public interface StudentDao {

    Students emailVerify(Students student);
    boolean registerStudent(Students student);
}
