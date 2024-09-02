package com.ashley.projectmanagementsystem.service;

import com.ashley.projectmanagementsystem.Model.PlanType;
import com.ashley.projectmanagementsystem.Model.Subscription;
import com.ashley.projectmanagementsystem.Model.User;

public interface SubscriptionService {
    Subscription createSubscription(User user);

    Subscription getUsersSubscription(Long userId) throws Exception;

    Subscription updateSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
