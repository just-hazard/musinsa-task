package com.task.musinsa.application

import com.task.musinsa.domain.Brand
import com.task.musinsa.domain.Price
import com.task.musinsa.global.config.message.ErrorMessage
import com.task.musinsa.repositories.BrandRepository
import com.task.musinsa.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Service
@Transactional
class BrandService {

    @Autowired
    private lateinit var brandRepository: BrandRepository

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @CacheEvict(cacheNames = ["products"], allEntries = true)
    fun createBrand(title: String, price: Int, categoryId: Long): Long {
        val category = categoryRepository.findById(categoryId).orElseThrow {
            EntityNotFoundException(ErrorMessage.NON_EXISTENT_CATEGORY)
        }

        return brandRepository.save(
            Brand(
                title,
                Price(price),
                category
            )
        ).id!!
    }

    @CacheEvict(cacheNames = ["products"], allEntries = true)
    fun updateBrand(id: Long, title: String, price: Int) {
        var brand = findBrand(id)
        brand.update(title, price)
    }

    @CacheEvict(cacheNames = ["products"], allEntries = true)
    fun deleteBrand(id: Long) {
        val brand = findBrand(id)
        brandRepository.delete(brand)
    }

    private fun findBrand(id: Long) = brandRepository.findById(id).orElseThrow {
        EntityNotFoundException(ErrorMessage.NON_EXISTENT_BRAND)
    }
}