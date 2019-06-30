package com.example.search.controller

import com.example.search.entity.User
import com.example.search.repository.SearchCriteria
import com.example.search.repository.UserDao
import com.example.search.service.UserService
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.regex.Matcher
import java.util.regex.Pattern

@RequestMapping("/users")
@RestController
class UserController(val userService: UserService,
                     val userDao: UserDao) {

    @GetMapping
    fun getUsers() = userService.getUsers()

    @GetMapping("/page")
    fun getUsersPages(pageable: Pageable) = userService.getUsersPage(pageable)

    @GetMapping("/page2")
    fun getUsersPages2(@RequestParam(required = false, defaultValue = "0") page: Int, @RequestParam(required = false, defaultValue = "10") size: Int) =
            userService.getUsersPage(PageRequest.of(page, size))

    @GetMapping("/search")
    fun getUsersSearch(@RequestParam(required = false) search: String?): List<User> {
        val params: MutableList<SearchCriteria> = mutableListOf()
        search?.let {
            val pattern: Pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?)")
            val matcher: Matcher = pattern.matcher(search)
            while (matcher.find()) {
                params.add(SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)))
            }
        }
        val paramsList: List<SearchCriteria> = params
        return userDao.receiveResult(paramsList)
    }
}