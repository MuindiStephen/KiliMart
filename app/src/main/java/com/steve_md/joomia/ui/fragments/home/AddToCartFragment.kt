/*
 * Copyright (c)  Stephen Muindi
 */

package com.steve_md.joomia.ui.fragments.home


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.steve_md.joomia.R
import com.steve_md.joomia.adapters.CartAdapter
import com.steve_md.joomia.databinding.FragmentAddToCartBinding
import com.steve_md.joomia.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddToCartFragment : Fragment() {

    private lateinit var binding: FragmentAddToCartBinding

    private lateinit var cartAdapter : CartAdapter

    private val cartViewModel by viewModels<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val inflate = FragmentAddToCartBinding.inflate(inflater, container, false)
        binding = inflate
        val root = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    
    /*
   *Getting all cart items */
        cartViewModel.cartLineItems.observe(viewLifecycleOwner) { cartItem ->
            cartAdapter.submitList(cartItem)


            checkCartQuantity()

            /*if (binding.cartLineItemsRecyclerView.adapter?.itemCount == 0){
                binding.cartImage.isVisible = true
                binding.textViewNoCartItem.isVisible = true
                binding.buttonStartShopping.isVisible = true
            }*/
        }



        /*
        *Handling deleting an item from the cart */
        cartAdapter = CartAdapter(CartAdapter.OnClickListener { cartItem->
            cartViewModel.removeOnlyOneItemFromCartLine(cartItem)

        })


       // val callBack = requireActivity().onBackPressedDispatcher.addCallback(this)


    }

    private fun checkCartQuantity() {

        if (cartAdapter.itemCount == 0) {
            Toast.makeText(requireContext(), "Cart Empty", Toast.LENGTH_SHORT).show()

            binding.cartLineItemsRecyclerView.visibility = View.GONE

            binding.cartLineItemsRecyclerView.removeView(view)

            binding.cartImage.isVisible = true
            binding.textViewNoCartItem.isVisible = true
            binding.buttonStartShopping.isVisible = true
            binding.buttonCheckout.isEnabled = false

            startShopping()
        } else {
            binding.cartLineItemsRecyclerView.adapter = cartAdapter
            binding.buttonCheckout.setOnClickListener {
               showAlertDialog()
            }
        }
    }

    private fun showAlertDialog() {
        AlertDialog.Builder(requireActivity())
            .setTitle("ORDER")
            .setMessage("Are you sure you want to order ?")
            .setPositiveButton("Yes") { _, _ ->
                findNavController().navigate(R.id.action_addToCartFragment_to_deliveryAddressFragment)
            }
            .setNegativeButton("Not Now") { dialog, _ ->
                dialog.cancel()
                findNavController().navigateUp()
                requireActivity().finishAffinity()
            }

            .create()
            .show()
    }

    private fun startShopping() {
        binding.buttonStartShopping.setOnClickListener {
            findNavController().navigate(R.id.action_addToCartFragment_to_homeFragment)
        }
    }


}