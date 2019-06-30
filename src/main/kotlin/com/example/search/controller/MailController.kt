package com.example.search.controller

import com.example.search.service.MailService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/mail")
class MailController(val mailService: MailService) {

    @GetMapping
    fun sendMail() = mailService.sendMail()
}