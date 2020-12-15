package com.example.erp.dao;

import com.example.erp.bean.Students;

public interface StudentDao {

    boolean emailVerify(Students student);
    boolean registerStudent(Students student);
}
