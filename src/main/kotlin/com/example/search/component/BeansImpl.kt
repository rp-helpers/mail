package com.example.search.component

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.mail.MailSender
import org.springframework.mail.javamail.JavaMailSenderImpl

@Configuration
class BeansImpl {

    @Bean
    fun mailSender(env: Environment): MailSender {
        val mailSender: JavaMailSenderImpl = JavaMailSenderImpl()
        mailSender.host = "smtp.gmail.com"
        mailSender.port = 587
        mailSender.username="mail"
        mailSender.password="password"
        var props = mailSender.javaMailProperties
        props.put("mail.transport.protocol", "smtp")
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.debug", "true");
        return mailSender
    }
}