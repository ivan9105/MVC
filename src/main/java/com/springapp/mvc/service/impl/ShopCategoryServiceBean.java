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
import java.util.*;

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
                            (id == null ? "c.parent is null" : "c.parent.id = :id")
            );
            if (id != null) {
                query.setParameter("id", id);
            }

            List list = query.getResultList();
            for (Object object : list) {
                Category category = (Category) object;
                CategoryDto dto = DtoConverter.toCategoryDto(category, request);
                fillChild(dto, request, category.getChild(), em);
                res.add(dto);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        for (CategoryDto categoryDto : res) {
            categoryDto.setParent(null);
            clearParent(categoryDto.getChild());
        }

        return res;
    }

    @Override
    public List<CategoryDto> getRootPath(UUID id, HttpServletRequest request) {
        List<CategoryDto> res = new ArrayList<>();

        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        try {
            Query query = em.createQuery("from Category c " +
                    "where c.id = :id");
            query.setParameter("id", id);
            List result = query.getResultList();
            if (!result.isEmpty()) {
                Category category = (Category) result.get(0);
                while (category.getParent() != null) {
                    res.add(DtoConverter.toCategoryDto(category, request));
                    category = category.getParent();
                }
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }

        Collections.sort(res, new Comparator<CategoryDto>() {
            @Override
            public int compare(CategoryDto category1, CategoryDto category2) {
                return category1.getLevel().compareTo(category2.getLevel());
            }
        });

        return res;
    }

    private void clearParent(List<CategoryDto> child) {
        if (child != null && !child.isEmpty()) {
            for (CategoryDto categoryDto : child) {
                categoryDto.setParent(null);
                clearParent(categoryDto.getChild());
            }
        }
    }

    private void fillChild(CategoryDto mainDto, HttpServletRequest request, List<Category> child, EntityManager em) {
        if (child != null && child.size() > 0) {
            for (Category category : child) {
                CategoryDto dto = DtoConverter.toCategoryDto(category, request);
                dto.setParent(mainDto);
                mainDto.getChild().add(dto);
                fillChild(dto, request, category.getChild(), em);
            }
        } else {
            Query countQuery = em.createQuery("select count(i.id) from Item i where i.category.id = :cId");
            countQuery.setParameter("cId", UUID.fromString(mainDto.getId()));
            mainDto.setItemsCount(Integer.valueOf(String.valueOf(countQuery.getResultList().get(0))));

            CategoryDto visitor = new CategoryDto();
            visitor.setItemsCount(mainDto.getItemsCount());
            visitor.setParent(mainDto.getParent());
            while (visitor.getParent() != null) {
                visitor.getParent().setItemsCount(visitor.getParent().getItemsCount() + visitor.getItemsCount());
                visitor = visitor.getParent();
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
