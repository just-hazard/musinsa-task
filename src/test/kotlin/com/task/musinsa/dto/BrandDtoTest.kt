package com.task.musinsa.dto

import com.task.musinsa.global.config.message.ErrorMessage
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory

class BrandDtoTest : BehaviorSpec ({
    given("validator object 생성") {
        val factory: ValidatorFactory = Validation.buildDefaultValidatorFactory()
        val validator: Validator = factory.validator
        `when`("브랜드 이름이 빈 값일 경우") {
            val violations = validator.validate(CreateBrandRequest(
                "",
                1000,
                1
            ))
            then("error message") {
                violations.first().message shouldBe ErrorMessage.NOT_EMPTY_BRAND_NAME
            }
        }
        `when`("가격이 음수일 경우") {
            val violations = validator.validate(CreateBrandRequest(
                "title",
                -1,
                1
            ))
            then("error message") {
                violations.first().message shouldBe ErrorMessage.CAT_NOT_NEGATIVE_NUMBER
            }
        }
    }
})