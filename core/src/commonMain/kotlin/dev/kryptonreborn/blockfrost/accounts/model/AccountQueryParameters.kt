package dev.kryptonreborn.blockfrost.accounts.model

data class AccountQueryParameters(
    val count: Int = 100,
    val page: Int = 1,
    val order: Order = Order.ASC,
) {
    fun toMap(): Map<String, String> {
        return mapOf(
            "count" to count.toString(),
            "page" to page.toString(),
            "order" to order.name.lowercase(),
        )
    }
}

enum class Order {
    ASC,
    DESC,
}
