package com.qonversion.flutter.sdk.qonversion_flutter_sdk

import com.qonversion.android.sdk.QonversionError
import com.qonversion.android.sdk.dto.QLaunchResult
import com.qonversion.android.sdk.dto.QPermission
import com.qonversion.android.sdk.dto.QProduct

data class PurchaseResult(val permissions: Map<String, QPermission>? = null, val error: QonversionError? = null) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
                "permissions" to permissions?.mapValues { it.value.toMap() },
                "error" to (error?.description ?: error?.additionalMessage)
        )
    }
}

fun QLaunchResult.toMap(): Map<String, Any> {
    return mapOf(
            "uid" to uid,
            "timestamp" to date.time.toDouble(),
            "products" to products.mapValues { it.value.toMap() },
            "permissions" to permissions.mapValues { it.value.toMap() },
            "user_products" to userProducts.mapValues { it.value.toMap() }
    )
}

fun QProduct.toMap(): Map<String, Any?> {
    return mapOf(
            "id" to qonversionID,
            "store_id" to storeID,
            "type" to type.type,
            "duration" to duration?.type
    )
}

fun QPermission.toMap(): Map<String, Any?> {
    return mapOf(
            "id" to permissionID,
            "associated_product" to productID,
            "renew_state" to renewState.type,
            "started_timestamp" to startedDate.time.toDouble(),
            "expiration_timestamp" to expirationDate?.time?.toDouble(),
            "active" to isActive()
    )
}