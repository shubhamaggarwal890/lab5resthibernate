package com.example.erp.service;

import com.example.erp.bean.Students;
import com.example.erp.dao.StudentDao;
import com.example.erp.dao.implementation.StudentDaoImpl;

public class StudentService {
    StudentDao studentDao = new StudentDaoImpl();
    public boolean verifyEmail(Students student){
        return studentDao.emailVerify(student);
    }

    public boolean registerStudent(Students student){

        return studentDao.registerStudent(student);
    }
}
