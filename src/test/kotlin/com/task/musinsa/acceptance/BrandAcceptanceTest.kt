package com.task.musinsa.acceptance

import com.task.musinsa.dto.CreateBrandRequest
import com.task.musinsa.dto.UpdateBrandRequest
import com.task.musinsa.util.AcceptanceTest
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import io.restassured.response.Response
import org.hamcrest.CoreMatchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class BrandAcceptanceTest : AcceptanceTest() {

    @Test
    fun `브랜드 상품 등록`() {
        `상품 등록`(
            CreateBrandRequest(
                "타이틀",
                500,
                1
            )
        ) Then {
            statusCode(HttpStatus.CREATED.value())
            header("Location", equalTo("apis/brands/73"))
        }
    }

    @Test
    fun `존재하지 않는 카테고리id 등록 시 예외`() {
        `상품 등록`(
            CreateBrandRequest(
                "타이틀",
                500,
                10
            )
        ) Then {
            statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        } Extract {
            println(body().asPrettyString())
        }
    }

    @Test
    fun `브랜드 상품 수정`() {
        val location = `상품 등록`(
            CreateBrandRequest(
                "타이틀",
                500,
                1
            )
        ) Extract {
            header("Location")
        }

        `상품 수정`(
            UpdateBrandRequest(
                "타이틀1",
                5000
            ), location.replace("apis/brands/", "").toLong()
        ) Then {
            statusCode(HttpStatus.NO_CONTENT.value())
        } Extract {
            println(body().asPrettyString())
        }
    }

    @Test
    fun `존재하지 않는 브랜드id 수정 시 예외`() {
        val location = `상품 등록`(
            CreateBrandRequest(
                "타이틀",
                500,
                1
            )
        ) Extract {
            header("Location")
        }

        `상품 수정`(
            UpdateBrandRequest(
                "타이틀1",
                5000
            ), 100L
        ) Then {
            statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
        } Extract {
            println(body().asPrettyString())
        }
    }

    @Test
    fun `브랜드 상품 삭제`() {
        val location = `상품 등록`(
            CreateBrandRequest(
                "타이틀",
                500,
                1
            )
        ) Extract {
            header("Location")
        }

        Given {
            contentType(ContentType.JSON)
        } When {
            delete("apis/brands/${location.replace("apis/brands/", "").toLong()}")
        } Then {
            statusCode(HttpStatus.NO_CONTENT.value())
        } Extract {
            println(body().asPrettyString())
        }
    }

    private fun `상품 등록`(request: CreateBrandRequest): Response {
        return Given {
            contentType(ContentType.JSON)
            body(request)
        } When {
            post("apis/brands")
        }
    }

    private fun `상품 수정`(request: UpdateBrandRequest, id: Long): Response {
        return Given {
            contentType(ContentType.JSON)
            body(request)
        } When {
            put("apis/brands/$id")
        }
    }
}