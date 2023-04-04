package com.example.bottomsheeteg.ui.di

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.bottomsheeteg.ui.AppState
import com.example.bottomsheeteg.ui.AppStateImp
import com.example.bottomsheeteg.ui.bt1.Bt1Actions
import com.example.bottomsheeteg.ui.bt1.Bt1ActionsImp
import com.example.bottomsheeteg.ui.bt1.Bt1State
import com.example.bottomsheeteg.ui.bt2.Bt2ActionsImp
import com.example.bottomsheeteg.ui.bt2.Bt2State
import com.example.bottomsheeteg.ui.bt2.Bt2Actions
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModule {

    @Binds
    @Singleton
    fun appState(i:AppStateImp): AppState


    companion object{
        @Provides
        @Singleton
        fun B1StateMs():MutableState<Bt1State>{
            return mutableStateOf(Bt1State.random())
        }

        @Provides
        @Singleton
        fun B2StateMs():MutableState<Bt2State>{
            return mutableStateOf(Bt2State.random())
        }

        @Provides
        @Singleton
        fun Bt1Actions(b1Ms:MutableState<Bt1State>):Bt1Actions{
            return Bt1ActionsImp(b1Ms)
        }

        @Provides
        @Singleton
        fun Bt2Actions(b2Ms:MutableState<Bt2State>): Bt2Actions {
            return Bt2ActionsImp(b2Ms)
        }
    }
}