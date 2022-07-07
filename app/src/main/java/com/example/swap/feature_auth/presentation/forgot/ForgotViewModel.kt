package com.example.swap.feature_auth.presentation.forgot

import androidx.lifecycle.ViewModel
import com.example.swap.common.EventHandler
import com.example.swap.feature_auth.presentation.forgot.ForgotEvent
import javax.inject.Inject

class ForgotViewModel @Inject constructor(
) : ViewModel(), EventHandler<ForgotEvent> {

    override fun obtainEvent(event: ForgotEvent) {

    }

}