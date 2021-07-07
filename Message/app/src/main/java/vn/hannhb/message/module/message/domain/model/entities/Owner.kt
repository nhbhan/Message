package vn.hannhb.message.module.message.domain.model.entities

import com.squareup.moshi.Json

class Owner(
    @Json(name = "accountId") var accountId: Int? = null,
    @Json(name = "avatar") var avatar: String? = null,
    @Json(name = "domainId") var domainId: Int? = null,
    @Json(name = "email") var email: String?,
    @Json(name = "fullName") var fullName: String?
) : Base()
