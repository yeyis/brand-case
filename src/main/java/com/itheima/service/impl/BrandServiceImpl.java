package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();

    /**
     * 查询所有
     *
     * @return
     */
    @Override
    public List<Brand> selectAll() {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = mapper.selectAll();
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.add(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteById(int id) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteById(id);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void update(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.updateById(brand);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int pageSize, int pageNumber) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = pageSize * (pageNumber - 1);
        List<Brand> brands = mapper.selectByPage(begin, pageSize);
        int totalSize = mapper.selectCount();
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(brands);
        pageBean.setTotalSize(totalSize);
        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int pageSize, int pageNumber, Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        int begin = pageSize * (pageNumber - 1);
        //处理brand对象适配模糊表达式
        String brandName = brand.getBrandName();
        if(brandName != null && brandName.length() != 0){
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if(companyName != null && companyName.length() != 0){
            brand.setCompanyName("%" + companyName + "%");
        }
        int totalCount = mapper.selectTotalCountByCondition(brand);
        List<Brand> rows = mapper.selectByPageAndCondition(begin, pageSize, brand);
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalSize(totalCount);
        return pageBean;
    }
}
