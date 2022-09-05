package com.task.musinsa.domain

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*

class PriceTest : BehaviorSpec({
    given("setup") {
        var entity = Price(100)
        `when`("update function call") {
            entity.update(1000)
            then("결과 확인") {
                entity.price shouldBe 1000
            }
        }
    }
})