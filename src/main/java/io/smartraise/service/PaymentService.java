package io.smartraise.service;

import io.smartraise.model.accounts.Payment;
import io.smartraise.service.skeleton.CrudService;
import io.smartraise.service.skeleton.ValidationService;

public interface PaymentService extends CrudService<Payment>, ValidationService<Payment> {
}
