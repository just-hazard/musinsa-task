package com.task.musinsa.application

import com.task.musinsa.dto.CategoryResponse
import com.task.musinsa.dto.LowestPriceBrandResponse
import com.task.musinsa.dto.LowestPriceProductStyleResponse
import com.task.musinsa.dto.LowestPriceProductStyleTotalPriceResponse
import com.task.musinsa.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

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

    fun findLowestPriceBrand(): LowestPriceBrandResponse {
        val products = findAllCacheProducts()
        val result = findLowestPriceInOneBrand(products)

        return LowestPriceBrandResponse(
            result.key,
            result.value
        )
    }

    private fun findLowestPriceInOneBrand(products: List<CategoryResponse>): Map.Entry<String, Int> {
        return products.flatMap {
            it.brands
        }.groupBy {
            it.title
        }.entries.associate {
            it.key to
            it.value.sumOf { brandResponse ->
                brandResponse.price
            }
        }.minByOrNull {
            it.value
        }!!
    }
}