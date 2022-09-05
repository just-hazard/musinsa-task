package com.task.musinsa.repositories

import com.task.musinsa.domain.Brand
import org.springframework.data.jpa.repository.JpaRepository

interface BrandRepository : JpaRepository<Brand, Long> {
}