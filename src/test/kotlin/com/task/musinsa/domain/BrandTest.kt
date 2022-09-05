package com.task.musinsa.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BrandTest : BehaviorSpec({
    given("setup") {
        var entity = Brand("title", Price(100), Category("category"))
        `when`("update function call") {
            entity.update("second", 1000)
            then("결과 확인") {
                entity.title shouldBe "second"
                entity.price.price shouldBe 1000
            }
        }
    }
})