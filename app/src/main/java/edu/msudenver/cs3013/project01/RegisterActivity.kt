package edu.msudenver.cs3013.project01

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterActivity : AppCompatActivity() {
    private val registerButton: Button
        get() = findViewById(R.id.register_button)
    private val firstName: EditText
        get() = findViewById(R.id.first_name)
    private val lastName: EditText
        get() = findViewById(R.id.last_name)
    private val userName: EditText
        get() = findViewById(R.id.user_name)
    private val password: EditText
        get() = findViewById(R.id.password)
    private val confirmPassword: EditText
        get() = findViewById(R.id.confirmPassword)
    private val age: EditText
        get() = findViewById(R.id.age)
    private val favoriteInstrument: EditText
        get() = findViewById(R.id.favorite_instrument)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        registerButton.setOnClickListener {

            var firstNameForm = firstName.text.toString().trim()
            var lastNameForm = lastName.text.toString().trim()
            var userNameForm = userName.text.toString().trim()
            var passwordForm = password.text.toString().trim()
            var confirmPasswordForm = confirmPassword.text.toString().trim()
            var ageForm = age.text.toString().trim()
            var favoriteInstrumentForm = favoriteInstrument.text.toString().trim()


            hideKeyboard()

            if (firstNameForm.isNotEmpty() && passwordForm.isNotEmpty() && lastNameForm.isNotEmpty() && ageForm.isNotEmpty() && favoriteInstrumentForm.isNotEmpty()) {

                userData.firstName = firstNameForm

                userData.lastName = lastNameForm
                userData.username = userNameForm
                userData.password = passwordForm
                userData.age = ageForm.toInt()
                userData.favoriteInstrument = favoriteInstrumentForm

                Intent(this, LoginActivity::class.java).also { loginIntent ->
                    val toast = Toast.makeText(
                        this,
                        getString(R.string.register_form_entry_success),
                        Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    startActivity(loginIntent)
                }



                //Set the name of the activity to launch
//                Intent(this, WelcomeActivity::class.java).also { welcomeIntent ->
//                    //Add the data
//                    welcomeIntent.putExtra(USER_NAME_KEY, userNameForm)
//                    welcomeIntent.putExtra(PASSWORD_KEY, passwordForm)
//
//                    //Reset text fields to blank
//                    this.userName.text.clear()
//                    this.password.text.clear()
//
//                    //Launch
//                    startActivity(welcomeIntent)
//                }


            } else {
                val toast = Toast.makeText(
                    this,
                    getString(R.string.register_form_entry_error),
                    Toast.LENGTH_LONG
                )
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
            }

        }

    }
    private fun hideKeyboard() {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }
    companion object {
        const val USER_NAME_KEY = "edu.msudenver.cs3013.project01.USER_NAME"
        const val PASSWORD_KEY = "edu.msudenver.cs3013.project01.PASSWORD"
    }
}