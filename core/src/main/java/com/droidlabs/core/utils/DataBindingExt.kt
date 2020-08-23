package com.droidlabs.core.utils

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.observe
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class FragmentDataBindingDelegate<T : ViewDataBinding>(
    val fragment: Fragment,
    val dataBindingFactory: (LayoutInflater) -> T
) : ReadOnlyProperty<Fragment, T> {

    private var _binding: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            _binding = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        val binding = _binding
        if (binding != null) {
            return binding
        }

        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException(
                "Should not attempt to get bindings when Fragment views" +
                        " are destroyed or uninitialized."
            )
        }

        return dataBindingFactory(thisRef.layoutInflater).also { _binding = it }
    }
}

class ActivityDataBindingDelegate<T : ViewDataBinding>(
    val activity: AppCompatActivity,
    val dataBindingFactory: (LayoutInflater) -> T
) : ReadOnlyProperty<AppCompatActivity, T> {

    private var _binding: T? = null

    init {
        activity.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                _binding = null
            }
        })
    }

    override fun getValue(thisRef: AppCompatActivity, property: KProperty<*>): T {
        val binding = _binding
        if (binding != null) {
            return binding
        }

        val lifecycle = activity.lifecycle
        if (!lifecycle.currentState.isAtLeast(Lifecycle.State.INITIALIZED)) {
            throw IllegalStateException(
                "Should not attempt to get bindings when Activity is destroyed or uninitialized."
            )
        }

        return dataBindingFactory(thisRef.layoutInflater).also { _binding = it }
    }
}

fun <T : ViewDataBinding> Fragment.dataBinding(
    dataBindingFactory: (LayoutInflater) -> T
): FragmentDataBindingDelegate<T> = FragmentDataBindingDelegate(this, dataBindingFactory)

fun <T : ViewDataBinding> AppCompatActivity.dataBinding(
    dataBindingFactory: (LayoutInflater) -> T
): ActivityDataBindingDelegate<T> = ActivityDataBindingDelegate(this, dataBindingFactory)
