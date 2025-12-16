package org.example.service;

import org.example.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeptService {
    List<Dept> findAll();

    Dept findById(Integer id);

    void deleteById(Integer id);

    void add(Dept dept);

    void update(Dept dept);
}
