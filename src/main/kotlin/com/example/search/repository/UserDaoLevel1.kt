package com.example.search.repository

import com.example.search.entity.User
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.*

@Service
class UserDaoLevel1 {

    @PersistenceContext
    private val entityManager: EntityManager? = null

    fun getUsers(params: List<SearchCriteria>): List<User> {
        val builder: CriteriaBuilder = entityManager?.criteriaBuilder!!
        val query: CriteriaQuery<User> = builder.createQuery(User::class.java)
        val root: Root<User> = query.from(User::class.java)
        var criteria: Predicate = builder.conjunction()

        params.forEach {
            if (it.operation == "is") {
                val parameterExpresion: ParameterExpression<User> = builder.parameter(User::class.java, it.key)
                criteria = builder.and(criteria, builder.equal(root.get<User>(it.key), it.value))
            }
            if (it.operation == "containing") {
                val parameterExpresion: ParameterExpression<User> = builder.parameter(User::class.java, it.key)
                criteria = builder.and(criteria, builder.like(root.get(it.key), "%" + it.value + "%"))
            }
//            query.select(root).where(criteria)
            query.where(criteria)
        }
        return entityManager.createQuery(query).resultList
    }
}

//https://chlebik.wordpress.com/2014/03/12/dajcie-kawalek-sqla-zapytania-criteria-api/
//https://www.baeldung.com/spring-data-criteria-queries
//https://www.baeldung.com/rest-search-language-spring-jpa-criteria