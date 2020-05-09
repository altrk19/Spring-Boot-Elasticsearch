package com.spring.elasticsearch.repository;

import com.spring.elasticsearch.entity.Person;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepo extends ElasticsearchRepository<Person, String> {

    @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    List<Person> getByCustomQuery(String search);

    List<Person> findByNameLikeOrSurnameLike(String name, String surname);
}