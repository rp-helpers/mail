package com.example.search.repository

data class SearchCriteria(
        val key: String,
        val operation: String,
        val value: String
)