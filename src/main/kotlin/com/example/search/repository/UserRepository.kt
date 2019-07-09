package com.example.search.repository

import com.example.search.entity.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long>, JpaSpecificationExecutor<User>/*, PagingAndSortingRepository<User, Long>*/ {
    fun findUserByEmail(email: String): Optional<User>
    override fun findAll(pageable: Pageable): Page<User>
    override fun findAll(spec: Specification<User>?, pageable: Pageable): Page<User>




}