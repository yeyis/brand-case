package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;
import org.apache.ibatis.annotations.Select;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    BrandService brandService = new BrandServiceImpl();
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.调用service查询
        List<Brand> brands = brandService.selectAll();

        //2.转为JSON
        String jsonString = JSON.toJSONString(brands);

        //3. 写数据
        response.setContentType("text/json;charset = utf-8");
        response.getWriter().write(jsonString);
    }
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //在页面获取json数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转为Brand对象
        Brand brand = JSON.parseObject(params,Brand.class);
        //调用service
        brandService.add(brand);
        //返回添加成功的消息
        response.getWriter().write("success");
    }
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //在页面获取id值
        int id = Integer.parseInt(request.getParameter("id"));
        brandService.deleteById(id);
        response.getWriter().write("success");
    }
    public void updateById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //在页面获取json数据
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        /*System.out.println(params);*/
        //转为Brand对象
        Brand brand = JSON.parseObject(params,Brand.class);

        brandService.update(brand);
        response.getWriter().write("success");
    }
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //在页面获取id值
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        int[] ids = JSON.parseObject(params,int[].class);
        brandService.deleteByIds(ids);
        response.getWriter().write("success");
    }
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.在前端获取页码和页面大小
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));

        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        Brand brand = JSON.parseObject(params, Brand.class);
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(pageSize,pageNumber,brand);
        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //3. 写数据
        response.setContentType("text/json;charset = utf-8");
        response.getWriter().write(jsonString);
    }
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //1.在前端获取页码和页面大小
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        PageBean<Brand> pageBean = brandService.selectByPage(pageSize,pageNumber);
        //2.转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //3. 写数据
        response.setContentType("text/json;charset = utf-8");
        response.getWriter().write(jsonString);
    }
}
