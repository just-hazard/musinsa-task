package com.task.musinsa.acceptance

import com.task.musinsa.util.AcceptanceTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class ProductAcceptanceTest : AcceptanceTest() {

    @Test
    fun `최저가 상품 리스트 조회`() {
        When {
            get("apis/products/lowest-price")
        } Then {
            statusCode(HttpStatus.OK.value())
            contentType(ContentType.JSON)
            body("lowestPriceProductStyle.size()", equalTo(8))
            body("lowestPriceProductStyle[0].categoryName", equalTo("상의"))
            body("lowestPriceProductStyle[0].price", equalTo(10000))
        } Extract {
            println(body().asPrettyString())
        }
    }

    @Test
    fun `브랜드 묶음 최저가 상품 리스트 조회`() {
        Given {
            contentType(ContentType.JSON)
        } When {
            get("apis/products/brands/lowest-price")
        } Then {
            statusCode(HttpStatus.OK.value())
            contentType(ContentType.JSON)
            body("brandName", equalTo("D"))
            body("totalPrice", equalTo(36100))
        } Extract {
            println(body().asPrettyString())
        }
    }
}