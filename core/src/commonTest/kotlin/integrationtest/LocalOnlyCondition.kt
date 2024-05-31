package integrationtest

import io.kotest.core.annotation.EnabledCondition
import io.kotest.core.spec.Spec
import kotlin.reflect.KClass

internal class LocalOnlyCondition : EnabledCondition {
    override fun enabled(kclass: KClass<out Spec>): Boolean {
        return true
    }
}
