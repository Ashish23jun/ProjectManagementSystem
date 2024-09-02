package com.ashley.projectmanagementsystem.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashley.projectmanagementsystem.Model.PlanType;
import com.ashley.projectmanagementsystem.Model.Subscription;
import com.ashley.projectmanagementsystem.Model.User;
import com.ashley.projectmanagementsystem.Repository.SubscriptionRepository;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private UserService userService;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription createSubscription(User user) {

        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setGetSubscriptionStartDate(LocalDate.now());
        subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
        subscription.setValid(true);
        subscription.setPlanType(PlanType.FREE);

        return subscriptionRepository.save(subscription);

    }

    @Override
    public Subscription getUsersSubscription(Long userId) throws Exception {

        Subscription subscription = subscriptionRepository.findByUserId(userId);
        if (!isValid(subscription)) {
            subscription.setPlanType(PlanType.FREE);
            subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));
            subscription.setGetSubscriptionStartDate(LocalDate.now());
            subscriptionRepository.save(subscription);
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public Subscription updateSubscription(Long userId, PlanType planType) {
        Subscription subscription = subscriptionRepository.findByUserId(userId);
        subscription.setPlanType(planType);
        subscription.setGetSubscriptionStartDate(LocalDate.now());
        if (planType.equals(PlanType.ANNUALLY)) {
            subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(12));

        } else {
            subscription.setGetSubscriptionEndDate(LocalDate.now().plusMonths(1));
        }
        return subscriptionRepository.save(subscription);
    }

    @Override
    public boolean isValid(Subscription subscription) {
        if (subscription.getPlanType().equals(PlanType.FREE)) {
            return true;
        }
        LocalDate endDate = subscription.getGetSubscriptionEndDate();
        LocalDate currenDate = LocalDate.now();

        return endDate.isAfter(currenDate) || endDate.isEqual(currenDate);
    }

}
