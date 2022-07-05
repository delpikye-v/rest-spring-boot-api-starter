package com.dp.spring.template.repositories.tutorial;

import com.dp.spring.template.models.tutorial.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);

    @Query("SELECT t FROM Tutorial t WHERE t.id = :id and t.title = :title")
    Optional<Tutorial> findByIdAndTitleContaining(@Param("id") long id, @Param("title") String title);
}
