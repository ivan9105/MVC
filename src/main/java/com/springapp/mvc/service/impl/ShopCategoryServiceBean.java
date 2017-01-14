package com.springapp.mvc.service.impl;

import com.springapp.mvc.model.shop.Category;
import com.springapp.mvc.rest.dto.DtoConverter;
import com.springapp.mvc.rest.dto.shop.CategoryDto;
import com.springapp.mvc.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    @Override
    public void removeHierarchy(Category category) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Category reload = (Category) em.createQuery("from Category c " +
                    "where c.id = :cId")
                    .setParameter("cId", category.getId()).getSingleResult();
            if (reload != null) {
                em.remove(reload);
                removeChild(reload.getChild(), em);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<CategoryDto> getCategoriesDto(UUID id, HttpServletRequest request) {
        List<CategoryDto> res = new ArrayList<>();

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("from Category c " +
                            "where " +
                            (id == null ? "c.parent is null" : "c.id = :id")
            );
            if (id != null) {
                query.setParameter("id", id);
            }
            List list = query.getResultList();
            for (Object object : list) {
                Category category = (Category) object;
                CategoryDto dto = DtoConverter.toCategoryDto(category, request);
                fillChild(dto, request, category.getChild());
                res.add(dto);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return res;
    }

    private void fillChild(CategoryDto mainDto, HttpServletRequest request, List<Category> child) {
        if (child != null && child.size() > 0) {
            for (Category category : child) {
                CategoryDto dto = DtoConverter.toCategoryDto(category, request);
                mainDto.getChild().add(dto);
                fillChild(dto, request, category.getChild());
            }
        }
    }

    private void removeChild(List<Category> child, EntityManager em) {
        if (child != null && child.size() > 0) {
            for (Category category : child) {
                em.remove(category);
                removeChild(category.getChild(), em);
            }
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
