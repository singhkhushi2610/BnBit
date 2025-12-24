package com.projects.BnBit.service;

import com.projects.BnBit.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}
