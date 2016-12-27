package com.springapp.mvc.data.shop;

import com.springapp.mvc.model.shop.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by Иван on 27.12.2016.
 */
public interface UserPagingRepository extends PagingAndSortingRepository<User, UUID> {
    @Query(value = "select i from User i " +
            "where i.name LIKE CONCAT('%',:text,'%') or i.surname LIKE CONCAT('%',:text,'%') " +
            "or i.middleName LIKE CONCAT('%',:text,'%') or i.login LIKE CONCAT('%',:text,'%')")
    List<User> findAll(@Param("text") String text);
}
