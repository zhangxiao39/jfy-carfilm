package zx.spring.service;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import zx.spring.model.QainfoModel;


@Mapper
public interface QainfoService {
	@Select("SELECT * FROM cft_quality_search where code=#{carcode}")
	QainfoModel findByCarCode(@Param("carcode") String carcode);
	
	@Select("SELECT * FROM cft_quality_search")
	List<QainfoModel> findAll();
	
	@Select("SELECT * FROM cft_quality_search where id=#{id}")
	QainfoModel findById(@Param("id") String id);
	
	@Update("UPDATE cft_quality_search SET code = #{code}, addtime = #{addtime}, year=#{year} WHERE id = #{id};")
	int updateQaById(QainfoModel qa);
	
	@Insert("INSERT INTO cft_quality_search (`code`, `addtime`, `year`) VALUES (#{code}, #{addtime}, #{year})")
	int insert(QainfoModel qa);
	
	@Delete("DELETE FROM cft_quality_search WHERE id  = #{id}")
	int deleteById(int id);
}
