package com.springapp.mvc.service.impl;

import com.springapp.mvc.service.ShopLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Иван on 30.12.2016.
 */
@Transactional
@Service
public class ShopLoginServiceBean implements ShopLoginService {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public boolean checkLogin(String login, String password) {
        boolean res = false;

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            List list = em.createQuery("from User u where u.login = :login and u.password = :password")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getResultList();
            res = list.size() > 0;
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return res;
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
