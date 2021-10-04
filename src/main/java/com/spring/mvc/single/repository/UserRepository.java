package com.spring.mvc.single.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.mvc.single.entity.User;

/*
 * 方法命名規則
 * 1. 查詢方法以 find | read | get 開頭
 * 2. 涉及條件查詢時，條件的屬性（首字母大寫）用條件關鍵字（例如：And、Or）連結
 * */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// 根據 name 來取得 User
	List<User> getByName(String name);
	
	// Where name LIKE ? AND id >= ?
	List<User> getByNameStartingWithAndIdGreaterThanEqual(String name, Long id);
	
	
}
