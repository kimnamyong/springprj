package com.example.shop.service;

import com.example.shop.dto.MailDto;
import com.example.shop.entity.Member;
import com.example.shop.repository.MemberRepository;
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


        //임시 비밀번호 생성
        public static String getTempPassword(){
            char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                    'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

            String str = "";

            // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
            int idx = 0;
            for (int i = 0; i < 10; i++) {
                idx = (int) (charSet.length * Math.random());
                str += charSet[idx];
            }
            return str;
        }

        // 메일 내용을 생성하고 임시 비밀번호로 회원 비밀번호를 변경
        public MailDto createMailAndChangePassword(String memberEmail) {
            String str = getTempPassword();
            MailDto dto = new MailDto();
            dto.setAddress(memberEmail);
            dto.setTitle("마이월드 임시비밀번호 안내 이메일 입니다.");
            dto.setMessage("안녕하세요. 마이월드 임시비밀번호 안내 관련 이메일 입니다." + " 회원님의 임시 비밀번호는 "
                    + str + " 입니다." + "로그인 후에 비밀번호를 변경해주세요!");
            updatePassword(str, memberEmail);
            return dto;
        }

        //MailDto를 바탕으로 실제 이메일 전송
        public void mailSend(MailDto mailDto) {
            System.out.println("전송 완료!");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mailDto.getAddress());
            message.setSubject(mailDto.getTitle());
            message.setText(mailDto.getMessage());
            message.setFrom("knyatom@gmail.com");
            message.setReplyTo("knyatom@daum.net");
            System.out.println("message : "+message);
            javaMailSender.send(message);
        }

        //임시 비밀번호로 업데이트
        public boolean updatePassword(String str, String email){
            try {
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                String encodePw = encoder.encode(str); // 패스워드 암호화
                Member member = memberRepository.findByEmail(email);
                member.updatePassword(encodePw);
                // member.setPassword(encodePw);
                memberRepository.save(member);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
}
