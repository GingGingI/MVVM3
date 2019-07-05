package c.gingdev.mvvm_3.vm

import android.text.TextUtils
import android.util.Patterns
import androidx.databinding.Observable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class BasicViewModel: ViewModel() {
    val email = ObservableField<String?>()
    val password = ObservableField<String?>()
    val isValid = ObservableBoolean()

    init {
        isValid.set(false)
        email.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                validation()
            }
        })
        password.addOnPropertyChangedCallback(object: Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                validation()
            }
        })
    }

    fun validation() {
        val EmailValid = !TextUtils.isEmpty(email.get()) && Patterns.EMAIL_ADDRESS.matcher(email.get()).matches()
        val PasswordValid = !TextUtils.isEmpty(password.get())

        isValid.set(EmailValid && PasswordValid)
    }
}