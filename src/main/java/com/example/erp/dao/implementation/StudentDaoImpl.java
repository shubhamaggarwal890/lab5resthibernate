package com.example.erp.dao.implementation;

import com.example.erp.bean.Students;
import com.example.erp.dao.StudentDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class StudentDaoImpl implements StudentDao {

    @Override
    public boolean emailVerify(Students student) {
        Session session = SessionUtil.getSession();
        try {
            Query query = session.createQuery("from Students where email=:email");
            query.setParameter("email", student.getEmail());
            if(query.getResultList().size()==1){
                return true;
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean registerStudent(Students student) {
        Session session = SessionUtil.getSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }finally {
            session.close();
        }
    }
}
