package cat.devsofthecoast.vectortechincaltest.common.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cat.devsofthecoast.vectortechincaltest.common.dependencyinjection.presentation.PresentationComponent
import cat.devsofthecoast.vectortechincaltest.databinding.FragmentErrorDialogBinding

class ErrorDialogFragment : BaseDialogFragment() {

    private lateinit var binding: FragmentErrorDialogBinding

    override fun injectView(presentationComponent: PresentationComponent) {
        presentationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentErrorDialogBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ERROR_MESSAGE)?.let {
            binding.tvErrorMessage.text = it
        }

        binding.tvErrorAction.setOnClickListener {
            this.dismiss()
        }
    }


    companion object {
        private const val ERROR_MESSAGE = "loadingMessage"

        fun newInstance(errorMessage: String?): ErrorDialogFragment {
            val args = Bundle()
            args.putString(ERROR_MESSAGE, errorMessage)
            val fragment = ErrorDialogFragment()
            fragment.arguments = args
            return fragment
        }
    }
}