package com.film.repository;


import java.util.List;

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
	
	@Query(value = "SELECT id FROM users u WHERE u.email = :email", nativeQuery = true)
	int findByEmailRtId(@Param("email") String email);
	
	@Query(value = "SELECT users_badges FROM users WHERE id = :id", nativeQuery = true)
	String findBadgesById(@Param("id") int id);
	
	@Query(value = "SELECT point, fullname, maxim FROM users u WHERE u.id = :point", nativeQuery = true)
	Object[][] queryByPoint(@Param("point") int point);
	
	@Query(value = "SELECT point, crystal, users_badges FROM users u WHERE u.id = :id", nativeQuery = true)
	Object[][] queryPointCrytalById(@Param("id") int id);
	
	@Query(value = "SELECT attendance, attendance_day FROM users u WHERE u.id = :id", nativeQuery = true)
	Object[][] queryAttendanceById(@Param("id") int id);
	
	
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
	@Query(value = "UPDATE users SET attendance_day = :attendance_day, attendance = :attendance, point = :point WHERE id = :id", nativeQuery = true)
	public void updateIsAttendance(@Param("attendance_day") String attendance_day, @Param("attendance") String attendance, @Param("point") int point, @Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET image = :image WHERE id = :id", nativeQuery = true)
	public void updateAvatar(@Param("image") String image, @Param("id") int id);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE users SET point = :point, crystal = :crystal, users_badges = :users_badges WHERE id = :id", nativeQuery = true)
	public void updateBadgesById(@Param("users_badges") String users_badges, @Param("id") int id, @Param("point") int point, @Param("crystal") int crystal);
}
