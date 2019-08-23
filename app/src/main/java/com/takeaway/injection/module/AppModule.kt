package com.takeaway.injection.module

import dagger.Module

@Module(includes = [(NetworkModule::class)])
class AppModule