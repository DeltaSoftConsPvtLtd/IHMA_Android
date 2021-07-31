package com.deltasoft.ihma.view.drawer

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.deltasoft.ihma.R
import com.deltasoft.ihma.databinding.FragmentAccountDetailsBinding
import com.deltasoft.ihma.databinding.PaymentFragmentBinding
import com.deltasoft.ihma.utilities.IlafSharedPreference
import com.deltasoft.ihma.viewmodel.PaymentViewModel
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import kotlinx.android.synthetic.main.fragment_account_details.view.*
import kotlinx.android.synthetic.main.payment_fragment.view.*
import kotlinx.android.synthetic.main.payment_fragment.view.amount_id
import org.json.JSONObject


class AccountDetailsFragment : AppCompatActivity(),PaymentResultWithDataListener {

    private var amount: String? = null
    private var name: String? = null
    private var phone: String? = null
    private var email: String? = null
    private var amount_value: String? = null
    lateinit var pref: IlafSharedPreference
    lateinit var name_ed: EditText
    lateinit var email_ed: EditText
    lateinit var phone_ed: EditText
    lateinit var amount_tv: TextView
    lateinit var button: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_account_details)

        Checkout.preload(getApplicationContext());
        pref = IlafSharedPreference(this)
        name_ed=findViewById(R.id.name_id)
        email_ed=findViewById(R.id.email_id)
        phone_ed=findViewById(R.id.phone_id)
        amount_tv=findViewById(R.id.amount_id)
        button = findViewById(R.id.verify_id)

        amount= pref.getStringPrefValue(IlafSharedPreference.Constants.FEE_AMOUNT).toString()
        amount_tv.setText(amount)
        button.setOnClickListener {
            startPayment()
        }



        }

    private fun startPayment() {
        name=  name_ed.text.toString()
        phone=  phone_ed.text.toString()
        email=  email_ed.text.toString()
        amount_value =amount+"00"
        val activity: Activity = this
        val co = Checkout()
        co.setKeyID("rzp_live_csVJVkO5hs47UI");

        try {
            val options = JSONObject()
            options.put("name", name)
            options.put("description", " IHMA Fee ")
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
            options.put("theme.color", "#3399cc");
            options.put("currency", "INR");
            // options.put("order_id", "order_DBJOWzybf0sJbb");
             options.put("amount", amount_value)//pass amount in currency subunits


            val retryObj = JSONObject();
            retryObj.put("enabled", true);
            retryObj.put("max_count", 4);
            options.put("retry", retryObj);

            val prefill = JSONObject()
            prefill.put("email", email)
            prefill.put("contact", phone)

            options.put("prefill", prefill)
            co.open(activity, options)
        } catch (e: Exception) {
            Toast.makeText(
                applicationContext,
                "Your Payment has been Cancelled",
                Toast.LENGTH_LONG
            ).show()
            e.printStackTrace()
        }

    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Toast.makeText(applicationContext, "Your Payment is successful", Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(
            applicationContext,
            "Your Payment has been Cancelled",
            Toast.LENGTH_LONG
        ).show()
    }
}