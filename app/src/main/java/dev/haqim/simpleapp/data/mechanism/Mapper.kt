package dev.haqim.simpleapp.data.mechanism

import dev.haqim.simpleapp.data.remote.response.AddressResponse
import dev.haqim.simpleapp.data.remote.response.CompanyResponse
import dev.haqim.simpleapp.data.remote.response.GeoResponse
import dev.haqim.simpleapp.data.remote.response.UserResponse
import dev.haqim.simpleapp.domain.model.Address
import dev.haqim.simpleapp.domain.model.Company
import dev.haqim.simpleapp.domain.model.Geo
import dev.haqim.simpleapp.domain.model.User

fun UserResponse.toUser() = User(
    website ?: "",
    address?.toAddress() ?: Address("", Geo("", ""), "", "", ""),
    phone ?: "",
    name ?: "",
    company?.toCompany() ?: Company("", "", ""),
    id ?: 0,
    email ?: "",
    username ?: ""
)

fun AddressResponse.toAddress() = Address(
    zipcode ?: "",
    geo?.toGeo() ?: Geo("", ""),
    suite ?: "",
    city ?: "",
    street ?: ""
)

fun GeoResponse.toGeo() = Geo(lat ?: "", lng ?: "")

fun CompanyResponse.toCompany() = Company(
    bs ?: "",
    name ?: "",
    catchPhrase ?: ""
)