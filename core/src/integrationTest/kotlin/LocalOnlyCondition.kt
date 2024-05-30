import io.kotest.core.annotation.EnabledCondition
import io.kotest.core.spec.Spec
import kotlin.reflect.KClass

class LocalOnlyCondition : EnabledCondition {
    override fun enabled(kclass: KClass<out Spec>): Boolean {
        return System.getenv("CI") != "true"
    }
}
