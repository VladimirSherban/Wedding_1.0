package com.example.wedding.repository;

import com.example.wedding.entity.WeddingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeddingDAO extends JpaRepository<WeddingEntity, Long> {
    List<WeddingEntity> findAllByDeletedFalse();

    List<WeddingEntity> findAllByDeadLineBefore(Date currentDate);
}
