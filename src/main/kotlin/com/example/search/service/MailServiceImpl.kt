package com.example.search.service

import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.stereotype.Service

@Service
class MailServiceImpl(val mailSender: MailSender) : MailService {

    override fun sendMail() {
        var message = SimpleMailMessage()
        message.setFrom("mail")
        message.setTo("mail")
        message.setSubject("wiadomość")
        message.setText("treść wiadomości")
        mailSender.send(message)
    }
}