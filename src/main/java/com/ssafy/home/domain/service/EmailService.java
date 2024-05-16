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

    public void sendEmailMessage(String id, String name) throws Exception {
        String email = authMapper.findByEmail(id, name);

        if(email != null) {
            MimeMessage message = emailSender.createMimeMessage();

            message.addRecipients(MimeMessage.RecipientType.TO, email); // 보낼 이메일 설정
            message.setSubject("[home] 비밀번호 찾기 안내"); // 이메일 제목
            message.setText(createHtml(id, name), "utf-8", "html"); // 내용 설정(Template Process)

            // 보낼 때 이름 설정하고 싶은 경우
            // message.setFrom(new InternetAddress([이메일 계정], [설정할 이름]));

            emailSender.send(message); // 이메일 전송
        }
    }

    private String createHtml(String id, String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("<div align=\"center\" style=\"font-size: 15px\">");
        sb.append("당신의 비밀번호는 ").append(authMapper.findByPass(id, name)).append("입니다.");
        return sb.toString();
    }
}