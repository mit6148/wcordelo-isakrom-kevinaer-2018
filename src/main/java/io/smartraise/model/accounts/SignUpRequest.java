package io.smartraise.model.accounts;

import org.springframework.web.multipart.MultipartFile;

public class SignUpRequest {

    private Member member;
//    private MultipartFile image;
    private Payment payment;

    public SignUpRequest(Member member) {
        this.member = member;
        this.payment = new Payment();
        this.payment.setUsername(member.getUsername());
    }

    public SignUpRequest() {
        this.member = new Member();
        this.payment = new Payment();
        this.payment.setUsername(member.getUsername());
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

//    public MultipartFile getImage() {
//        return image;
//    }
//
//    public void setImage(MultipartFile image) {
//        this.image = image;
//    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
