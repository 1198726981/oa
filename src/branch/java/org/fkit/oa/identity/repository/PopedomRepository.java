package org.fkit.oa.identity.repository;

import java.util.List;

import org.fkit.oa.identity.domain.Popedom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PopedomRepository extends JpaRepository<Popedom, Long>{
	
	@Query("select p.opera.code from Popedom p where p.role.id = :id and p.module.code = :parentCode")
	public List<String> findByIdAndParentCode(@Param("id") long id, @Param("parentCode") String parentCode);
    
	@Modifying
	@Query("delete Popedom p where p.role.id = :id and p.module.code = :parentCode")
	public void setByIdAndParentCode(@Param("id") long id, @Param("parentCode") String parentCode);

	/*
	* distinct 排除重复值
	* inner join 内部连接，如果是sql需加 on将两表连接例如：on user.id=addr.user_id 而hibernate已经由映射关系指定则不需
	*
	* */
	@Query("select distinct p.module.code from Popedom p where "
			+ "p.role.id in(select r.id from Role r "
			+ "inner join r.users u where u.userId = ?1 ) "
			+ "order by p.module.code asc")
	public List<String> getUserPopedomModuleCodes(String userId);

	@Query("select distinct p.opera.code from Popedom p "
			+ "where p.role.id in(select r.id from Role r "
			+ "inner join r.users u where u.userId = ?1 ) order by p.opera.code asc")
	public List<String> getUserPopedomOperasCodes(String userId);
	
	
}





