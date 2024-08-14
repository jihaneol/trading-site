package com.example.trade_site.repository;

import com.example.trade_site.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<BoardFileEntity, Long> {
}
