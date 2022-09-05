package com.task.musinsa.dto

data class CategoryResponse(
    val id: Long,
    val title: String,
    val brands: List<BrandResponse>
) {
    fun findLowestPriceBrand(): BrandResponse {
        return findLowestProduct()
    }

    fun findLowestAndMostExpensivePrice(): LowestAndMostExpensivePriceResponse {
        val min = findLowestProduct()
        val max = brands.maxByOrNull { it.price }!!

        return LowestAndMostExpensivePriceResponse(
            min.title,
            min.price,
            max.title,
            max.price
        )
    }

    private fun findLowestProduct() = brands.minByOrNull {
        it.price
    }!!
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

data class LowestPriceBrandResponse(
    val brandName: String,
    val totalPrice: Int
)

data class LowestAndMostExpensivePriceResponse(
    val lowestPriceBrandName: String,
    val lowestPrice: Int,
    val mostExpensiveBrandName: String,
    val mostExpensivePrice: Int
)