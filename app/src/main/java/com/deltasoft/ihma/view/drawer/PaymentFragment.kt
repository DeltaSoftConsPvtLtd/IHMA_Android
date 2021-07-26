package com.deltasoft.ihma.view.drawer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.PaymentFragmentBinding
import com.deltasoft.ihma.viewmodel.PaymentViewModel
import kotlinx.android.synthetic.main.payment_fragment.view.*
import kotlinx.android.synthetic.main.register_fragment.view.*


class PaymentFragment : Fragment() {

    val viewModel: PaymentViewModel by viewModels()
    lateinit var annual: TextView
    lateinit var lifetime: TextView
    lateinit var amount: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val paymentFragmentBinding = DataBindingUtil.inflate<PaymentFragmentBinding>(
            inflater,
            R.layout.payment_fragment,
            container,
            false
        )
        paymentFragmentBinding.lifecycleOwner = this
        paymentFragmentBinding.viewModel = viewModel
        paymentFragmentBinding.fragment=this
        return paymentFragmentBinding.root
    }
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        annual = view.annual_id as TextView
        lifetime = view.lifetime_id as TextView
        amount = view.amount_id as TextView

        annual.setOnClickListener(View.OnClickListener {
            annual.setBackgroundColor(getResources().getColor(R.color.colorPrimary))
            lifetime.setBackgroundColor(getResources().getColor(R.color.white))
            lifetime.setTextColor(getResources().getColor(R.color.colorPrimary))
            annual.setTextColor(getResources().getColor(R.color.white))
            amount.setText("400")


        })
        lifetime.setOnClickListener(View.OnClickListener {
            lifetime.setBackgroundColor(getResources().getColor(R.color.colorPrimary))
            annual.setBackgroundColor(getResources().getColor(R.color.white))
            annual.setText("Annual")
            annual.setTextColor(getResources().getColor(R.color.colorPrimary))
            lifetime.setTextColor(getResources().getColor(R.color.white))
            amount.setText("4000")

        })



    }
    fun onRefundPolicyClicked(view: View) {

        findNavController().navigate(R.id.action_payment_fragment_to_privacypolicy)

    }

}






