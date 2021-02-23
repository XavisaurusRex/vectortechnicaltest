package cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.activity

import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.presentation.PresentationComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class, UseCaseModule::class])
interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent

}