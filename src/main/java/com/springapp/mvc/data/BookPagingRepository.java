package com.springapp.mvc.data;

import com.springapp.mvc.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

/**
 * Created by Иван on 09.10.2016.
 */
public interface BookPagingRepository extends PagingAndSortingRepository<Book, UUID> {
    @Query("select b from Book b where b.id in :ids")
    List<Book> findByIds(@Param("ids") List<UUID> ids, Pageable pageRequest);
}
