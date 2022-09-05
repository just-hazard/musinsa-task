package com.task.musinsa.repositories

import com.task.musinsa.domain.Category
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CategoryRepository : JpaRepository<Category, Long> {
    fun findByTitle(categoryName: String): Optional<Category>
}