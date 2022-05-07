package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.impl.BrandServiceImpl;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();
    void add(Brand brand);
    void deleteById(int id);
    void update(Brand brand);
    void deleteByIds(int ids[]);
    PageBean<Brand> selectByPage(int pageSize, int pageNumber);
    PageBean<Brand> selectByPageAndCondition(int pageSize, int pageNumber,Brand brand);
}