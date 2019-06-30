package com.example.search.repository

import com.example.search.entity.User
import java.util.function.Consumer
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class UserSearchQueryCriteriaConsumer(
        var predicate: Predicate?,
        var builder: CriteriaBuilder?,
        var root: Root<User>?
) : Consumer<SearchCriteria> {

    override fun accept(param: SearchCriteria) {
        if (param.operation == ":") {
            predicate = builder?.and(predicate, builder?.equal(root?.get<User>(param.key), param.value))
        }
    }
}

//https://www.baeldung.com/rest-search-language-spring-jpa-criteria