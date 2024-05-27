package com.talenthub.empresanominamicroservice.repository;

import com.talenthub.empresanominamicroservice.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n WHERE n.employeeid = :employeeid ORDER BY n.id DESC LIMIT 1")
    News findLastByEmployeeid(@Param("employeeid") Long employeeid);

}
