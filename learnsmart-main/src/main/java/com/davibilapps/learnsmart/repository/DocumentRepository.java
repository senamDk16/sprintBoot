package com.davibilapps.learnsmart.repository;

import com.davibilapps.learnsmart.entity.Document;
import com.davibilapps.learnsmart.entity.Exercise.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository extends JpaRepository<Document,Long> {

    Optional<Document> findByTrackingId(UUID uuid);

    List<Document> findAllByQuestionOrderByName( Question question);


}
