package dev.haqim.simpleapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse(

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("address")
	val address: AddressResponse? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("company")
	val company: CompanyResponse? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class AddressResponse(

	@field:SerializedName("zipcode")
	val zipcode: String? = null,

	@field:SerializedName("geo")
	val geo: GeoResponse? = null,

	@field:SerializedName("suite")
	val suite: String? = null,

	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("street")
	val street: String? = null
)

data class CompanyResponse(

	@field:SerializedName("bs")
	val bs: String? = null,

	@field:SerializedName("catchPhrase")
	val catchPhrase: String? = null,

	@field:SerializedName("name")
	val name: String? = null
)

data class GeoResponse(

	@field:SerializedName("lng")
	val lng: String? = null,

	@field:SerializedName("lat")
	val lat: String? = null
)
