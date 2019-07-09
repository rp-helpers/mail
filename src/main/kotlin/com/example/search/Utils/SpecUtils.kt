package com.example.search.Utils

import com.example.search.entity.User
import com.example.search.repository.SearchCriteria
import org.apache.commons.lang3.StringUtils
import org.springframework.data.jpa.domain.Specification
import javax.persistence.criteria.Predicate

class SpecUtils {
    companion object {
        fun getSpecification(search: String?): Specification<User> {

            val params: MutableList<SearchCriteria> = mutableListOf()
            search?.let {
                val operation = StringUtils.substringBetween(search, ":", ":")
                val key = StringUtils.substringBefore(search, ":$operation:")
                val value = StringUtils.substringAfter(search, ":$operation:")
                println("operation: $operation")
                println("key: $key")
                println("value: $value")
                params.add(SearchCriteria(key, operation, value))

            }
            val specification: Specification<User> = Specification<User> { root, _, builder ->
                val predicates: MutableList<Predicate> = mutableListOf()
                params.forEach {
                    if (it.operation == "is")
                        predicates.add(builder.equal(root.get<User>(it.key), it.value))
                }
                builder.and(*predicates.toTypedArray())
            }
            return specification
        }
    }

}

//https://github.com/consoleau/kotlin-jpa-specification-dsl/blob/master/src/main/kotlin/au/com/console/jpaspecificationdsl/JPASpecificationDSL.kt
//https://stackoverflow.com/questions/43447687/spring-specification-and-pageable
//https://docs.spring.io/spring-data/rest/docs/2.0.0.M1/reference/html/paging-chapter.html
//localhost:8080/users/test?search=email:is:email_No_3@op.pl
//http://localhost:8080/users/test?sort=priority
//https://stackoverflow.com/questions/10527124/how-to-query-data-out-of-the-box-using-spring-data-jpa-by-both-sort-and-pageable
//http://localhost:8080/users/test?sort=priority&sort=email


// var criteria: Predicate = builder.conjunction()
//
//        params.forEach {
//            if (it.operation == "is") {
//                val parameterExpresion: ParameterExpression<User> = builder.parameter(User::class.java, it.key)
//                criteria = builder.and(criteria, builder.equal(root.get<User>(it.key), it.value))