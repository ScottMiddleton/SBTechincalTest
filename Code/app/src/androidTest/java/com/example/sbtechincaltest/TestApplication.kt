package com.example.sbtechincaltest

import org.koin.dsl.module

/**
 * Helps to configure required dependencies for Instru Tests.
 * Method provideDependency can be overrided and new dependencies can be supplied.
 */
class TestApplication : SBApplication() {
    override fun provideDependency() = module { }
}