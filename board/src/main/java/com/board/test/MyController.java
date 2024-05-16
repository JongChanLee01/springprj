package com.board.test;

import com.board.dto.MyData;
import com.board.dto.UserForm;
import com.board.entity.UserEntity;
import com.board.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

@Slf4j
@Controller
public class MyController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/hellotest")
    public String hello(@RequestParam(name = "name", required = false, defaultValue = "Guest") String name,
                        Model model) {
        String message = "Hello, " + name + "!";
        model.addAttribute("message", message);
        return "hello"; // hello.html 뷰를 반환
        // localhost:8088/hellotest
    }


    @GetMapping("/hellotest2")
    public String hello2(Model model) {
        String name = "Welcome";
        String message = "Hello, ";
        // String[] fruits={"사과","배","바나나","망고"};
        ArrayList<String> fruits=new ArrayList<>();
        fruits.add("사과");
        fruits.add("배");
        fruits.add("바나나");

        model.addAttribute("message", message);
        model.addAttribute("name", name);
        model.addAttribute("fruits", fruits);
        log.info("과일 : " +fruits);
        return "hello2"; // hello2.html 뷰를 반환
    }


    @GetMapping("/redirect")
    public String redirectToAnotherPage(RedirectAttributes redirectAttributes) {
        // 플래시 속성 추가
        // addAttribute는 값을 지속적으로 사용해야할때
        // addFlashAttribute는 일회성으로 사용해야할때 사용
        redirectAttributes.addFlashAttribute("message", "This is a flash message!");

        // 리다이렉트할 URL 리턴
        return "redirect:/anotherPage";
    }

    @GetMapping("/anotherPage")
    public String showAnotherPage(@ModelAttribute("message") String message) {
        // 리다이렉트된 페이지에서 플래시 속성을 사용
        System.out.println("Flash Message: " + message);
        return "anotherPage";     // anotherPage.mustache
    }

    @PostMapping("/api/person")
    public ResponseEntity<Person> person(@RequestBody Person person){
        return ResponseEntity.status(HttpStatus.OK).body(person);
    }
    // http://localhost:8088/api/person
    // {
    //     "name":"이순신",
    //         "age":10
    // }




    // user
    @PostMapping("/api/save")
    // public String saveUser(@RequestBody UserEntity user){
    public ResponseEntity saveUser(@RequestBody UserEntity user){
        // 받아온 User 객체를 이용하여 데이터 저장 또는 처리 등의 작업을 수행
        // return "User saved: " + user;
        return ResponseEntity.status(HttpStatus.OK).body("User saved:"+user);
    }


    @GetMapping("/data/{id}")
    public ResponseEntity<String> getData(@PathVariable Long id) {
        if (id == 1L) {
            return ResponseEntity.ok("Data with ID 1 found");
        } else {
            return ResponseEntity.notFound().build();
        }
        //return ResponseEntity.status(HttpStatus.OK);
    }

    @PostMapping("/api/create")
    public ResponseEntity<String> create(@RequestBody MyData myData) {
        log.info("myData : " + myData);
        return ResponseEntity.ok("Data created successfully");
    }


    @PostMapping("/api/processForm")
    public String process(@RequestBody UserForm user,RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("userTest",user);

        log.info("user:" + user);
        return "redirect:/api/processForm";
    }

    @GetMapping("/api/processForm")
    // @ModelAttribute는 HTTP Body 내용과 HTTP 파라미터의 값들을 Getter, Setter, 생성자를 통해 주입하기 위해 사용한다.
    // 일반 변수의 경우 전달이 불가능하기 때문에 model 객체를 통해서 전달해야 한다.
    public ResponseEntity<String> processForm(@ModelAttribute("userTest") UserForm userTest) {
        log.info("userTest : " + userTest.toString());
        String result= userTest.getUsername()+" " + userTest.getPassword();
        log.info("result : " + result);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/usersTest/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
        // id에 해당하는 사용자를 데이터베이스에서 조회하고 user 변수에 저장하는 로직을 가정합니다.
        UserEntity user = userRepository.findById(id).orElse(null);

        if (user != null) {
            // 사용자가 존재하는 경우 200 OK 상태 코드와 사용자 객체를 응답으로 반환
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            // 사용자가 존재하지 않는 경우 404 Not Found 상태 코드를 반환
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
