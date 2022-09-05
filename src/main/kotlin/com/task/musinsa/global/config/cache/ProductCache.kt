package com.task.musinsa.global.config.cache

import com.task.musinsa.application.InitProductCacheService
import com.task.musinsa.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class ProductCache : CommandLineRunner {

    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @Autowired
    private lateinit var initProductCacheService: InitProductCacheService

    override fun run(vararg args: String?) {
        categoryRepository.findAll()
            .forEach {
                initProductCacheService.findAllProducts(it.id!!)
            }
    }
}