package com.task.musinsa.dto

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class CategoryResponseTest : BehaviorSpec ({
    given("카테고리 데이터 생성") {
        val data = CategoryResponse(
            1,
            "title",
            listOf(
                BrandResponse(1, "title_one", 1000),
                BrandResponse(2, "title_two", 2000),
                BrandResponse(3, "title_three", 3000)
            )
        )

        `when`("가장 가격이 낮은 브랜드 찾기") {
            val result = data.findLowestPriceBrand()
            then("가장 낮은 가격인지 검증") {
                result.price shouldBe 1000
            }
        }
    }
})