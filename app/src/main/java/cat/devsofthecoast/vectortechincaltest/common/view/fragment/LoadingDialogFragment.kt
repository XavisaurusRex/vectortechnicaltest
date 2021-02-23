package cat.devsofthecoast.vectortechincaltest.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.databinding.FragmentLoadingBinding

class LoadingDialogFragment : BaseDialogFragment() {

    private lateinit var binding: FragmentLoadingBinding

    override fun injectView(presentationComponent: PresentationComponent) {
        presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoadingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(LOADING_MESSAGE)?.let {
            binding.tvLoadingMessage.text = it
        }

    }


    companion object {
        private const val LOADING_MESSAGE = "loadingMessage"

        fun newInstance(loadingMessage: String?): LoadingDialogFragment {
            val args = Bundle()
            args.putString(LOADING_MESSAGE, loadingMessage)
            val fragment = LoadingDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}