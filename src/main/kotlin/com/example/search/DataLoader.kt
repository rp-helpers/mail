package com.example.search

import com.example.search.entity.User
import com.example.search.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataLoader @Autowired constructor(val userRepository: UserRepository) : ApplicationRunner {

    @Transactional
    override fun run(args: ApplicationArguments?) {
        var userNo1: User = User(null, "email_No_1@op.pl", "1234", Priority.NORMAL)
        var userNo2: User = User(null, "email_No_2@op.pl", "1234", Priority.NORMAL)
        var userNo3: User = User(null, "email_No_3@op.pl", "1234", Priority.NORMAL)
        var userNo4: User = User(null, "drjsk", "1234", Priority.HIGH)
        var userNo5: User = User(null, "stkysk@op.pl", "1234", Priority.HIGH)
        var userNo6: User = User(null, "dyksxms@op.pl", "1234", Priority.HIGH)
        var userNo7: User = User(null, "x@op.pl", "1234", Priority.HIGH)
        var userNo8: User = User(null, "tk@op.pl", "1234", Priority.NORMAL)
        var userNo9: User = User(null, "xdt,ktdymtm@op.pl", "1234", Priority.NORMAL)
        var userNo10: User = User(null, "dx,tmdhd@op.pl", "1234", Priority.NORMAL)
        var userNo11: User = User(null, "xtmxmtmyy@op.pl", "1234", Priority.NORMAL)

        userRepository.save(userNo1)
        userRepository.save(userNo2)
        userRepository.save(userNo3)
        userRepository.save(userNo4)
        userRepository.save(userNo5)
        userRepository.save(userNo6)
        userRepository.save(userNo7)
        userRepository.save(userNo8)
        userRepository.save(userNo9)
        userRepository.save(userNo10)
        userRepository.save(userNo11)
    }
}