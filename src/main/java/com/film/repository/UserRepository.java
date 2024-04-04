package com.film.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.film.models.UserModel;

import jakarta.transaction.Transactional;


public interface UserRepository extends JpaRepository<UserModel, Long>{
	UserModel findByUserName(String userName);
	
	@Query(value = "SELECT count(*) FROM users u WHERE u.username = :name", nativeQuery = true)
	int findByUser(@Param("name") String name);
	@Query(value = "SELECT count(*) FROM users u WHERE u.email = :email", nativeQuery = true)
	int findByEmail(@Param("email") String email);
	@Query(value = "SELECT point, fullname, maxim FROM users u WHERE u.id = :point", nativeQuery = true)
	Object[][] queryByPoint(@Param("point") int point);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET fullname = :display_name, point = :point WHERE id = :id", nativeQuery = true)
	public void updateFullName(@Param("display_name") String display_name, @Param("point") int point , @Param("id") int id);	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET maxim = :maxim WHERE id = :id", nativeQuery = true)
	public void updateMaxim(@Param("maxim") String maxim, @Param("id") int id);	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET is_activity = :isActivity, last_active = :lastActive WHERE id = :id", nativeQuery = true)
	public void updateIsActivity(@Param("isActivity") int isActivity, @Param("lastActive") String lastActive, @Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET image = :image WHERE id = :id", nativeQuery = true)
	public void updateAvatar(@Param("image") String image, @Param("id") int id);
}
