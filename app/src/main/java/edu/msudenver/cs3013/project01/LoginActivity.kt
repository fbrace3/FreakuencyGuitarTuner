package edu.msudenver.cs3013.project01

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class LoginActivity : AppCompatActivity() {

    private val submitButton: Button
        get() = findViewById(R.id.submit_button)
    private val registerButton: Button
        get() = findViewById(R.id.register_button)

    private val userName: EditText
        get() = findViewById(R.id.user_name)

    private val password: EditText
        get() = findViewById(R.id.password)

    private var registeredUsername: String? = ""
    private var registeredPassword: String? = ""
    private var registeredUser = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        if (userName != null) {
//            userData.username = userName
//        }

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                registeredUsername = data?.getStringExtra("myUsername")
                registeredPassword = data?.getStringExtra("myPassword")
                registeredUser = data?.getSerializableExtra("myUser") as User
            }
        }
        registerButton.setOnClickListener {
            startForResult.launch(Intent(this, RegisterActivity::class.java))
        }

        submitButton.setOnClickListener {

            var userNameForm = userName.text.toString().trim()
            var passwordForm = password.text.toString().trim()

            hideKeyboard()

            if (userNameForm.isNotEmpty() && passwordForm.isNotEmpty()) {

                //Set the name of the activity to launch
                Intent(this, WelcomeActivity::class.java).also { welcomeIntent ->
                    //Add the data
                    welcomeIntent.putExtra("registeredUsername", registeredUsername)
                    welcomeIntent.putExtra("registeredPassword", registeredPassword)
                    welcomeIntent.putExtra("myUser", registeredUser)
                    welcomeIntent.putExtra("USER_NAME_KEY", userNameForm)
                    welcomeIntent.putExtra("PASSWORD_KEY", passwordForm)


                    //Debugging tests
//                    if (registeredUsername != null) {
//                        Log.d("Login Username", registeredUsername!!)
//                    }
//                    if (registeredPassword != null) {
//                        Log.d("Login Password", registeredPassword!!)
//                    }


                    //Reset text fields to blank
                    this.userName.text.clear()
                    this.password.text.clear()

                    //Launch
                    startActivity(welcomeIntent)
                }
            } else {
                val toast = Toast.makeText(
                    this,
                    getString(R.string.login_form_entry_error),
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
}