package com.jwt.SpringSecurityJWT.controller;

import com.jwt.SpringSecurityJWT.dto.ReqRes;
import com.jwt.SpringSecurityJWT.entity.Product;
import com.jwt.SpringSecurityJWT.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminUsers {

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/public/products")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(productRepo.findAll());
    }

    @PostMapping("/admin/saveproduct")
    public ResponseEntity<Object> signUp(@RequestBody ReqRes productRequest){
        Product productTosave = new Product();
        productTosave.setName(productRequest.getName());
        return ResponseEntity.ok(productRepo.save(productTosave));
    }

    @GetMapping("/user/alone")
    public ResponseEntity<Object> userAlone (){
        return ResponseEntity.ok("Users alone can access this API only");
    }

    @GetMapping("/adminuser/alone")
    public ResponseEntity<Object> bothAdminaAndUsersApi(){
        return ResponseEntity.ok("Both Admins and Users can access this API");
    }

    /* get the details(name,email,role,ip, e.t.c) of user accessing the service*/
    @GetMapping("/public/email")
    public String getCurrentUserEmail(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(authentication); //get all details
        System.out.println(authentication.getDetails()); //get remote ip address
        System.out.println(authentication.getName());
        return authentication.getName(); //return email
    }
}


