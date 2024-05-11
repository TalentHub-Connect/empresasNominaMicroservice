package com.talenthub.empresanominamicroservice.repository;

import com.talenthub.empresanominamicroservice.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
