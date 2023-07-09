package edu.msudenver.cs3013.project01

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.isVisible


class WelcomeActivity : AppCompatActivity() {

    var isLoggedIn = false
    var loggedInUser = ""

    private val header: TextView
        get() = findViewById(R.id.header)

    private val backButton: Button
        get() = findViewById(R.id.back_button)
    private val continueButton: Button
        get() = findViewById(R.id.continue_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        //Get the intent which started this activity
        intent.let { loginIntent ->

            val userNameForm = loginIntent?.getStringExtra(userData.username) ?: ""
            Log.d(TAG, "SendingRecieved intent: $userData.username")
            val passwordForm = loginIntent?.getStringExtra(userData.password) ?: ""
            Log.d(TAG, "SendingRecieved intent2: $userData.password")


            val loggedInCorrectly =
                hasEnteredCorrectCredentials(userNameForm.trim(), passwordForm.trim())

            if (loggedInCorrectly) {
                setLoggedIn(userNameForm)
                isLoggedIn = true
                continueButton.isVisible = true
                continueButton.setOnClickListener{
                    Intent(this, MainActivity::class.java).also { mainIntent ->
                        //Add the data
                        mainIntent.putExtra(MainActivity.USER_NAME_KEY, userNameForm)
                        Log.d(TAG, "Sending intent: $userNameForm")

                        startActivity(mainIntent)
                    }}

            } else {
                header.text = getString(R.string.login_error)
                backButton.isVisible = true
                backButton.setOnClickListener {
                    //Finishes this activity and so goes back to the previous activity
//                    val intent = Intent(this, LoginActivity::class.java)
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    Log.d(TAG, "userData.welcomefinishfail: $userData.username")
//                    startActivity(intent)
                    finish()


                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putBoolean(IS_LOGGED_IN, isLoggedIn)
        outState.putString(LOGGED_IN_USERNAME, loggedInUser)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        isLoggedIn = savedInstanceState.getBoolean(IS_LOGGED_IN, false)
        loggedInUser = savedInstanceState.getString(LOGGED_IN_USERNAME, "")

        if (isLoggedIn && loggedInUser.isNotBlank()) {
            setLoggedIn(loggedInUser)
        }
    }

    private fun setLoggedIn(userName: String) {
        loggedInUser = userName
        val welcomeMessage = getString(R.string.welcome_text, userName)
        backButton.isVisible = false
        header.text = welcomeMessage
    }

    private fun hasEnteredCorrectCredentials(
        userNameForm: String,
        passwordForm: String
    ): Boolean {
        return userNameForm.contentEquals(userData.username) && passwordForm.contentEquals(
            userData.password
        )
    }
    companion object {
        const val IS_LOGGED_IN = "IS_LOGGED_IN"
        const val LOGGED_IN_USERNAME = "LOGGED_IN_USERNAME"
    }
}