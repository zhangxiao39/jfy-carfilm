package zx.spring.service;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import zx.spring.model.UserModel;

@Mapper
public interface UserService {

	@Select("SELECT * FROM cft_user where user_id=#{user_id} and password=#{password}")
	UserModel login(@Param("user_id") String user_id, @Param("password") String password);
}
