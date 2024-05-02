package com.jpa4.controller;

import com.jpa4.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JPQLController {
    @Autowired
    JpaContext jpaContext;

    @RequestMapping("/index2")
    public String index2() {
        return "index2";
    }

    @RequestMapping("execute")
    public @ResponseBody Object jpql(@RequestParam("jpql") String jpql) {
        EntityManager manager = jpaContext.getEntityManagerByManagedType(Student.class);
        Query query = manager.createQuery(jpql);
        Object r = query.getResultList();
        return r;
    }

}
