package com.oose.jobportal.repositories;

import com.oose.jobportal.models.entities.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface WorkRepo extends JpaRepository<Work, Integer> {
    @Query("select w from Work w where w.date >= :current")
    List<Work> findTop10Work(@Param("current") Date current);
}
