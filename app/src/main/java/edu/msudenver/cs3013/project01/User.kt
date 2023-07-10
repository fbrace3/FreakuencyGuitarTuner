package edu.msudenver.cs3013.project01

import java.io.Serializable

class User : Serializable {
    var username: String = "admin"
        set(value) {
            field = value
            userData.username = value
        }
        get() {
            return userData.username
        }
    var password: String = "admin"
        set(value) {
            field = value
            userData.password = value
        }
        get() {
            return userData.password
        }
    var age: Int = 0
        set(value) {
            field = value
            userData.age = value
        }
        get() {
            return userData.age
        }
    var favoriteInstrument: String = ""
        set(value) {
            field = value
            userData.favoriteInstrument = value
        }
        get() {
            return userData.favoriteInstrument
        }
    var firstName: String = ""
        set(value) {
            field = value
            userData.firstName = value
        }
        get() {
            return userData.firstName
        }
    var lastName: String = ""
        set(value) {
            field = value
            userData.lastName = value
        }
        get() {
            return userData.lastName
        }
    var emailAddress: String = ""
        set(value) {
            field = value
            userData.emailAddress = value
        }
        get() {
            return userData.emailAddress
        }

    init {
        // Assign the property values to the corresponding properties of the userData object
        userData.username = username
        userData.password = password
        userData.age = age
        userData.favoriteInstrument = favoriteInstrument
        userData.firstName = firstName
        userData.lastName = lastName
        userData.emailAddress = emailAddress
    }
}
