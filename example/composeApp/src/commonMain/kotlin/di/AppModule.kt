package di

import common.Context
import org.koin.dsl.module
import presentation.ui.home.viewmodel.HomeViewModel
import presentation.ui.result.viewmodel.ResultViewModel

fun appModule(context: Context) =
    module {
        factory { HomeViewModel() }
        factory { ResultViewModel() }
    }
