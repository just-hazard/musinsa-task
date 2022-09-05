package com.task.musinsa.dto

import com.task.musinsa.global.config.message.ErrorMessage
import org.hibernate.validator.constraints.Range
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class CreateBrandRequest(
    @field:NotBlank(message = ErrorMessage.NOT_EMPTY_BRAND_NAME)
    val title: String,
    @field:Range(message = ErrorMessage.CAT_NOT_NEGATIVE_NUMBER)
    val price: Int,
    val categoryId: Long
)

data class UpdateBrandRequest(
    @field:NotBlank(message = ErrorMessage.NOT_EMPTY_BRAND_NAME)
    val title: String,
    @field:Range(message = ErrorMessage.CAT_NOT_NEGATIVE_NUMBER)
    val price: Int,
)