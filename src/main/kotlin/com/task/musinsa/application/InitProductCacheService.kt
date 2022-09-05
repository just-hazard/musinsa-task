package com.task.musinsa.application

import com.task.musinsa.dto.BrandResponse
import com.task.musinsa.dto.CategoryResponse
import com.task.musinsa.global.config.message.ErrorMessage
import com.task.musinsa.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional(readOnly = true)
class InitProductCacheService {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Cacheable(cacheNames = ["products"], key = "#id")
    fun findAllProducts(id: Long): CategoryResponse {

        val category = categoryRepository.findById(id).orElseThrow {
            EntityNotFoundException(ErrorMessage.NON_EXISTENT_CATEGORY)
        }

        return CategoryResponse(
            category.id,
            category.title,
            category.brands.map {
                BrandResponse(
                    it.id,
                    it.title,
                    it.price.price
                )
            }.toList()
        )
    }
}