package org.fkit.oa.identity.repository;

import java.util.List;

import org.fkit.oa.identity.domain.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ModuleRepository extends JpaRepository<Module, String> , JpaSpecificationExecutor<Module>{

	/*
	* 在@Query注解中编写JPQL实现DELETE和UPDATE操作的时候必须加上@modifying注解，以通知Spring Data 这是一个DELETE或UPDATE操作。
	* */
	@Modifying
	@Query("delete Module m where m.code like ?1")
	public void setCode(String code) ;

	/**
	 *在没有通配符的情况下like就是=，length取长度
	 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!????????????????????
	 */

	@Query("select m from Module m where m.code like :parentCode and length(m.code) = :sonCodeLen")
	public List<Module> findModules(@Param("parentCode") String parentCode, @Param("sonCodeLen") int sonCodeLen);
	
	@Query("select Max(m.code) from Module m where m.code like :parentCode and  length(m.code) = :sonCodeLen ")
	public String findUniqueEntity(@Param("parentCode") String parentCode, @Param("sonCodeLen") int sonCodeLen);
	
	
}







