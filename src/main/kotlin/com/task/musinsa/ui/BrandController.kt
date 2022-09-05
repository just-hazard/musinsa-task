package com.task.musinsa.ui

import com.task.musinsa.application.BrandService
import com.task.musinsa.dto.CreateBrandRequest
import com.task.musinsa.dto.UpdateBrandRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("apis/brands")
class BrandController {

    @Autowired
    private lateinit var brandService: BrandService

    @PostMapping
    fun createBrand(@RequestBody request: CreateBrandRequest): ResponseEntity<Void> {
        val id = brandService.createBrand(
            request.title,
            request.price,
            request.categoryId
        )

        return ResponseEntity.created(URI.create("apis/brands/$id"))
            .build()
    }

    @PutMapping("{id}")
    fun updateBrand(@PathVariable id: Long, @RequestBody request: UpdateBrandRequest): ResponseEntity<Void> {
        brandService.updateBrand(id, request.title, request.price)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("{id}")
    fun deleteBrand(@PathVariable id: Long): ResponseEntity<Void> {
        brandService.deleteBrand(id)
        return ResponseEntity.noContent().build()
    }
}