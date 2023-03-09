package com.steve_md.joomia.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Fragment.hideKeyboard(): Boolean {
    return (context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow((activity?.currentFocus ?: View(context)).windowToken, 0)
}

fun Fragment.toast(message: String) {
    Toast.makeText(requireContext(),
        message,
        Toast.LENGTH_SHORT).show()
}

// SnackBar
fun Fragment.snackBar() {
    Snackbar.make(View(requireActivity()),"Coming soon!", Snackbar.LENGTH_SHORT).show()
}
