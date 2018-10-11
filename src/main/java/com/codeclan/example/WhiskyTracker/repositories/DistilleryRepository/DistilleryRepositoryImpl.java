package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Transactional
    public List<Distillery> getDistilleryByRegion(String region){
        List<Distillery> distilleries = null;
        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Distillery.class);
            cr.add(Restrictions.eq("region", region));
            distilleries = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return distilleries;
    }

    @Transactional
    public List<Distillery> getDistilleriesHasWhiskyFromCertainAge(int age){


        List<Whisky> whiskies = null;

        Session session = entityManager.unwrap(Session.class);
        try {
            Criteria cr = session.createCriteria(Whisky.class);
            cr.add(Restrictions.gt("age", age));
            whiskies = cr.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        List<Distillery> distileries = new ArrayList<>();
        for(Whisky whisky :whiskies){
            distileries.add(whisky.getDistillery());
        }

        return distileries;
    }





}
