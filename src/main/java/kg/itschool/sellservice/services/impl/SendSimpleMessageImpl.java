package kg.itschool.sellservice.services.impl;

import kg.itschool.sellservice.services.SendSimpleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendSimpleMessageImpl implements SendSimpleMessage {

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendSimpleMessage(String to, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("adilets988@gmail.com");
        mailMessage.setTo(to);
        mailMessage.setText("Ваш код подтверждения: " + text + " Никому не передавайте");
        javaMailSender.send(mailMessage);
    }
}
