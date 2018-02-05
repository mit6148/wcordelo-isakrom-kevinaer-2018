package io.smartraise.service.impl;

import io.smartraise.dao.PaymentDAO;
import io.smartraise.model.accounts.Payment;
import io.smartraise.service.PaymentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Override
    public Payment get(String id) {
        if (paymentDAO.exists(id) ) {
            return paymentDAO.findOne(id);
        } else {
            return new Payment();
        }
    }

    @Override
    public boolean create(Payment payment) {
        if (isValid(payment)) {
            paymentDAO.save(payment);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean update(Payment payment) {
        if (isValid(payment)) {
            paymentDAO.save(payment);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(String id) {
        if (exists(id)) {
            paymentDAO.delete(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isValid(Payment payment) {
        return !(payment.getName().isEmpty()
                && payment.getZipCode().isEmpty()
                && payment.getNumber().isEmpty()
                && payment.getExpirationMonth().isEmpty()
                && payment.getExpirationYear().isEmpty()
                && payment.getCvv().isEmpty());
//        return true;
//        String[] dates = payment.getExpirationMonth().split("/");
//        return payment.getCvv().length() == 3
//                && Long.toString(payment.getNumber()).length() == 16
//                && !payment.getName().isEmpty()
//                && payment.getZipCode().length() == 5
//                && payment.getExpirationMonth().contains("/")
//                && (validDate(payment.getExpirationMonth()));
    }

    private boolean validMonth(String month) {
        return StringUtils.isNumeric(month)
                && Integer.valueOf(month) <= 12
                && Integer.valueOf(month) <= 0;
    }

    private boolean validYear(String year) {
        return StringUtils.isNumeric(year)
                && year.length() == 2;
    }

    private boolean validDate(String date) {
        String[] numbers = date.split("/");
        return validMonth(numbers[0]) && validYear(numbers[1]);
    }

    @Override
    public boolean exists(String id) {
        return paymentDAO.exists(id);
    }
}
