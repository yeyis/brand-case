package com.itheima.mapper;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import jogamp.graph.font.typecast.ot.table.ID;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Select("select * from tb_brand where id = #{id}")
    Brand selectById(int id);
    /**
     * 添加一个品牌
     * @param brand
     */
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    /**
     * 删除一个品牌
     * @param id
     */
    @Delete("delete from tb_brand where id = #{id}")
    void deleteById(int id);

    /**
     * 根据id更新一个品牌
     * @param brand
     */
    @Update("update tb_brand set company_name = #{companyName},brand_name = #{brandName},ordered = #{ordered},description = #{description},status=#{status} " +
            "where id = #{id}")
    void updateById(Brand brand);

    /**
     * 根据id数组删除品牌
     * @param ids
     */
    void deleteByIds(@Param("ids") int[] ids);

    /**
     * 分页查询分析，我们要从前端获得页码和每页条目数，返回的是所需对象的数组和对象的数量，不妨将其封装为一个对象
     */

    /**
     * 分页查询
     * @param begin
     * @param pageSize
     * @return
     */
    @Select("select * from tb_brand limit #{begin},#{pageSize}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin")int begin,@Param("pageSize") int pageSize);

    /**
     * 查询总得条目数
     * @return
     */
    @Select("select count(*) from tb_brand")
    int selectCount();

    /**
     * 分页条件查询
     * @param begin
     * @param pageSize
     * @param brand
     * @return
     */
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("pageSize") int pageSize, @Param("brand") Brand brand);

    /**
     * 根据条件查得的总数
     * @param brand
     * @return
     */
    int selectTotalCountByCondition(Brand brand);
}
