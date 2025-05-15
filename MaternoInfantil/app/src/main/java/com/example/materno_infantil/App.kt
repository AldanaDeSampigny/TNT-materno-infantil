package com.example.materno_infantil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class App : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { result ->
        onSignInResult(result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            goToMainOrRegister(currentUser)
        } else {
            launchSignInFlow()
        }
    }

    private fun launchSignInFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.AnonymousBuilder().build() // <- Habilita modo invitado
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setLogo(R.drawable.maternoinfantil)
            .setTheme(R.style.Theme_MaternoInfantil)
            .build()

        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val user = FirebaseAuth.getInstance().currentUser
        if (result.resultCode == RESULT_OK && user != null) {
            goToMainOrRegister(user)
        } else {
            Log.e("App.kt", "Login cancelado o fallido")
            finish()
        }
    }

    private fun goToMainOrRegister(user: FirebaseUser) {
       // if (user.isAnonymous) {
            // Ir a pantalla para convertir a cuenta real
      //      startActivity(Intent(this, RegisterActivity::class.java))
   //     } else {
            // Ir al home
            startActivity(Intent(this, MainActivity::class.java))
   //     }
        finish()
    }
}

