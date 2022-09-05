package com.task.musinsa.ui

import com.task.musinsa.application.ProductPriceService
import com.task.musinsa.dto.LowestPriceBrandResponse
import com.task.musinsa.dto.LowestPriceProductStyleTotalPriceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("apis/products")
class ProductController {

    @Autowired
    private lateinit var productPriceService: ProductPriceService

    @GetMapping("lowest-price")
    fun findLowestPriceProductStyle(): ResponseEntity<LowestPriceProductStyleTotalPriceResponse> {
        return ResponseEntity.ok().body(productPriceService.findLowestPriceProductStyle())
    }

    @GetMapping("brands/lowest-price")
    fun findLowestPriceBrand(): ResponseEntity<LowestPriceBrandResponse> {
        return ResponseEntity.ok().body(this.productPriceService.findLowestPriceBrand())
    }

    @GetMapping("categories/min-max-price")
    fun findCategoryLowestAndMostExpensivePrice(
        @RequestParam categoryName: String
    ): ResponseEntity<Any> {
        return ResponseEntity.ok().body(productPriceService.findLowestAndMostExpensivePriceCategory(categoryName))
    }
}