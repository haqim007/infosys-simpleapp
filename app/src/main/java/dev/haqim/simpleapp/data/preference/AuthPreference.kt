package dev.haqim.simpleapp.data.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.haqim.simpleapp.domain.model.Address
import dev.haqim.simpleapp.domain.model.Company
import dev.haqim.simpleapp.domain.model.Geo
import dev.haqim.simpleapp.domain.model.User
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthPreference @Inject constructor(
    private val dataStore: DataStore<Preferences>
){
    suspend fun logout() {
        dataStore.edit {preferences ->
            preferences[USER_NAME] = ""
            preferences[USER_EMAIL] = ""
            preferences[USER_PHONE] = ""
            preferences[USER_ID] = 0
            preferences[USER_WEBSITE] = ""

            preferences[USER_ADDRESS_ZIPCODE] = ""
            preferences[USER_ADDRESS_CITY] = ""
            preferences[USER_ADDRESS_STREET] = ""
            preferences[USER_ADDRESS_SUIT] = ""
            preferences[USER_LAT] = ""
            preferences[USER_LNG] = ""

            preferences[HAS_LOGIN] = false
        }
    }

    suspend fun saveUserToDataStore(user: User) {
        dataStore.edit { preferences ->
            preferences[USER_NAME] = user.name
            preferences[USER_EMAIL] = user.email
            preferences[USER_PHONE] = user.phone
            preferences[USER_ID] = user.id
            preferences[USER_WEBSITE] = user.website

            preferences[USER_ADDRESS_ZIPCODE] = user.address.zipcode
            preferences[USER_ADDRESS_CITY] = user.address.city
            preferences[USER_ADDRESS_STREET] = user.address.street
            preferences[USER_ADDRESS_SUIT] = user.address.suite

            preferences[USER_LAT] = user.address.geo.lat
            preferences[USER_LNG] = user.address.geo.lng

            preferences[HAS_LOGIN] = true
        }
    }

    fun getUser() = dataStore.data.map {preferences ->
        val name = preferences[USER_NAME] ?: ""
        val email = preferences[USER_EMAIL] ?: ""
        val phone = preferences[USER_PHONE] ?: ""
        val id = preferences[USER_ID] ?: 0
        val website = preferences[USER_WEBSITE] ?: ""

        val zipcode = preferences[USER_ADDRESS_ZIPCODE] ?: ""
        val city = preferences[USER_ADDRESS_CITY] ?: ""
        val street = preferences[USER_ADDRESS_STREET] ?: ""
        val suite = preferences[USER_ADDRESS_SUIT] ?: ""

        val lat = preferences[USER_LAT] ?: "0.0"
        val lng = preferences[USER_LNG] ?: "0.0"

        val address = Address(zipcode, Geo(lng, lat), suite, city, street)
        User(website, address, phone, name, Company("", "", ""), id, email, "")
    }

    fun hasLogin() = dataStore.data.map { preferences ->
        preferences[HAS_LOGIN] ?: false
    }

    companion object{
        // Define keys
        val USER_NAME = stringPreferencesKey("user_name")
        val USER_EMAIL = stringPreferencesKey("user_email")
        val USER_PHONE = stringPreferencesKey("user_phone")
        val USER_ID = intPreferencesKey("user_id")
        val USER_WEBSITE = stringPreferencesKey("user_website")

        // Address-related keys
        val USER_ADDRESS_ZIPCODE = stringPreferencesKey("user_address_zipcode")
        val USER_ADDRESS_CITY = stringPreferencesKey("user_address_city")
        val USER_ADDRESS_STREET = stringPreferencesKey("user_address_street")
        val USER_ADDRESS_SUIT = stringPreferencesKey("user_address_suite")
        val USER_LAT = stringPreferencesKey("user_lat")
        val USER_LNG = stringPreferencesKey("user_lng")

        val HAS_LOGIN = booleanPreferencesKey("has_login")
    }
}