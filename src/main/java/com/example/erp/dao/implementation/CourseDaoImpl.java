package com.example.erp.dao.implementation;

import com.example.erp.bean.Courses;
import com.example.erp.dao.CourseDao;
import com.example.erp.utils.SessionUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    @Override
    public boolean registerCourse(Courses course) {
        try (Session session = SessionUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(course);
            transaction.commit();
            return true;
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public List<Courses> getCourses() {
        Session session = SessionUtil.getSession();
        List<Courses> courses = new ArrayList<>();
        try {
            for (final Object course : session.createQuery("from Courses ").list()) {
                courses.add((Courses) course);
            }
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return courses;
    }

    @Override
    public Courses getCourseByID(Integer id) {
        try (Session session = SessionUtil.getSession()) {
            return session.get(Courses.class, id);
        } catch (HibernateException exception) {
            System.out.print(exception.getLocalizedMessage());
        }
        return null;
    }
}