package com.blog.service;

import com.blog.model.Board;
import com.blog.model.Reply;
import com.blog.model.RoleType;
import com.blog.model.User;
import com.blog.repository.BoardRepository;
import com.blog.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
// @RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository;


    // @Transactional
    // public int 회원가입(User user){
    //     try {
    //         userRepository.save(user);
    //         return 1;
    //     }catch (Exception e){
    //         e.printStackTrace();
    //         System.out.println("UserService: 회원가입(): " + e.getMessage());
    //     }
    //     return -1;
    // }

    @Autowired
    private PasswordEncoder encoder;

    // private final PasswordEncoder encoder;

    // public UserService(PasswordEncoder encoder) {
    //     this.encoder = encoder;
    // }


    @Transactional
    public int 회원가입(User user) {
        try {
            String rawPassword = user.getPassword();
            String encPassword = encoder.encode(rawPassword); // 해쉬

            user.setPassword(encPassword);
            user.setRole(RoleType.USER);
            userRepository.save(user);

            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


    @Transactional(readOnly = true)
    public User 로그인(User user) {
        User principal = userRepository.findByUsernameAndPassword(user.getUsername(),user.getPassword());

        return principal;
    }

    public int 중복확인(String username) {
        // User user=userRepository.findByUsername(username);
        Optional<User> user=userRepository.findByUsername(username);
        if(user==null) return 1;
        else return 0;
    }

    @Autowired
    HttpSession session;
    @Transactional
    public void 회원수정(User user) {
        // 수정시에는 영속성 컨텍스트 User오브젝트를 영속화시키고, 영속화된 User오브젝트를 수정한다.
        // select 해서 User 오브젝트를 DB로 부터 가져오는 이유는 영속화를 하기 위해서이다.
        // 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다.
        User persistance=userRepository.findById(user.getId()).orElse(null);
        persistance.setPassword(user.getPassword());
        persistance.setEmail(user.getEmail());
        // 회원수정 함수 종료시=서비스 종료=트랙잭션종료=commit이 자동으로 된다.
        // 영속화된 persistance객체의 변화가 감지되면 더티체킹이 되어 자동으로 update문을 날려준다.

        session.setAttribute("principal",persistance);
    }

    @Transactional
    public int 회원탈퇴(Integer id, User user) {
        User user2=userRepository.findById(id).orElse(null);
        if(user2 != null) {
            User user3 = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            if(user3 != null){
                boardRepository.deleteByUserId(user3.getId());
                userRepository.delete(user3);
                return 1;
            }
        }
        return -1;
    }

}
