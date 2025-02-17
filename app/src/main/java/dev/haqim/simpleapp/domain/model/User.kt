package dev.haqim.simpleapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class User(
	val website: String,
	val address: Address,
	val phone: String,
	val name: String,
	val company: Company,
	val id: Int,
	val email: String,
	val username: String
): Parcelable

@Serializable
@Parcelize
data class Company(
	val bs: String,
	val catchPhrase: String,
	val name: String
): Parcelable

@Serializable
@Parcelize
data class Geo(
	val lng: String,
	val lat: String
): Parcelable

@Serializable
@Parcelize
data class Address(
	val zipcode: String,
	val geo: Geo,
	val suite: String,
	val city: String,
	val street: String
): Parcelable

val dummyUser = User(
	id = 1,
	name = "John Doe",
	username = "johndoe",
	email = "johndoe@example.com",
	phone = "+1-202-555-0173",
	website = "www.johndoe.com",
	address = Address(
		street = "123 Main St",
		suite = "Apt. 456",
		city = "New York",
		zipcode = "10001",
		geo = Geo(
			lat = "40.7128",
			lng = "-74.0060"
		)
	),
	company = Company(
		name = "Doe Enterprises",
		catchPhrase = "Innovate and Elevate",
		bs = "tech solutions"
	)
)


