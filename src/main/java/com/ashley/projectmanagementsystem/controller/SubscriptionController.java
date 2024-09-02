package com.ashley.projectmanagementsystem.controller;

import com.ashley.projectmanagementsystem.Model.PlanType;
import com.ashley.projectmanagementsystem.Model.Subscription;
import com.ashley.projectmanagementsystem.Model.User;
import com.ashley.projectmanagementsystem.service.SubscriptionService;
import com.ashley.projectmanagementsystem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private UserService userService;

    public SubscriptionController(SubscriptionService subscriptionService, UserService userService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription createdSubscription = subscriptionService.createSubscription(user);
        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Subscription> getUserSubscription(
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription subscription = subscriptionService.getUsersSubscription(user.getId());
        return new ResponseEntity<>(subscription, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Subscription> updateSubscription(
            @RequestHeader("Authorization") String jwt,
            @RequestBody PlanType planType) throws Exception {
        User user = userService.findUserProfileByJwt(jwt);
        Subscription updatedSubscription = subscriptionService.updateSubscription(user.getId(), planType);
        return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
    }
}
