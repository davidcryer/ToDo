package com.davidcryer.todo

import com.davidc.uiwrapper.UiWrapperFactoryProvider

class Application : android.app.Application(), UiWrapperFactoryProvider<UiWrapperFactory> {
    private var uiWrapperFactory: UiWrapperFactory? = null

    override fun onCreate() {
        super.onCreate()
        uiWrapperFactory = UiWrapperFactory()
    }

    override fun getUiWrapperFactory(): UiWrapperFactory {
        return uiWrapperFactory!!
    }
}