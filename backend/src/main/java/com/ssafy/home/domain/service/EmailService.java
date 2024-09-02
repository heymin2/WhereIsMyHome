package com.ssafy.home.domain.service;

import com.ssafy.home.domain.mapper.AuthMapper;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    @Autowired
    private final JavaMailSender emailSender;

    @Autowired
    private final AuthMapper authMapper;

    public boolean sendEmailMessage(String id, String name) throws Exception {
        String email = authMapper.findByEmail(id, name);

        if(email != null) {
            MimeMessage message = emailSender.createMimeMessage();

            message.addRecipients(MimeMessage.RecipientType.TO, email); // 보낼 이메일 설정
            message.setSubject("[개집] 비밀번호 찾기 안내"); // 이메일 제목
            message.setText(createHtml(id, name), "utf-8", "html"); // 내용 설정(Template Process)

            // 보낼 때 이름 설정하고 싶은 경우
            // message.setFrom(new InternetAddress([이메일 계정], [설정할 이름]));

            emailSender.send(message); // 이메일 전송

            return true;
        }
        return false;
    }

    private String createHtml(String id, String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div style=\"width: 100%; background-color: #f3f4f6; padding: 20px; font-family: Arial, sans-serif;\">");
        sb.append("<div style=\"max-width: 600px; margin: auto; background-color: #ffffff; border-radius: 10px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); overflow: hidden;\">");
        sb.append("<div style=\"background-color: #4CAF50; padding: 10px; text-align: center; color: #ffffff;\">");
        sb.append("<h1 style=\"margin: 0; font-size: 24px;\">비밀번호 찾기</h1>");
        sb.append("</div>");
        sb.append("<div style=\"padding: 20px; text-align: center;\">");
        sb.append("<p style=\"font-size: 18px; color: #333333;\">안녕하세요, ").append(name).append("님!</p>");
        sb.append("<p style=\"font-size: 16px; color: #555555;\">요청하신 비밀번호는 아래와 같습니다:</p>");
        sb.append("<p style=\"font-size: 20px; font-weight: bold; color: #333333;\">").append(authMapper.findByPass(id, name)).append("</p>");
        sb.append("<a href=\"http://localhost:5173\" style=\"display: inline-block; margin-top: 20px; padding: 10px 20px; font-size: 16px; color: #ffffff; background-color: #4CAF50; text-decoration: none; border-radius: 5px;\">로그인 하러 가기</a>");
        sb.append("</div>");
        sb.append("<div style=\"background-color: #f9f9f9; padding: 10px; text-align: center; font-size: 12px; color: #aaaaaa;\">");
        sb.append("<p style=\"margin: 0;\">© 2024 hyung-hye. All rights reserved.</p>");
        sb.append("</div>");
        sb.append("</div>");
        sb.append("</div>");
        return sb.toString();

    }
}