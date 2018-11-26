package zx.spring.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.spring.model.ProductModel;

@Mapper
public interface ProductService {

	@Select("SELECT * FROM cft_product where isshow=1")
	List<ProductModel> findAll();

	@Select("SELECT category FROM cft_product group by category")
	List<Map> getCategory();

	@Select("SELECT * FROM cft_product where id = #{id}")
	ProductModel findById(@Param("id") String id);

	@Update("UPDATE cft_product SET category = #{category}, name = #{name}, color=#{color}, madein=#{madein}, type=#{type}, c1=#{c1}, c2=#{c2}, c3=#{c3}, c4=#{c4}, qb=#{qb}, price=#{price}, size=#{size}, isshow=#{isshow}, isstock=#{isstock}, image=#{image} WHERE id = #{id};")
	int update(ProductModel product);

	@Insert("INSERT INTO cft_product (`category`, `name`,`color`,`type`,`madein`,`c1`,`c2`,`c3`,`c4`,`qb`,`price`,`size`,`isshow`,`isstock`,`image`) VALUES (#{category},#{name},#{color},#{type},#{madein},#{c1},#{c2},#{c3},#{c4},#{qb},#{price},#{size},#{isshow},#{isstock},#{image})")
	int insert(ProductModel product);

	@Delete("DELETE FROM cft_product WHERE id  = #{id}")
	int deleteById(int id);
}