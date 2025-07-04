package com.newsaggregator.repository;

import com.newsaggregator.Entity.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsTypeRepository extends JpaRepository<NewsType,Integer> {
}
