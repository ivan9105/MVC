package com.springapp.mvc.service.impl;

import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by Иван on 02.01.2017.
 */
@Transactional
@Service
public class ShopCategoryServiceBean implements ShopCategoryService {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void updateHierarchy(Category category) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Category reload = (Category) em.createQuery("from Category c " +
                    "where c.id = :cId")
                    .setParameter("cId", category.getId()).getSingleResult();
            if (reload != null) {
                int level = category.getLevel() != null ? category.getLevel() : 0;
                updateChild(reload.getChild(), level, em);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void updateChild(List<Category> child, int parentLevel, EntityManager em) {
        if (child != null && child.size() > 0) {
            for (Category category : child) {
                category.setLevel(parentLevel + 1);
                updateChild(category.getChild(), category.getLevel(), em);
            }
        }
    }

    private EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
