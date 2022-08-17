package com.example.demo.repository;

import com.example.demo.pojos.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Integer> {

}
