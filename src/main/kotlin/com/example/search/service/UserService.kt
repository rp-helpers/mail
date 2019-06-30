package com.example.search.service

import com.example.search.repository.UserRepository
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun getUsers() = userRepository.findAll()
    fun getUsersPage(pageable: Pageable) = userRepository.findAll(pageable)
}