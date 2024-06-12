package dev.kryptonreborn.blockfrost.base

/**
 * The `BaseQueryParameters` class represents the query parameters for an account.
 *
 * @property count The number of items to return.
 * @property page The page number to return.
 * @property order The order in which to return the items.
 */
data class BaseQueryParameters(
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

/**
 * The `Order` enum represents the order in which to return items.
 */
enum class Order {
    ASC,
    DESC,
}
