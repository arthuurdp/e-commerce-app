package com.ecommerce.app.data.model.notification

data class NotificationResponse(
    val id: Long,
    val message: String?,
    val type: String?,
    val read: Boolean?,
    val createdAt: String?
)
