package com.task.musinsa.dto

data class CategoryResponse(
    val id: Long,
    val title: String,
    val brands: List<BrandResponse>
) {
    fun findLowestPriceBrand() : BrandResponse {
        return this.brands.minByOrNull {
            it.price
        }!!
    }
}

data class BrandResponse(
    val id: Long,
    val title: String,
    val price: Int
)

data class LowestPriceProductStyleResponse(
    val categoryName: String,
    val brandName: String,
    val price: Int
)

data class LowestPriceProductStyleTotalPriceResponse(
    val lowestPriceProductStyle: List<LowestPriceProductStyleResponse>,
    val totalPrice: Int = lowestPriceProductStyle.sumOf { it.price }
)
