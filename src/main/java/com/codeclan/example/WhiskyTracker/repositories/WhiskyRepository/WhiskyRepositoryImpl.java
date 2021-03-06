package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;


import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Whisky> getWhiskiesFromCertainYear(int year){
        List<Whisky> whiskies = null;
        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Whisky.class);
            cr.add(Restrictions.eq("year", year));
            whiskies = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return whiskies;
    }

    @Transactional
    public List<Whisky> getWhiskiesFromCertainRegion(String region){


        List<Whisky> whiskies = null;
        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Whisky.class);
            cr.createAlias("distillery","distillery");
            cr.add(Restrictions.eq("distillery.region", region));
            whiskies = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return whiskies;




//        List<Whisky> whiskies = null;
//        Session session = entityManager.unwrap(Session.class);
//        try {
//            Criteria cr = session.createCriteria(Whisky.class);
//            Criteria distilleryCr = cr.createCriteria("distillery");
//            distilleryCr.add(Restrictions.eq("region", region));
//            whiskies = cr.list();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return whiskies;




//        List<Distillery> distileries = null;
//        Session session = entityManager.unwrap(Session.class);
//        try {
//            Criteria cr = session.createCriteria(Distillery.class);
//            cr.add(Restrictions.eq("region", region));
//            distileries = cr.list();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
//        Distillery distillery = distileries.get(0);
//        List<Whisky> whiskies = null;
//        //Session session = entityManager.unwrap(Session.class);
//        try {
//            Criteria cr = session.createCriteria(Whisky.class);
//            cr.createAlias("distillery", "distillery");
//            cr.add(Restrictions.eq("distillery.id", distillery.getId()));
//            whiskies = cr.list();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return whiskies;
    }

    @Transactional
    public List<Whisky> getWhiskiesFromCertainDisterryandAge(String name, int age) {




        List<Distillery> distileries = null;
        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Distillery.class);
            cr.add(Restrictions.eq("name", name));
            distileries = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        Distillery distillery = distileries.get(0);
        List<Whisky> whiskies = null;
        //Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Whisky.class);
            cr.createAlias("distillery", "distillery");
            cr.add(Restrictions.eq("distillery.id", distillery.getId()));
            cr.add(Restrictions.eq("age", age));
            whiskies = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return whiskies;
    }

}
