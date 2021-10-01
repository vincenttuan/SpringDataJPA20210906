package com.spring.mvc.single_user.controller;

import com.github.javafaker.Faker;
import com.spring.mvc.single_user.entities.User;
import com.spring.mvc.single_user.entities.UserView;
import com.spring.mvc.single_user.repository.UserRepository;
import com.spring.mvc.single_user.repository.UserViewRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserViewRepository userViewRepository;
    
    // uri: /mvc/user/name?name=Simonis
    @GetMapping(value = {"/name"})
    @ResponseBody
    public String getByName(@RequestParam("name") String name) {
        return userRepository.getByName(name) + "";
    }
    
    // uri: /mvc/user/name/id/S/150
    @GetMapping(value = {"/name/id/{name}/{id}"})
    @ResponseBody
    public String getByNameID(@PathVariable("name") String name,
                              @PathVariable("id") Long id) {
        return userRepository.getByNameStartingWithAndIdLessThan(name, id) + "";
        //return userRepository.getByNameStartingWithAndIdGreaterThan(name, id) + "";
    }
    
    // uri: /mvc/user/ids/birth?ids=5,10,15&birth=2020-12-18
    @GetMapping(value = {"/ids/birth"})
    @ResponseBody
    public String getByIdAndBirth(@RequestParam("ids") List<Long> ids,
                                  @RequestParam("birth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birth) {
        return userRepository.getByIdInOrBirthLessThan(ids, birth) + "";
        //return ids + ", " + birth;
    }
    
    // uri: /mvc/user/birth?begin=2001-1-1&end=2020-12-18
    @GetMapping(value = {"/birth"})
    @ResponseBody
    public String getByBirth(@RequestParam("begin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date begin,
                             @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
        return userRepository.getByBirthGreaterThanEqualAndBirthLessThanEqual(begin, end) + "";
    }
    
    // uri: /mvc/user/name?name=Simonis
    @GetMapping(value = {"/maxid"})
    @ResponseBody
    public String getByMaxId() {
        return userRepository.getMaxIdUser() + "";
    }
    
    // uri: /mvc/user/age/40
    @GetMapping(value = {"/age/{age}"})
    @ResponseBody
    public String getUserByAge(@PathVariable("age") Integer age) {
        return userRepository.getUserByAge(age)+ "";
    }
    
    // User / UserView 分頁查詢
    @GetMapping(value = {"/page/{no}"})
    public String pagingUsers(Model model, @PathVariable Optional<Integer> no) {
        int pageNo = 0;
        int pageSize = 10;
        if(no.isPresent()) {
            pageNo = no.get();
        }
        // 排序
        Sort.Order order1 = new Sort.Order(Sort.Direction.ASC, "name");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort sort = new Sort(order1, order2);
        
        // 分頁請求
        PageRequest pageRequest = new PageRequest(pageNo, pageSize, sort);
        //Page<User> page = userRepository.findAll(pageRequest);
        Page<UserView> page = userViewRepository.findAll(pageRequest);
        model.addAttribute("users", page.getContent());
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("pages", page.getTotalPages());
        
        return "single_user/user_paging";
    }
    
    @GetMapping(value = {"/", "/{id}", "/{delete}/{id}"})
    public String read(Model model, 
            @PathVariable Optional<Long> id,
            @PathVariable Optional<String> delete) {
        User user = new User();
        String _method = "POST";
        if(id.isPresent()) {
            user = userRepository.findOne(id.get());
            if(delete.isPresent() && delete.get().equalsIgnoreCase("delete")) {
                _method = "DELETE";
            } else {
                _method = "PUT";
            }
        }
        model.addAttribute("_method", _method);
        model.addAttribute("user", user);
        model.addAttribute("users", userRepository.findAll());
        return "single_user/user";
    }
    
    @GetMapping(value = {"/auto"})
    @ResponseBody
    public String auto() {
        Faker faker = new Faker();
        for (int i = 0; i < 150; i++) {
            User user = new User();
            user.setName(faker.name().lastName());
            user.setPassword(new Random().nextInt(9000) + 1000 + "");
            user.setBirth(faker.date().birthday());
            userRepository.save(user);
        }
        return "auto";
    }
    
    @RequestMapping(method = {RequestMethod.POST}, value = {"/"})
    public String create(User user) {
    	System.out.println(user);
        userRepository.save(user);
        return "redirect: ./";
    }
    
    @RequestMapping(method = {RequestMethod.PUT}, value = {"/"})
    public String update(@ModelAttribute("user") User user) {
        userRepository.saveAndFlush(user);
        return "redirect: ./";
    }
    
    @RequestMapping(method = {RequestMethod.DELETE}, value = {"/"})
    public String delete(@ModelAttribute("user") User user) {
        userRepository.delete(user.getId());
        return "redirect: ./";
    }
}
