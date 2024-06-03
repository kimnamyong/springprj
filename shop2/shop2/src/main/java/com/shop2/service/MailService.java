package com.shop2.service;

import com.shop2.repository.MemberRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {

    @Autowired
    MemberRepository memberRepository;

    private final JavaMailSender javaMailSender;

    private static final String senderEmail="knyatom@gmail.com";

    private static int number;

    public static void createNumber(){
        number = (int)(Math.random() * (90000)) + 100000;
        // (int) Math.random() * (최댓값-최소값+1) + 최소값
    }

    public MimeMessage CreateMail(String mail){
        createNumber();

        log.info("mail:{}",mail);

        MimeMessage message = javaMailSender.createMimeMessage();
        //  MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("이메일 인증");
            String body = "";
            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h1>" + number + "</h1>";
            body += "<h3>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return message;
    }

    public int sendMail(String mail){
        MimeMessage message = CreateMail(mail);
        log.info("message:{}",message.toString());
        javaMailSender.send(message);

        return number;
    }



}
