package com.spring.mvc.single.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.javafaker.Faker;
import com.spring.mvc.single.entity.User;
import com.spring.mvc.single.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	// 新增範例資料
	@GetMapping("/test/create_sample_data")
	@ResponseBody
	public String testCreateSampleData() {
		Random r = new Random();
		Faker faker = new Faker();
		int count = 150;
		for (int i = 0; i < count; i++) {
			User user = new User();
			user.setName(faker.name().lastName());
			user.setPassword(String.format("%04d", r.nextInt(10000)));
			user.setBirth(faker.date().birthday());
			// 儲存到資料庫
			userRepository.saveAndFlush(user);
		}
		return "Create sample data ok !";
	}

	// 查詢範例資料 1
	@GetMapping("/test/findall")
	@ResponseBody
	public List<User> testFindall() {
		List<User> users = userRepository.findAll();
		return users;
	}

	// 查詢範例資料 2
	@GetMapping("/test/findall_sort")
	@ResponseBody
	public List<User> testFindallSort() {
		// ASC 自然排序 小->大, DESC 大->小
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		List<User> users = userRepository.findAll(sort);
		return users;
	}

	// 查詢範例資料 3
	@GetMapping("/test/findall_ids")
	@ResponseBody
	public List<User> testFindallIds() {
		// 查找指定 id 的資料
		Iterable<Long> ids = Arrays.asList(1L, 3L, 5L);
		List<User> users = userRepository.findAll(ids);
		return users;
	}

	// 查詢範例資料 4
	@GetMapping("/test/findall_example")
	@ResponseBody
	public List<User> testFindallExample() {
		User user = new User();
		user.setId(2L);
		user.setPassword("2590");
		// 根據 Example 所提供的實例(例如：user)來查詢
		Example<User> example = Example.of(user);
		List<User> users = userRepository.findAll(example);
		return users;
	}

	// 查詢範例資料 5
	@GetMapping("/test/findall_example2")
	@ResponseBody
	public List<User> testFindallExample2() {
		User user = new User();
		user.setName("a");
		// 欄位 name 的內容是否有包含 "a"
		// 建立 ExampleMatcher 比對器
		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name",
				ExampleMatcher.GenericPropertyMatchers.contains());
		Example<User> example = Example.of(user, matcher);
		List<User> users = userRepository.findAll(example);
		return users;
	}

	// 查詢範例資料 6
	@GetMapping("/test/find_one")
	@ResponseBody
	public User findOne() {
		return userRepository.findOne(3L);
	}

	// 查詢分頁
	@GetMapping("/test/page/{no}")
	@ResponseBody
	public List<User> testPage(@PathVariable("no") Integer no) {
		int pageNo = no;
		int pageSize = 10;
		// 排序
		Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "name");
		Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "id");
		Sort sort = new Sort(order1, order2);

		// 分頁請求
		PageRequest pageRequest = new PageRequest(pageNo, pageSize, sort);
		Page<User> page = userRepository.findAll(pageRequest);
		return page.getContent();
	}

	@GetMapping("/test/name")
	@ResponseBody
	public List<User> getByName(@RequestParam("name") String name) {
		return userRepository.getByName(name);
	}

	// 測試 url: /mvc/user/test/name/id/S/50
	@GetMapping("/test/name/id/{name}/{id}")
	@ResponseBody
	public List<User> getByNameAndID(@PathVariable("name") String name, @PathVariable("id") Long id) {
		return userRepository.getByNameStartingWithAndIdGreaterThanEqual(name, id);
	}

	// 測試 url: /mvc/user/test/ids?ids=5,10,15
	@GetMapping("/test/ids")
	@ResponseBody
	public List<User> getByIds(@RequestParam("ids") List<Long> ids) {
		return userRepository.getByIdIn(ids);
	}

	// 測試 url: /mvc/user/test/birth?birth=1965-9-9
	@GetMapping("/test/birth")
	@ResponseBody
	public List<User> getByBirthLessThan(@RequestParam("birth") @DateTimeFormat(iso = ISO.DATE) Date birth) {
		return userRepository.getByBirthLessThan(birth);
	}

}
