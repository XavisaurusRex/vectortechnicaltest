package cat.devsofthecoast.vectortechincaltest.common.di.activity

import cat.devsofthecoast.vectortechincaltest.common.di.presentation.PresentationComponent
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

    fun newPresentationComponent(): PresentationComponent

}