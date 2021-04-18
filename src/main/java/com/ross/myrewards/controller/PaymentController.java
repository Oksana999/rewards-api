package com.ross.myrewards.controller;

import com.ross.myrewards.model.Payment;
import com.ross.myrewards.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("get-all")
    public List<Payment> getPayment() {
        return paymentRepository.findAll();
    }

}
