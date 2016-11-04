package com.springapp.mvc.converters;

import com.springapp.mvc.data.AuthorRepository;
import com.springapp.mvc.model.Author;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

/**
 * Created by Иван on 17.10.2016.
 */
public class IdToAuthorConverter implements Converter<String, Author> {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author convert(String idStr) {
        if (StringUtils.isEmpty(idStr)) {
            return null;
        }

        UUID id = UUID.fromString(idStr);
        return authorRepository.findOne(id);
    }
}
