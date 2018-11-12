package com.goku.heroes.repository;

import com.goku.heroes.model.Hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    Iterable<Hero> findByNameContaining (String name);

    @Query(value= "from Hero where name= :name")
    Iterable<Hero> zoekTaak(@Param("name") String task);
}
