package com.task.musinsa.application

import com.task.musinsa.dto.*
import com.task.musinsa.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional(readOnly = true)
class ProductPriceService {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Autowired
    private lateinit var initProductCacheService: InitProductCacheService

    fun findLowestPriceProductStyle(): LowestPriceProductStyleTotalPriceResponse {
        val products = findAllCacheProducts()

        return LowestPriceProductStyleTotalPriceResponse(
            products.map { category ->
                val brand = category.findLowestPriceBrand()

                LowestPriceProductStyleResponse(
                    category.title,
                    brand.title,
                    brand.price
                )
            }.toList()
        )
    }

    private fun findAllCacheProducts() = findAllCategoryIds().map {
        initProductCacheService.findAllProducts(it)
    }.toList()

    private fun findAllCategoryIds(): List<Long> {
        return categoryRepository.findAll().map {
            it.id
        }.toList()
    }
}