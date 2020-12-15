package com.example.erp.service;

import com.example.erp.bean.Students;
import com.example.erp.dao.StudentDao;
import com.example.erp.dao.implementation.StudentDaoImpl;

public class StudentService {

    public boolean verifyEmail(Students student){
        StudentDao studentDao = new StudentDaoImpl();
        return studentDao.emailVerify(student);
    }
}
