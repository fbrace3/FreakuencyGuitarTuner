package edu.msudenver.cs3013.project01

import android.util.Log

object userData {

    private val TAG = "userData"

    var username: String = "admin"
        get() {
            Log.d(TAG, "Accessed userData.username: $field")
            return field
        }
        set(value) {
            field = value
            Log.d(TAG, "Updated userData.username: $field")
        }

    var password: String = "admin"
        get() {
            Log.d(TAG, "Accessed userData.password: $field")
            return field
        }
        set(value) {
            field = value
            Log.d(TAG, "Updated userData.password: $field")
        }
    var age: Int = 0
    var favoriteInstrument: String = "null"
    var firstName: String = "null"
    var lastName: String = "null"
    var emailAddress: String = "null"

}