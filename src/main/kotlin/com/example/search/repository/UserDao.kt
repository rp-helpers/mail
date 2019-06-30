package com.example.search.repository

import com.example.search.entity.User
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

@Service
class UserDao {

    @PersistenceContext
    private val entityManager: EntityManager? = null

    fun receiveResult(params: List<SearchCriteria>): List<User> {
        val builder: CriteriaBuilder = entityManager!!.criteriaBuilder
        val query: CriteriaQuery<User> = builder.createQuery(User::class.java)
        val root: Root<User> = query.from(User::class.java)
        var pred: Predicate = builder.conjunction()
        val searchConsumer: UserSearchQueryCriteriaConsumer = UserSearchQueryCriteriaConsumer(pred, builder, root)
        params.forEach(searchConsumer)
        pred = searchConsumer.predicate!!
        query.where(pred)
        return entityManager.createQuery(query).resultList
    }
}
